package pl.psi;

import lombok.Getter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.FirstAidTent;
import pl.psi.spells.AreaDamageSpell;
import pl.psi.spells.Spell;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Math.ceil;

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

    public GameEngine(final Hero aHero1, final Hero aHero2) {
        hero1 = aHero1;
        hero2 = aHero2;
        turnQueue = new TurnQueue(aHero1.getCreatures(), aHero2.getCreatures());
        board = new Board(aHero1.getCreatures(), aHero2.getCreatures());
    }

    public void attack(final Point point) {
        board.getCreature(point)
                .ifPresent(defender -> turnQueue.getCurrentCreature()
                        .attack(defender));
    }

    public void heal(final Point point) {
        FirstAidTent firstAidTent = (FirstAidTent) turnQueue.getCurrentCreature();
        board.getCreature(point)
                .ifPresent(firstAidTent::healCreature);
    }

    public boolean canMove(final Point aPoint) {
        return board.canMove(turnQueue.getCurrentCreature(), aPoint) && board.getCreature( aPoint ).isEmpty();
    }

    public void move(final Point aPoint) {
        board.move(turnQueue.getCurrentCreature(), aPoint);
        turnQueue.getRangeCreatures().forEach(this::creatureInMeleeRange);
        observerSupport.firePropertyChange(CREATURE_MOVED, null, aPoint);
    }

    private void creatureInMeleeRange( final Creature creature ){
        Point position = board.getCreaturePosition( creature );
        List<Point> adjacentPositionsList = board.getAdjacentPositions( position );
        List<Optional<Creature>> creaturesOnAdjacentPositions = adjacentPositionsList.stream().map(this::getEnemyCreature).collect( Collectors.toList() );
        while (creaturesOnAdjacentPositions.remove(Optional.empty())) {  // remove every instance of "Optional.empty null" from list
        }
        creature.setInMelee( !creaturesOnAdjacentPositions.isEmpty() );
    }


    public Optional<Creature> getCreature(final Point aPoint) {
        return board.getCreature(aPoint);
    }

    private Optional<Creature> getEnemyCreature(final Point aPoint) {
        if( board.getCreature( aPoint ).isPresent() ){
            if( board.getCreature(aPoint).get().getHeroNumber() != turnQueue.getCurrentCreature().getHeroNumber()){
                return board.getCreature(aPoint);
            }
        }
        return Optional.empty();
    }


    public void pass() {
        turnQueue.next();
    }

    public void addObserver(final String aEventType, final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aEventType, aObserver);
    }

    public boolean canAttack(final Point point) {
        return board.canAttack( turnQueue.getCurrentCreature(), point )  && board.getCreature( point ).isPresent() && board.getCreature( point ).get().getHeroNumber() != turnQueue.getCurrentCreature().getHeroNumber();
    }

    public boolean canHeal(final Point point) {
        Creature currentCreature = turnQueue.getCurrentCreature();

        return currentCreature instanceof FirstAidTent
                && board.getCreature(point)
                .filter(creature -> creature.getHeroNumber() == currentCreature.getHeroNumber())
                .isPresent();
    }

    public void castSpell(final Point point, Spell spell) {
        switch (spell.getCategory()) {
            case FIELD:
                board.getCreature(point)
                        .ifPresent(defender -> {
                            turnQueue.getCurrentCreature()
                                    .castSpell(defender, spell);
                        });
                break;
            case AREA:
                List<Creature> creatureList = getCreaturesFromArea(point, (AreaDamageSpell) spell);
                turnQueue.getCurrentCreature().castSpell(creatureList, spell);
                break;
            default:
                throw new IllegalArgumentException("Not supported category.");
        }

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
                if (board.getCreature(new Point(j, i)).isPresent()) {
                    board.getCreature(new Point(j, i)).ifPresent(creatures::add);
                }
            }
        }

        return creatures;
    }
}
