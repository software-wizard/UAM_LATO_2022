package pl.psi;

import pl.psi.creatures.Creature;

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
    public static final String NEW_TURN = "NEW_TURN";

    private final Collection<Creature> creatures;
    private final LinkedList<Creature> creaturesQueue;
    private final LinkedList<Creature> waitingCreaturesQueue;
//  private final Queue<Creature> creaturesQueue; ???
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private Creature currentCreature;
    private int roundNumber;
    private List<Creature> deadCreatures = new ArrayList<>();
    private List<Point> deadCreaturePoints = new ArrayList<>();
    private List<Creature> hero1Creatures;
    private List<Creature> hero2Creatures;

    public TurnQueue(final Collection<Creature> aCreatureList,
                     final Collection<Creature> aCreatureList2) {
        creatures = Stream.concat(aCreatureList.stream(), aCreatureList2.stream())
                .collect(Collectors.toList());
        creaturesQueue = new LinkedList<>();
        waitingCreaturesQueue = new LinkedList<>();
        hero1Creatures = new ArrayList<>(aCreatureList);
        hero2Creatures = new ArrayList<>(aCreatureList2);
        initQueue();
        creatures.forEach(c -> observerSupport.addPropertyChangeListener(END_OF_TURN, c));
        creatures.forEach(c -> observerSupport.addPropertyChangeListener(NEW_TURN, c));
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
        waitingCreaturesQueue.add(currentCreature);
    }

    public Creature getCurrentCreature() {
        return currentCreature;
    }

    public Collection<Creature> getRangeCreatures(){
        return creatures.stream().filter(Creature::isRange).collect( Collectors.toList() );
    }

    public void addDeadCreature( final Creature creature ){
        deadCreatures.add(creature);
    }

    public List<Creature> getDeadCreatures(){
        return deadCreatures;
    }

    public List<Point> getDeadCreaturePoints(){
        return deadCreaturePoints;
    }

    public void addDeadCreaturePoint(final Point point){
        if(deadCreaturePoints.contains(point)){
            int i = deadCreaturePoints.indexOf(point);
            deadCreaturePoints.remove(i);
            deadCreatures.remove(i);
        }
        deadCreaturePoints.add(point);
    }

    public void next() {
        sortBySpeed();
        if (creaturesQueue.isEmpty()) {
            if(waitingCreaturesQueue.isEmpty()){
                endOfTurn();
                next();
            }
            else{
                currentCreature = waitingCreaturesQueue.poll();
            }
        }
        else{
            currentCreature = creaturesQueue.poll();
        }

        if(!currentCreature.isAlive()){
            next();
        }

        newTurn();

        if(!currentCreature.isActive()) {
            next();
        }
    }

    private void endOfTurn() {
        roundNumber++;
        initQueue();
        observerSupport.firePropertyChange(END_OF_TURN, roundNumber - 1, roundNumber);
    }

    private void newTurn() {
        if (hero1Creatures.contains(currentCreature)) {
            observerSupport.firePropertyChange(NEW_TURN,hero2Creatures,hero1Creatures);
        } else {
            observerSupport.firePropertyChange(NEW_TURN, hero1Creatures, hero2Creatures);
        }
    }

    public int getRoundNumber(){
        return roundNumber+1;
    }
}
