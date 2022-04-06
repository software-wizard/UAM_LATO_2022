package pl.psi;

import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pl.psi.creatures.Creature;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class TurnQueue
{

    public static final String END_OF_TURN = "END_OF_TURN";
    private final Collection< Creature > creatures;
    private final Queue< Creature > creaturesQueue;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport( this );
    private Creature currentCreature;
    private int roundNumber;

    public TurnQueue( final Collection< Creature > aCreatureList,
        final Collection< Creature > aCreatureList2 )
    {
        creatures = Stream.concat( aCreatureList.stream(), aCreatureList2.stream() )
            .collect( Collectors.toList() );
        creaturesQueue = new LinkedList<>();
        initQueue();
        creatures.forEach( c -> observerSupport.addPropertyChangeListener( END_OF_TURN, c ) );
        next();
    }

    private void initQueue()
    {
        creaturesQueue.addAll( creatures );
    }

    public Creature getCurrentCreature()
    {
        return currentCreature;
    }

    public void next()
    {
        if( creaturesQueue.isEmpty() )
        {
            endOfTurn();
        }
        currentCreature = creaturesQueue.poll();
    }

    private void endOfTurn()
    {
        roundNumber++;
        initQueue();
        observerSupport.firePropertyChange( END_OF_TURN, roundNumber - 1, roundNumber );
    }
}
