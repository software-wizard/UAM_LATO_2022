package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.CastleCreatureFactory;
import pl.psi.hero.HeroStatistics;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngineTest {
    @Test
    void shouldWorksHeHe() {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(creatureFactory.create(1, false, 5)), HeroStatistics.NECROMANCER),
                        new Hero(List.of(creatureFactory.create(1, false, 5)), HeroStatistics.NECROMANCER));

        gameEngine.attack(new Point(14, 1));
    }

    @Test
    void splashDamageTest(){
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(creatureFactory.create(2, false, 5)), HeroStatistics.NECROMANCER),
                        new Hero(List.of(creatureFactory.create(1, false, 5)), HeroStatistics.NECROMANCER));

        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(2,3));

        assertThat(gameEngine.getCurrentCreatureSplashDamagePointsList(new Point(2,2)).get(1)).isEqualTo(pointList.get(0));

        gameEngine.pass();

        assertThat(gameEngine.getCurrentCreatureSplashDamagePointsList(new Point(2,2)).get(0)).isEqualTo(new Point(2,2));
    }


}

