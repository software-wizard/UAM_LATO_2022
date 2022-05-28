package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.CastleCreatureFactory;
import pl.psi.creatures.DamageCalculatorIf;
import pl.psi.creatures.DefaultDamageCalculator;
import pl.psi.creatures.ReducedDamageCalculator;
import pl.psi.hero.HeroStatistics;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
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

        gameEngine.attack(new Point(1, 1));
    }

    @Test
    void gameEngineShouldReturnTrueIfAdjacentCreature() {
        /**
         * whole point of this test is to make sure range creatures can't shoot when other creatures are in melee
         * this is so needlessly complicated but i think it works, will be fixed i promise :(
         */
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(creatureFactory.create(1, false, 5)), HeroStatistics.NECROMANCER),
                        new Hero(List.of(creatureFactory.create(2, true, 5), creatureFactory.create(2, true, 5)), HeroStatistics.NECROMANCER));
        gameEngine.getHero1().getCreatures().get(0).setHeroNumber(1);
        gameEngine.getHero2().getCreatures().get(0).setHeroNumber(2);
        gameEngine.getHero2().getCreatures().get(1).setHeroNumber(2);


        //hero1 turn
        gameEngine.move(new Point( 6,1 ) );
        gameEngine.move(new Point( 7,1 ) );
        assertThat( gameEngine.getHero2().getCreatures().get(0).getCalculator() ).isInstanceOf( DefaultDamageCalculator.class );
        gameEngine.pass();
        //hero2 creature 1 turn
        assertThat( gameEngine.getHero2().getCreatures().get(0).getCalculator() ).isInstanceOf( DefaultDamageCalculator.class );
        gameEngine.pass();
        //hero2 creature 2 turn
        gameEngine.move(new Point( 14,2 ) );
        gameEngine.pass();
        //hero1 turn
        gameEngine.pass();
        //hero2 creature 1 turn
        assertThat( gameEngine.getHero2().getCreatures().get(0).getCalculator() ).isInstanceOf( DefaultDamageCalculator.class );
        gameEngine.move(new Point( 9,1 ) );
        gameEngine.move(new Point( 8,1 ) );
        gameEngine.pass();
        //hero2 creature 2 turn
        gameEngine.pass();
        //hero1 turn
        assertThat( gameEngine.getCreature( new Point(7,1 )).get().isRange() ).isEqualTo(false);
        assertThat( gameEngine.getCreature( new Point( 8,1 )).get().isRange() ).isEqualTo(true);
        gameEngine.pass();
        //hero2 creature 1 turn
        assertThat( gameEngine.getHero2().getCreatures().get(0).getCalculator() ).isInstanceOf( ReducedDamageCalculator.class );
        gameEngine.move( new Point( 10, 5 ) );
        assertThat( gameEngine.getCreature(new Point( 10,5 )).get().isRange() ).isEqualTo( gameEngine.getHero2().getCreatures().get(0).isRange() );
        gameEngine.pass();
        //hero2 creature 2 turn
        gameEngine.pass();
        //hero1 turn
        gameEngine.pass();
        //hero2 creature 1 turn
        assertThat( gameEngine.getHero2().getCreatures().get(0).getCalculator() ).isInstanceOf( DefaultDamageCalculator.class );



    }
}
