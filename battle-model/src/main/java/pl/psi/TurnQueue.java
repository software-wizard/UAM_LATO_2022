package pl.psi;

import pl.psi.creatures.Creature;
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
    private final Queue<Creature> creaturesQueue;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private Creature currentCreature;
    private int roundNumber;

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
    }

    public Creature getCurrentCreature() {
        return currentCreature;
    }

    public void next() {
        if (creaturesQueue.isEmpty()) {
            endOfTurn();
        }
        currentCreature = creaturesQueue.poll();

        if (currentCreature instanceof WarMachinesAbstract) {
            handleWarMachineAction();
        }
    }

    private void endOfTurn() {
        roundNumber++;
        initQueue();
        observerSupport.firePropertyChange(END_OF_TURN, roundNumber - 1, roundNumber);
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
