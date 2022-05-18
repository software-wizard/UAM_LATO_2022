package pl.psi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.psi.creatures.EconomyCreature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.hero.EconomyHero;

class EconomyEngineTest
{

    private EconomyEngine economyEngine;
    private EconomyHero h1;
    private EconomyHero h2;
    private EconomyNecropolisFactory creatureFactory;

    @BeforeEach
    void init()
    {
        h1 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS );
        h2 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS );
        economyEngine = new EconomyEngine( h1, h2 );
        creatureFactory = new EconomyNecropolisFactory();
    }

    @Test
    void shouldChangeActiveHeroAfterPass()
    {
        assertEquals( h1.getHeroNumber(), economyEngine.getActiveHero().getHeroNumber() );
        economyEngine.buy(ProductType.CREATURE,creatureFactory.create( false, 2, 1 ) );
        economyEngine.pass();
        assertEquals( h2.getHeroNumber(), economyEngine.getActiveHero().getHeroNumber() );
    }

    @Test
    void shouldCountRoundCorrectly()
    {
        assertEquals( 1, economyEngine.getRoundNumber() );
        economyEngine.buy(ProductType.CREATURE,creatureFactory.create( false, 2, 1 ) );
        economyEngine.pass();
        assertEquals( 2, economyEngine.getRoundNumber() );
    }

    @Test
    void shouldBuyCreatureToCorrectHero()
    {
        economyEngine.buy( ProductType.CREATURE,creatureFactory.create( false, 1, 1 ) );
        assertEquals( 3900, h1.getGold() );
        assertEquals( 4000, h2.getGold() );
        economyEngine.pass();
        economyEngine.buy( ProductType.CREATURE,creatureFactory.create( false, 2, 1 ) );
        assertEquals( 3900, h1.getGold() );
        assertEquals( 3800, h2.getGold() );
    }

    @Test
    void shouldEndGameAfter2Rounds(){
        assertEquals( 1, economyEngine.getRoundNumber() );
        economyEngine.buy(ProductType.CREATURE,creatureFactory.create( false, 2, 1 ) );
        economyEngine.pass();
        assertEquals( 2, economyEngine.getRoundNumber() );
        economyEngine.buy(ProductType.CREATURE,creatureFactory.create( false, 2, 1 ) );
        economyEngine.pass();
        assertEquals(2 ,economyEngine.getRoundNumber() );
        assertEquals(true ,economyEngine.isEnd() );

    }

    @Test
    void acvtiveHeroCannotPassIfHeDidntBuyAnyCreature(){
        // hero1 cannot pass
        assertThrows( IllegalStateException.class, () -> economyEngine.pass() );
        // hero1 bought creature and can pass
        EconomyCreature creature = creatureFactory.create(false,2,1);
        economyEngine.buy(ProductType.CREATURE,creature);
        economyEngine.pass();
        // hero2 cannot pass
        assertThrows( IllegalStateException.class, () -> economyEngine.pass() );
        economyEngine.buy(ProductType.CREATURE,creature);
        economyEngine.pass();
        // end of the game
        assertEquals(true,economyEngine.isEnd());

    }

}