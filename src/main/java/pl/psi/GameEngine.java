package pl.psi;

import java.awt.*;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngine
{

    private final TurnQueue turnQueue;

    public GameEngine( final Hero aHero1, final Hero aHero2 )
    {
        turnQueue = new TurnQueue( aHero1.getCreatures(), aHero2.getCreatures() );
    }

    void attack( final Point aPointToAttack)
    {
        turnQueue.getCurrentCreature()
            .attack( aDefender );
    }
}
