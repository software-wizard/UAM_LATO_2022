package pl.psi;

import lombok.Getter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.FirstAidTent;
import pl.psi.spells.AreaDamageSpell;
import pl.psi.spells.BuffDebuffSpell;
import pl.psi.spells.Spell;
import pl.psi.spells.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.ceil;
import static pl.psi.TurnQueue.END_OF_TURN;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngine {

    public static final String CREATURE_MOVED = "CREATURE_MOVED";
    private final TurnQueue turnQueue;
    private final Board board;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    @Getter
    private final Hero hero1;
    @Getter
    private final Hero hero2;
    private boolean currentCreatureCanMove = true;
    private boolean currentCreatureCanAttack = true;
    private String attackInformation = "";
    private String additionalInformation = "";
    private String spellCastInformation = "";

    public GameEngine(final Hero aHero1, final Hero aHero2) {
        hero1 = aHero1;
        hero2 = aHero2;
        turnQueue = new TurnQueue(aHero1.getCreatures(), aHero2.getCreatures());
        board = new Board(aHero1.getCreatures(), aHero2.getCreatures());
    }

    public void attack(final Point aPoint) {
        if (turnQueue.getCurrentCreature().isDefending()) {
            turnQueue.getCurrentCreature().defend(false);
        }
        AttackAndPrintAttackInformation(aPoint);
        currentCreatureCanMove = false;
        currentCreatureCanAttack = false;
        if (!getCreature(aPoint).get().isAlive()) {
            turnQueue.addDeadCreature(getCreature(aPoint).get());
            turnQueue.addDeadCreaturePoint(aPoint);
        }
        if (!getCurrentCreature().isAlive()) {
            turnQueue.addDeadCreature(getCurrentCreature());
            turnQueue.addDeadCreaturePoint(getCreaturePosition(getCurrentCreature()));
        }
//        updateDeadCreatureLists();
        pass();
        observerSupport.firePropertyChange(CREATURE_MOVED, null, null);
    }

    public List<Point> getCurrentCreatureSplashDamagePointsList(final Point aPoint) {
        return board.getCreatureSplashDamagePointsList(getCurrentCreature(), aPoint);
    }

    private void AttackAndPrintAttackInformation(final Point pointToAttack) {
        applyAttackLogic(pointToAttack);
    }

    private void applyAttackLogic(final Point aPoint) {
        List<Point> pointsToAttackList = getCurrentCreatureSplashDamagePointsList(aPoint);
        AtomicInteger counter = new AtomicInteger();
        pointsToAttackList.forEach(point -> board.getCreature(point)
                .ifPresent(defender -> {
                    int defenderBeforeAmount = defender.getAmount();
                    int attackerBeforeAmount = getCurrentCreature().getAmount();
                    if (defender.isAlive() && isEnemy(defender)) {
                        turnQueue.getCurrentCreature()
                                .attack(defender);
                        counter.addAndGet(1);
                        int defenderAfterAmount = defender.getAmount();
                        int attackerAfterAmount = getCurrentCreature().getAmount();
                        if (counter.get() > 1) {
                            getCurrentCreature().addShots(1);
                        }

                        if (defenderBeforeAmount == defenderAfterAmount) {
                            attackInformation = getCurrentCreature().getName() + " attacked " + defender.getName() + " for "
                                    + (int) (getCurrentCreature().getLastAttackDamage()) + ".\n" + attackInformation;
                        } else {
                            attackInformation = getCurrentCreature().getName() + " attacked " + defender.getName() + " for "
                                    + (int) (getCurrentCreature().getLastAttackDamage()) + ". " + (defenderBeforeAmount - defenderAfterAmount)
                                    + " " + defender.getName() + " perish.\n" + attackInformation;
                        }

                        if (defender.getLastCounterAttackDamage() > 0) {
                            if (attackerBeforeAmount == attackerAfterAmount) {
                                attackInformation = defender.getName() + " counter attacked " + getCurrentCreature().getName() + " for "
                                        + (int) (defender.getLastCounterAttackDamage()) + ".\n" + attackInformation;
                            } else {
                                attackInformation = defender.getName() + " counter attacked " + getCurrentCreature().getName() + " for "
                                        + (int) (defender.getLastCounterAttackDamage()) + ". " + (attackerBeforeAmount - attackerAfterAmount)
                                        + " " + getCurrentCreature().getName() + " perish.\n" + attackInformation;
                            }
                            defender.clearLastCounterAttackDamage();
                        }

                        if (getCurrentCreature().getLastHealAmount() > 0) {
                            attackInformation = getCurrentCreature().getName() + " healed for " + getCurrentCreature().getLastHealAmount() + ".\n" + attackInformation;
                        }
                    }
                }));
    }

    public boolean canAttack(final Point aPoint) {
        return board.getCreature(aPoint).isPresent()
                && board.canAttack(turnQueue.getCurrentCreature(), aPoint)
                && isEnemy(board.getCreature(aPoint).get())
                && currentCreatureCanAttack
                && getCurrentCreature().getShots() > 0;
    }

    public void lastMove(final Point aPoint) {
        currentCreatureCanMove = false;
        if (turnQueue.getCurrentCreature().isDefending()) {
            turnQueue.getCurrentCreature().defend(false);
        }

        move(aPoint);
        if (getCurrentCreature().isRange()) {
            turnQueue.getRangeCreatures().forEach(this::creatureInMeleeRange);
        }

        if ((turnQueue.getCurrentCreature().isRange() && turnQueue.getCurrentCreature().getAttackRange() > 2)
                || !board.canCreatureAttackAnyone(turnQueue.getCurrentCreature())) {
            pass();
        }
        observerSupport.firePropertyChange(CREATURE_MOVED, null, aPoint);
    }

    public boolean canMove(final Point aPoint) {
        if (turnQueue.getCurrentCreature().isRange()) {
            turnQueue.getRangeCreatures().forEach(this::creatureInMeleeRange);
        }
        if (board.getCreature(aPoint).isPresent()) {
            return !board.getCreature(aPoint).get().isAlive()
                    && board.canMove(turnQueue.getCurrentCreature(), aPoint)
                    && currentCreatureCanMove;
        } else {
            return board.canMove(turnQueue.getCurrentCreature(), aPoint) && currentCreatureCanMove;
        }
    }

    public List<Point> getPath(final Point aPoint) {
        return board.getPathToPoint(board.getCreaturePosition(getCurrentCreature()), aPoint);
    }

    public Point getCreaturePosition(final Creature aCreature) {
        return board.getCreaturePosition(aCreature);
    }

    public void heal(final Point aPoint) {
        FirstAidTent firstAidTent = (FirstAidTent) turnQueue.getCurrentCreature();
        board.getCreature(aPoint)
                .ifPresent(firstAidTent::healCreature);
    }

    public String getAttackInformation() {
        return attackInformation;
    }

    public boolean allActionsLeft() {
        return currentCreatureCanAttack && currentCreatureCanMove;
    }

    public void move(final Point aPoint) {
        board.move(turnQueue.getCurrentCreature(), aPoint);
        turnQueue.next();
        observerSupport.firePropertyChange(CREATURE_MOVED, null, aPoint);
    }

    public void waitAction() {
        turnQueue.pushCurrentCreatureToEndOfQueue();
        pass();
    }

    private void creatureInMeleeRange(final Creature aCreature) {
        Point position = board.getCreaturePosition(aCreature);
        List<Point> adjacentPositionsList = board.getAdjacentPositions(position);
        List<Optional<Creature>> creaturesOnAdjacentPositions = adjacentPositionsList.stream().map(this::getAliveEnemyCreature).collect(Collectors.toList());
        while (creaturesOnAdjacentPositions.remove(Optional.empty())) {  // remove every instance of "Optional.empty null" from list
        }
        aCreature.setInMelee(!creaturesOnAdjacentPositions.isEmpty());
    }

    public Optional<Creature> getCreature(final Point aPoint) {
        return board.getCreature(aPoint);
    }

    public Creature getCurrentCreature() {
        return turnQueue.getCurrentCreature();
    }

    public boolean isCurrentCreature(final Point point) {
        return point.getX() == board.getCreaturePosition(turnQueue.getCurrentCreature()).getX() && point.getY() == board.getCreaturePosition(turnQueue.getCurrentCreature()).getY();
    }

    public boolean isCurrentCreatureAlive() {
        return turnQueue.getCurrentCreature().isAlive();
    }

    private Optional<Creature> getAliveEnemyCreature(final Point aPoint) {
        if (board.getCreature(aPoint).isPresent()) {
            if (isEnemy(board.getCreature(aPoint).get()) && board.getCreature(aPoint).get().isAlive()) {
                return board.getCreature(aPoint);
            }
        }
        return Optional.empty();
    }

    private Hero getCreatureHero(final Creature aCreature) {
        if (hero1.getCreatures().contains(aCreature)) {
            return hero1;
        }
        return hero2;
    }

    private boolean isEnemy(final Creature aCreature) {
        return !getCreatureHero(getCurrentCreature()).equals(getCreatureHero(aCreature));
    }

    public String getCreatureInformation(final Point aPoint) {
        return getCreature(aPoint).get().getCreatureInformation();
    }

    public boolean canCastSpell(final Point aPoint) {
        return board.getCreature(aPoint).isPresent() && getCurrentCreature().getSpellCastCounter() > 0 && !isEnemy(getCreature(aPoint).get()) && getCreature(aPoint).get().isAlive();
    }

    public String getSpellCastInformation() {
        return spellCastInformation;
    }

    public void pass() {
        currentCreatureCanMove = true;
        currentCreatureCanAttack = true;
        turnQueue.next();
        additionalInformation = "";
        board.putDeadCreaturesOnBoard(turnQueue.getDeadCreatures(), turnQueue.getDeadCreaturePoints());
    }

    public String getRoundNumber() {
        return "    Turn " + turnQueue.getRoundNumber();
    }

    public void addObserver(final String aEventType, final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aEventType, aObserver);
    }

    public void addObserverToTurnQueue(final String aEventType, final PropertyChangeListener aObserver) {
        turnQueue.addObserver(aEventType, aObserver);
    }


    public boolean canAttack(final Point point) {
        return false;
    public void addObserverToTurnQueue(final String aEventType, final PropertyChangeListener aObserver) {
        turnQueue.addObserver(aEventType, aObserver);
    }

    public boolean canHeal(final Point aPoint) {
        Creature currentCreature = turnQueue.getCurrentCreature();

        return currentCreature instanceof FirstAidTent
                && board.getCreature(aPoint)
                .filter(creature -> creature.getHeroNumber() == currentCreature.getHeroNumber())
                .isPresent();
    }

    public Hero getCurrentHero() {
        return (hero1.getCreatures().contains(turnQueue.getCurrentCreature())) ? hero1 : hero2;
    }

    private Hero getEnemyHero() {
        return (hero1.getCreatures().contains(turnQueue.getCurrentCreature())) ? hero2 : hero1;
    }

    public void castSpell(final Point point, Spell<? extends SpellableIf> spell) {
        switch (spell.getCategory()) {
            case FIELD:
                board.getCreature(point)
                        .ifPresent(defender -> {
                            if (spell instanceof BuffDebuffSpell) {
                                BuffDebuffSpell buffDebuffSpell = new BuffDebuffSpell((BuffDebuffSpell) spell, defender);
                                turnQueue.addObserver(END_OF_TURN, (buffDebuffSpell.getRoundTimer()));
                                turnQueue.getCurrentCreature().castSpell(defender, buffDebuffSpell);
                            } else if ((spell instanceof Dispel) && (spell.getRang() == SpellRang.BASIC)) {
                                if (isCreatureAllied()) {
                                    turnQueue.getCurrentCreature()
                                            .castSpell(defender, spell);
                                } else {
                                    System.out.println("You can't cast basic dispel on this creature!"); // change to Sl4j
                                }
                            } else {
                                turnQueue.getCurrentCreature()
                                        .castSpell(defender, spell);
                            }
                        });
                break;
            case AREA:
                board.getCreature(point)
                        .ifPresent(defender -> {
                            SpellCreatureList creatureList = new SpellCreatureList(getCreaturesFromArea(point, (AreaDamageSpell) spell));
                            turnQueue.getCurrentCreature().castSpell(creatureList, spell);
                        });
                break;
            case FOR_ALL_ENEMY_CREATURES:
                getEnemyHero().getCreatures().forEach(creature -> {
                    creature.castSpell(creature, spell);
                });
                break;
            case FOR_ALL_ALLIED_CREATURES:
                getCurrentHero().getCreatures().forEach(creature -> {
                    creature.castSpell(creature, spell);
                });
                break;
            case FOR_ALL_CREATURES:
                Stream.concat(getCurrentHero().getCreatures().stream(), getEnemyHero().getCreatures().stream())
                        .forEach(creature -> {
                            creature.castSpell(creature, spell);
                        });
                break;
            default:
                throw new IllegalArgumentException("Not supported category.");
        }
    }

    private boolean isCreatureAllied() {
        return getCurrentHero().getCreatures().contains(turnQueue.getCurrentCreature());
    }

    //ToDO: In the future think about better solution and refactor this
    private List<Creature> getCreaturesFromArea(Point point, AreaDamageSpell areaDamageSpell) {
        List<Creature> creatures = new ArrayList<>();

        int centerOfArea = (int) ceil((float) areaDamageSpell.getArea().length / 2);

        int startX = point.getX() - centerOfArea + 1;
        int endX = startX + (centerOfArea * 2) - 1;
        int startY = point.getY() - centerOfArea + 1;
        int endY = startY + (centerOfArea * 2) - 1;

        for (int i = startY; i < endY; i++) {
            for (int j = startX; j < endX; j++) {
                if (board.getCreature(new Point(j, i)).isPresent() && areaDamageSpell.getArea()[i - startY][j - startX]) {
                    board.getCreature(new Point(j, i)).ifPresent(creatures::add);
                }
            }
        }

        return creatures;
    }

    public boolean canCastSpell() {
        return getCurrentHero().isHeroCastingSpell();
    }

    public void defendAction() {
        turnQueue.getCurrentCreature().defend(true);
        pass();
    }

    public void getDeadCreaturesInformation() {
        for (int i = 0; i < turnQueue.getDeadCreaturePoints().size(); i++) {
            System.out.println("i=" + i + " Creature: " + turnQueue.getDeadCreatures().get(i).getName() + " Point: " + turnQueue.getDeadCreaturePoints().get(i) + "\n");
        }
        System.out.println("---------------------------------------------------------\n");
    }
}
