package pl.psi;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import pl.psi.creatures.CastleCreatureFactory;

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
            new GameEngine( new Hero( List.of( creatureFactory.create( 1, false, 5 ) ), Collections.emptyList()),
                new Hero( List.of( creatureFactory.create( 1, false, 5 ) ), Collections.emptyList()) );

        gameEngine.attack( new Point( 1, 1 ) );
    }
}
