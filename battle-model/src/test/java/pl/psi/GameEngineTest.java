package pl.psi;

import java.util.List;

import org.junit.jupiter.api.Test;

import pl.psi.creatures.CastleCreatureFactory;
import pl.psi.hero.HeroStatistics;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngineTest
{
    @Test
    void shoudWorksHeHe()
    {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
            new GameEngine( new Hero( List.of( creatureFactory.create( 1, false, 5 ) ), HeroStatistics.DEATH_KNIGHT ),
                new Hero( List.of( creatureFactory.create( 1, false, 5 ) ), HeroStatistics.NECROMANCER) );

        gameEngine.attack( new Point( 1, 1 ) );
    }
}
