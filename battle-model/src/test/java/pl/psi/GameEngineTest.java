package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.CastleCreatureFactory;
import pl.psi.hero.HeroStatistics;

import java.util.List;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngineTest {
    @Test
    void shoudWorksHeHe() {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(creatureFactory.create(1, false, 5)), HeroStatistics.NECROMANCER),
                        new Hero(List.of(creatureFactory.create(1, false, 5)), HeroStatistics.NECROMANCER));

        gameEngine.attack(new Point(1, 1));
    }
}
