package pl.psi;

import lombok.Getter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.FirstAidTent;
import pl.psi.spells.AreaDamageSpell;
import pl.psi.spells.Spell;
import pl.psi.spells.SpellFactory;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
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
    private boolean currentCreatureCanMove = true;
    private boolean currentCreatureCanAttack = true;
    private String attackInformation = "";
    private String additionalInformation = "";
    private String spellCastInformation = "";
    private int turnNumber = 0;
    private List<Pair> deadCreaturesList = new ArrayList<>();

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

    public List<Point> getCurrentCreatureSplashDamagePointsList(Point point){
        return board.getCreatureSplashDamagePointsList(getCurrentCreature(), point);
    }

    private void AttackAndPrintAttackInformation(final Point point) {
        applyAttackLogic(point);
    }

    private void applyAttackLogic(Point point) {
        List<Point> pointsToAttackList = getCurrentCreatureSplashDamagePointsList(point);
        AtomicInteger counter = new AtomicInteger();
        pointsToAttackList.forEach(point1 -> board.getCreature(point1)
                .ifPresent(defender -> {
                    int defenderBeforeAmount = defender.getAmount();
                    int attackerBeforeAmount = getCurrentCreature().getAmount();
                    if(defender.isAlive() && isEnemy(defender)){
                        turnQueue.getCurrentCreature()
                                .attack(defender);
                        counter.addAndGet(1);
                        int defenderAfterAmount = defender.getAmount();
                        int attackerAfterAmount = getCurrentCreature().getAmount();
                        if(counter.get()>1){
                            getCurrentCreature().addShots(1);
                        }
                        if(defenderBeforeAmount == defenderAfterAmount){
                            attackInformation = getCurrentCreature().getName() + " attacked " + defender.getName() + " for " + (int)(getCurrentCreature().getLastAttackDamage()) + ".\n" + attackInformation;
                        }
                        else{
                            attackInformation = getCurrentCreature().getName() + " attacked " + defender.getName() + " for " + (int)(getCurrentCreature().getLastAttackDamage()) + ". " + (defenderBeforeAmount-defenderAfterAmount) + " " + defender.getName() + " perish.\n" + attackInformation;
                        }
                        if(defender.getLastCounterAttackDamage() > 0){
                            if(attackerBeforeAmount == attackerAfterAmount){
                                attackInformation = defender.getName() + " counter attacked " + getCurrentCreature().getName() + " for " + (int)(defender.getLastCounterAttackDamage()) + ".\n" + attackInformation;
                            }
                            else{
                                attackInformation = defender.getName() + " counter attacked " + getCurrentCreature().getName() + " for " + (int)(defender.getLastCounterAttackDamage()) + ". " + (attackerBeforeAmount-attackerAfterAmount) + " " + getCurrentCreature().getName() + " perish.\n" + attackInformation;
                            }
                            defender.clearLastCounterAttackDamage();
                        }
                        if(getCurrentCreature().getLastHealAmount() > 0){
                            attackInformation = getCurrentCreature().getName() + " healed for " + getCurrentCreature().getLastHealAmount() + ".\n" + attackInformation;
                        }
                    }
                }));
    }

    public boolean canAttack(final Point aPoint) {
        return board.canAttack( turnQueue.getCurrentCreature(), aPoint )  && board.getCreature( aPoint ).isPresent() && board.getCreature(aPoint).get().isAlive() && isEnemy(board.getCreature(aPoint).get()) && currentCreatureCanAttack && getCurrentCreature().getShots()>0;
    }

    public void lastMove(final Point aPoint){
        currentCreatureCanMove = false;
        if(turnQueue.getCurrentCreature().isDefending()){
            turnQueue.getCurrentCreature().defend(false);
        }
        move(aPoint);
        turnQueue.getRangeCreatures().forEach(this::creatureInMeleeRange); // dont ask me why its setting this again, i dont know either
        if((turnQueue.getCurrentCreature().isRange() && turnQueue.getCurrentCreature().getAttackRange() > 2 ) || !board.canCreatureAttackAnyone( turnQueue.getCurrentCreature() )){
            pass();
        }
        observerSupport.firePropertyChange(CREATURE_MOVED, null, aPoint);
    }


    public void move(final Point aPoint) {
        if(getCreature(aPoint).isEmpty() || !getCreature(aPoint).get().isAlive()){
            board.move(turnQueue.getCurrentCreature(), aPoint);
        }
    }

    public void putDeadCreatureOnBoard(){
        board.putDeadCreaturesOnBoard();
    }

    public void updateDeadCreaturesList() {
        deadCreaturesList = board.getDeadCreaturePositions();
    }

    public List<Pair> getDeadCreaturesList(){
        updateDeadCreaturesList();
        return deadCreaturesList;
    }

    public boolean canMove(final Point aPoint) {
        turnQueue.getRangeCreatures().forEach(this::creatureInMeleeRange); // setting inMelee for every range creature
        if(board.getCreature(aPoint).isPresent()){
            return !board.getCreature(aPoint).get().isAlive() && board.canMove(turnQueue.getCurrentCreature(), aPoint) && currentCreatureCanMove;
        }
        else{
            return board.canMove(turnQueue.getCurrentCreature(), aPoint) && currentCreatureCanMove;
        }
    }

    public List<Point> getPath(Point aPoint){
        return board.getPathToPoint(board.getCreaturePosition(getCurrentCreature()),aPoint);
    }

    public Point getCreaturePosition(final Creature creature){
        return board.getCreaturePosition(creature);
    }

    public void heal(final Point point) {
        FirstAidTent firstAidTent = (FirstAidTent) turnQueue.getCurrentCreature();
        board.getCreature(point)
                .ifPresent(firstAidTent::healCreature);
    }

    public String getAttackInformation(){
        return attackInformation;
    }

    public String getAdditionalInformation(){
        turnNumber = turnQueue.getRoundNumber();
        return additionalInformation;
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
        List<Optional<Creature>> creaturesOnAdjacentPositions = adjacentPositionsList.stream().map(this::getAliveEnemyCreature).collect( Collectors.toList() );
        while (creaturesOnAdjacentPositions.remove(Optional.empty())) {  // remove every instance of "Optional.empty null" from list
        }
        creature.setInMelee( !creaturesOnAdjacentPositions.isEmpty() );
    }

    public List<Optional<Creature>> getCreaturesOnPointList( final List<Point> pointList ){
        List<Optional<Creature>> creaturesOnAdjacentPositions = pointList.stream().map(this::getAliveEnemyCreature).collect( Collectors.toList() );
        while (creaturesOnAdjacentPositions.remove(Optional.empty())) {  // remove every instance of "Optional.empty null" from list
        }
        return creaturesOnAdjacentPositions;
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

    private Optional<Creature> getAliveEnemyCreature(final Point aPoint) {
        if( board.getCreature( aPoint ).isPresent() ){
            if( isEnemy(board.getCreature(aPoint).get()) && board.getCreature(aPoint).get().isAlive()){
                return board.getCreature(aPoint);
            }
        }
        return Optional.empty();
    }

    private Hero getCreatureHero(Creature creature){
        if(hero1.getCreatures().contains(creature)){
            return hero1;
        }
        return hero2;
    }

    private boolean isEnemy(Creature creature){
        return !getCreatureHero(getCurrentCreature()).equals(getCreatureHero(creature));
    }

    public String getCreatureInformation(Point point){
        return getCreature(point).get().getCreatureInformation();
    }

    public boolean canCastSpell(Point aPoint){
        return board.getCreature(aPoint).isPresent() && getCurrentCreature().getSpellCastCounter()>0 && !isEnemy(getCreature(aPoint).get()) && getCreature(aPoint).get().isAlive();
    }

    public void castCurrentCreatureSpell(Point aPoint){
        final Spell spell = new SpellFactory().create(getCurrentCreature().getSpellName(), getCurrentCreature().getSpellRang(), getCurrentCreature().getSpellPower());
        castSpell(aPoint,spell);
        getCurrentCreature().reduceNumberOfSpellCasts();
        spellCastInformation = getCurrentCreature().getName() + " casted " + spell.getName() + " on " + getCreature(aPoint).get().getName();
        pass();
        observerSupport.firePropertyChange(CREATURE_MOVED, null, null);
    }

    public String getSpellCastInformation(){
        return spellCastInformation;
    }

    public void pass() {
        currentCreatureCanMove = true;
        currentCreatureCanAttack = true;
        turnQueue.next();
        additionalInformation = "";

        if(turnNumber != turnQueue.getRoundNumber()){
            additionalInformation = "TURN " + turnQueue.getRoundNumber();
        }

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
