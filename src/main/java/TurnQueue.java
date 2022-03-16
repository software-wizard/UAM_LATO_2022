import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class TurnQueue
{

    private final Collection<Creature> creatures;
    private final Queue<Creature> creaturesQueue;
    private Creature currentCreature;

    public TurnQueue( final Collection<Creature> aCreatureList, final Collection<Creature> aCreatureList2) {
        creatures = Stream.concat(aCreatureList.stream(), aCreatureList2.stream())
                .collect(Collectors.toList());
        creaturesQueue = new LinkedList<>();
        initQueue();
        next();
    }

    private void initQueue() {
        creaturesQueue.addAll(creatures);
    }

    Creature getCurrentCreature() {
        return currentCreature;
    }

    void next() {
        if (creaturesQueue.isEmpty()) {
            initQueue();
        }
        currentCreature = creaturesQueue.poll();
    }
}
