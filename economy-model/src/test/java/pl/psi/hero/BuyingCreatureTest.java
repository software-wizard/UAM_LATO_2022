package pl.psi.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.EconomyEngine;
import pl.psi.ProductType;
import pl.psi.creatures.EconomyNecropolisFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuyingCreatureTest
{

    private final EconomyNecropolisFactory creatureFactory = new EconomyNecropolisFactory();
    private EconomyHero hero1;
    private EconomyEngine economyEngine;
    private EconomyHero hero2;

    @BeforeEach
    void init()
    {
        hero1 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS );
        hero2 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS );
        economyEngine = new EconomyEngine( hero1, hero2 );
    }

    @Test
    void heroShouldCanBuyCreature()
    {
        economyEngine.buy( ProductType.CREATURE,creatureFactory.create( false, 1, 1 ) );

        assertEquals( 3900, hero1.getGold() );
    }

    @Test
    void heroShouldCanBuyMoreThanOneCreatureInOneStack()
    {
        economyEngine.buy( ProductType.CREATURE,creatureFactory.create( false, 1, 2 ) );

        assertEquals( 3800, hero1.getGold() );
    }

    @Test
    void heroShouldCanBuyMoreThanOneCreatureInFewStack()
    {
        economyEngine.buy( ProductType.CREATURE,creatureFactory.create( false, 1, 2 ) );
        economyEngine.buy( ProductType.CREATURE,creatureFactory.create( true, 2, 2 ) );

        assertEquals( 3000, hero1.getGold() );
    }

    @Test
    void heroCannotBuyCreatureWhenHasNotEnoughtGold()
    {
        assertThrows( IllegalStateException.class,
                () -> economyEngine.buy( ProductType.CREATURE,creatureFactory.create( false, 1, 200 ) ) );
        assertEquals( 4000, hero1.getGold() );
        assertEquals( 0, hero1.getCreatures().size() );
    }

    @Test
    void heroCannotBuyCreatureIfHeBought7TypesOfCreatures()
    {
        economyEngine.buy( ProductType.CREATURE,creatureFactory.create( false, 1, 1 ) );
        economyEngine.buy( ProductType.CREATURE,creatureFactory.create( false, 2, 1 ) );
        economyEngine.buy( ProductType.CREATURE,creatureFactory.create( false, 3, 1 ) );
        economyEngine.buy( ProductType.CREATURE,creatureFactory.create( false, 4, 1 ) );
        economyEngine.buy( ProductType.CREATURE,creatureFactory.create( false, 5, 1 ) );
        economyEngine.buy( ProductType.CREATURE,creatureFactory.create( false, 6, 1 ) );
        economyEngine.buy( ProductType.CREATURE,creatureFactory.create( false, 7, 1 ) );
        assertThrows( IllegalStateException.class,
                () -> economyEngine.buy( ProductType.CREATURE,creatureFactory.create( false, 1, 2 ) ) );

        assertEquals( 1200, hero1.getGold() );
        assertEquals( 7, hero1.getCreatures().size() );
    }
}
