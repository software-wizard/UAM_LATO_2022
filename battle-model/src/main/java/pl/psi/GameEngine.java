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

import static java.lang.Math.abs;
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
    private boolean currentCreatureCanMove = true;
    private boolean currentCreatureCanAttack = true;
    private String attackInformation = "";
    private String additionalAttackInformation = "";

    public GameEngine(final Hero aHero1, final Hero aHero2) {
        hero1 = aHero1;
        hero2 = aHero2;
        turnQueue = new TurnQueue(aHero1.getCreatures(), aHero2.getCreatures());
        board = new Board(aHero1.getCreatures(), aHero2.getCreatures());
    }

    public void attack(final Point point) {
        if(turnQueue.getCurrentCreature().isDefending()){
            turnQueue.getCurrentCreature().defend(false);
        }
        AttackAndPrintAttackInformation(point);
        currentCreatureCanMove = false;
        currentCreatureCanAttack = false;
        pass();
        observerSupport.firePropertyChange(CREATURE_MOVED, null, null);

    }

    private void AttackAndPrintAttackInformation(final Point point) {
        double maxHp = getCreature(point).get().getStats().getMaxHp();
        int beforeAmount = getCreature(point).get().getAmount();
        double beforeCurrentHp = getCreature(point).get().getCurrentHp();
        board.getCreature(point)
                .ifPresent(defender -> turnQueue.getCurrentCreature()
                        .attack(defender));
        additionalAttackInformation = "";
        int afterAmount = getCreature(point).get().getAmount();
        double afterCurrentHp = getCreature(point).get().getCurrentHp();
        //int damageDealt = (int) calculateDamageDealt(maxHp, beforeAmount, beforeCurrentHp, afterAmount, afterCurrentHp );
        int damageDealt = (int) getCurrentCreature().getLastAttackDamage();
        if(beforeAmount-afterAmount == 0){
            System.out.println(getCurrentCreature().getName() + " attacked " + getCreature(point).get().getName() + " for " + damageDealt + ".");
            attackInformation = getCurrentCreature().getName() + " attacked " + getCreature(point).get().getName() + " for " + damageDealt + ".";
        }
        else{
            System.out.println(getCurrentCreature().getName() + " attacked " + getCreature(point).get().getName() + " for " + damageDealt + ". " + (beforeAmount-afterAmount) + " " + getCreature(point).get().getName() + " perish.");
            attackInformation = getCurrentCreature().getName() + " attacked " + getCreature(point).get().getName() + " for " + damageDealt + ". " + (beforeAmount-afterAmount) + " " + getCreature(point).get().getName() + " perish.";
        }
        if(getCurrentCreature().getLastCounterAttackDamage() > 0){
            System.out.println(getCreature(point).get().getName() + " counter attacked " + getCurrentCreature().getName() + " for " + getCurrentCreature().getLastCounterAttackDamage() + ".");
            attackInformation = attackInformation + "\n" + getCreature(point).get().getName() + " counter attacked " + getCurrentCreature().getName() + " for " + (int)(getCurrentCreature().getLastCounterAttackDamage()) + ".";
        }
        if(getCurrentCreature().getLastHealAmount() > 0){
            additionalAttackInformation = getCurrentCreature().getName() + " healed for " + getCurrentCreature().getLastHealAmount();
        }
    }

    private double calculateDamageDealt(double maxHp, int beforeAmount, double beforeCurrentHp, int afterAmount, double afterCurrentHp) {
        if(afterAmount == 0){
            return (beforeAmount - 1) * maxHp + beforeCurrentHp;
        }
        return (beforeAmount - afterAmount) * maxHp + abs(beforeCurrentHp - afterCurrentHp);
    }

    public boolean canAttack(final Point point) {
        return board.canAttack( turnQueue.getCurrentCreature(), point )  && board.getCreature( point ).isPresent() && board.getCreature(point).get().isAlive() && board.getCreature( point ).get().getHeroNumber() != turnQueue.getCurrentCreature().getHeroNumber() && currentCreatureCanAttack;
    }

    public void move(final Point aPoint) {
        currentCreatureCanMove = false;
        if(turnQueue.getCurrentCreature().isDefending()){
            turnQueue.getCurrentCreature().defend(false);
        }
        board.move(turnQueue.getCurrentCreature(), aPoint);
        turnQueue.getRangeCreatures().forEach(this::creatureInMeleeRange); // dont ask me why its setting this again, i dont know either
        if((turnQueue.getCurrentCreature().isRange() && turnQueue.getCurrentCreature().getAttackRange() > 2 ) || !board.canCreatureAttackAnyone( turnQueue.getCurrentCreature() )){
            pass();
        }
        observerSupport.firePropertyChange(CREATURE_MOVED, null, aPoint);
    }

    public boolean canMove(final Point aPoint) {
        turnQueue.getRangeCreatures().forEach(this::creatureInMeleeRange); // setting inMelee for every range creature
        /** in this version you can move to tile with dead creature on it, not ideal because board hash map cant store 2 creatures on the same field so if you move on it information about the creature is lost. Remember to ask spells about resurrection */
//        if(board.getCreature(aPoint).isPresent()){
//            return !board.getCreature(aPoint).get().isAlive() && board.canMove(turnQueue.getCurrentCreature(), aPoint) && currentCreatureCanMove && turnQueue.getCurrentCreature().canAttack();
//        }
//        else{
//            return board.canMove(turnQueue.getCurrentCreature(), aPoint) && currentCreatureCanMove && turnQueue.getCurrentCreature().canAttack();
//        }
        return board.getCreature(aPoint).isEmpty() && board.canMove(turnQueue.getCurrentCreature(), aPoint) && currentCreatureCanMove;
    }

    public void heal(final Point point) {
        FirstAidTent firstAidTent = (FirstAidTent) turnQueue.getCurrentCreature();
        board.getCreature(point)
                .ifPresent(firstAidTent::healCreature);
    }

    public boolean anyActionLeft(){
        return currentCreatureCanAttack || currentCreatureCanMove;
    }

    public String getAttackInformation(){
        return attackInformation + "\n" + additionalAttackInformation;
    }

    public boolean allActionLeft(){
        return currentCreatureCanAttack && currentCreatureCanMove;
    }

    public void waitAction(){
        turnQueue.pushCurrentCreatureToEndOfQueue();
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

    public Creature getCurrentCreature(){
        return turnQueue.getCurrentCreature();
    }

    public boolean isCurrentCreature( final Point point ){
        return point.getX() == board.getCreaturePosition( turnQueue.getCurrentCreature() ).getX() && point.getY() == board.getCreaturePosition( turnQueue.getCurrentCreature() ).getY() ;
    }

    public boolean isCurrentCreatureAlive(){
        return turnQueue.getCurrentCreature().isAlive();
    }

    private Optional<Creature> getEnemyCreature(final Point aPoint) {
        if( board.getCreature( aPoint ).isPresent() ){
            if( board.getCreature(aPoint).get().getHeroNumber() != turnQueue.getCurrentCreature().getHeroNumber()){
                return board.getCreature(aPoint);
            }
        }
        return Optional.empty();
    }

    public String getCreatureInformation(Point point){
        return getCreature(point).get().getCreatureInformation();
    }


    public void pass() {
        currentCreatureCanMove = true;
        currentCreatureCanAttack = true;
        turnQueue.next();
    }

    public void pushCurrentCreatureToEndOfQueue(){
        turnQueue.pushCurrentCreatureToEndOfQueue();
    }

    public void addObserver(final String aEventType, final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aEventType, aObserver);
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

    public void defendAction() {
        turnQueue.getCurrentCreature().defend(true);
    }
}
