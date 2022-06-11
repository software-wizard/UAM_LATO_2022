package pl.psi;

import pl.psi.creatures.Creature;
import pl.psi.creatures.ShooterCreature;
import pl.psi.creatures.WarMachinesAbstract;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class TurnQueue {

    public static final String END_OF_TURN = "END_OF_TURN";
    private final Collection<Creature> creatures;
    private final LinkedList<Creature> creaturesQueue;
//  private final Queue<Creature> creaturesQueue; ???
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private Creature currentCreature;
    private int roundNumber;
    private List<Creature> deadCreatures = new ArrayList<>();
    private List<Point> deadCreaturePoints = new ArrayList<>();

    public TurnQueue(final Collection<Creature> aCreatureList,
                     final Collection<Creature> aCreatureList2) {
        creatures = Stream.concat(aCreatureList.stream(), aCreatureList2.stream())
                .collect(Collectors.toList());
        creaturesQueue = new LinkedList<>();
        initQueue();
        creatures.forEach(c -> observerSupport.addPropertyChangeListener(END_OF_TURN, c));
        next();
    }

    public void addObserver(final String aEventType, final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aEventType, aObserver);
    }

    private void initQueue() {
        creaturesQueue.addAll(creatures);
        sortBySpeed();
    }

    private void sortBySpeed(){
        Collections.sort(creaturesQueue);
    }

    public void pushCurrentCreatureToEndOfQueue(){
        creaturesQueue.add(currentCreature);
    }

    public Creature getCurrentCreature() {
        return currentCreature;
    }

    public Collection<Creature> getRangeCreatures(){
        return creatures.stream().filter(Creature::isRange).collect( Collectors.toList() );
    }

    public void updateDeadCreatures() {
        deadCreatures = creatures.stream().filter(creature -> !creature.isAlive()).collect(Collectors.toList());
    }

    public void addDeadCreature( Creature creature ){
        deadCreatures.add(creature);
    }

    public List<Creature> getDeadCreatures(){
        return deadCreatures;
    }

    public List<Point> getDeadCreaturePoints(){
        return deadCreaturePoints;
    }

    public void addDeadCreaturePoint(Point point){
        if(deadCreaturePoints.contains(point)){
            int i = deadCreaturePoints.indexOf(point);
            deadCreaturePoints.remove(i);
            deadCreatures.remove(i);
        }
        deadCreaturePoints.add(point);
    }

    public void next() {
        if (creaturesQueue.isEmpty()) {
            endOfTurn();
        }
        currentCreature = creaturesQueue.poll();
        if(!currentCreature.isAlive()){
            next();
        }

        if (currentCreature instanceof WarMachinesAbstract) {
            handleWarMachineAction();
        }
    }

    private void endOfTurn() {
        roundNumber++;
        initQueue();
        observerSupport.firePropertyChange(END_OF_TURN, roundNumber - 1, roundNumber);
    }

    public int getRoundNumber(){
        return roundNumber;
    }

    private void handleWarMachineAction() {
        WarMachinesAbstract warMachine = (WarMachinesAbstract) currentCreature;

        if (warMachine.getSkillLevel() == 0) {
            List<Creature> creatureList = new ArrayList<>(creatures);
            warMachine.performAction(creatureList);
            currentCreature = creaturesQueue.poll();
        }
    }
}
