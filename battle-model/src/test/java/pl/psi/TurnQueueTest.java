package pl.psi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import pl.psi.creatures.Creature;

class TurnQueueTest
{
    @Test
    void shouldAddPawnsCorrectly()
    {
        final Creature creature1 = new Creature.Builder().build();
        final Creature creature2 = new Creature.Builder().build();
        final Creature creature3 = new Creature.Builder().build();
        final TurnQueue turnQueue = new TurnQueue( List.of( creature1, creature2 ), List.of( creature3 ) );

        assertEquals( turnQueue.getCurrentCreature(), creature1 );
        turnQueue.next();
        assertEquals( turnQueue.getCurrentCreature(), creature2 );
        turnQueue.next();
        assertEquals( turnQueue.getCurrentCreature(), creature3 );
        turnQueue.next();
        assertEquals( turnQueue.getCurrentCreature(), creature1 );
    }
}