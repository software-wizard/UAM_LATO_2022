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

        gameEngine.attack(new Point(14, 1));
    }

    /*@Test
    void gameEngineShouldReturnTrueIfAdjacentCreature() {
        /**
         * whole point of this test is to make sure range creatures can't shoot when other creatures are in melee
         * this is so needlessly complicated but i think it works, will be fixed i promise :(

        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(creatureFactory.create(1, false, 5)), HeroStatistics.NECROMANCER),
                        new Hero(List.of(creatureFactory.create(2, true, 5), creatureFactory.create(2, true, 5)), HeroStatistics.NECROMANCER));
        gameEngine.getHero1().getCreatures().get(0).setHeroNumber(1);
        gameEngine.getHero2().getCreatures().get(0).setHeroNumber(2);
        gameEngine.getHero2().getCreatures().get(1).setHeroNumber(2);

        //hero1 turn, creature moves to the center
        gameEngine.move( new Point( 4,1 ) );
        gameEngine.move( new Point( 7,1 ) );

        gameEngine.pass();
        //hero2 creature 1 turn

        gameEngine.pass();
        //hero2 creature 2 turn, second shooter creature moves next to the first
        gameEngine.move( new Point( 14,2 ) );
        //two shooter creatures from the same hero don't collide
        assertThat( gameEngine.getHero2().getCreatures().get(0).getCalculator() ).isInstanceOf( DefaultDamageCalculator.class );
        assertThat( gameEngine.getHero2().getCreatures().get(1).getCalculator() ).isInstanceOf( DefaultDamageCalculator.class );

        gameEngine.pass();
        //hero1 turn

        gameEngine.pass();
        //hero2 creature 1 turn, first shooter creature moves near enemy
        gameEngine.move( new Point( 9,1 ) );
        gameEngine.move( new Point( 8,1 ) );
        //while near enemy, isInMelee == true so damage calculator changes
        assertThat( gameEngine.getHero2().getCreatures().get(0).getCalculator() ).isInstanceOf( ReducedDamageCalculator.class );

        gameEngine.move( new Point( 10, 5 ) );
        //when shooter creature moves away from enemy damage calculator goes back to default
        assertThat( gameEngine.getHero2().getCreatures().get(0).getCalculator() ).isInstanceOf( DefaultDamageCalculator.class );
    }

    @Test
    void defenceShouldWorkProperly() {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(creatureFactory.create(1, false, 5)), HeroStatistics.NECROMANCER),
                        new Hero(List.of(creatureFactory.create(1, false, 5)), HeroStatistics.NECROMANCER));

        gameEngine.defendAction();
        assertThat( gameEngine.getCurrentCreature().isDefending() ).isEqualTo( true );

        gameEngine.move( new Point(2,2) );
        assertThat( gameEngine.getCurrentCreature().isDefending() ).isEqualTo( false );

        gameEngine.defendAction();

        gameEngine.attack( new Point(2,3) );
        assertThat( gameEngine.getCurrentCreature().isDefending() ).isEqualTo( false );
    }*/
}