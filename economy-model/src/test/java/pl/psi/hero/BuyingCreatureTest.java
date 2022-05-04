package pl.psi.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.psi.EconomyEngine;
import pl.psi.products.Products;
import pl.psi.products.creatures.EconomyNecropolisFactory;

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
        economyEngine.buy( Products.CREATURE,creatureFactory.create( false, 1, 1 ) );

        assertEquals( 3900, hero1.getGold() );
    }

    @Test
    void heroShouldCanBuyMoreThanOneCreatureInOneStack()
    {
        economyEngine.buy( Products.CREATURE,creatureFactory.create( false, 1, 2 ) );

        assertEquals( 3800, hero1.getGold() );
    }

    @Test
    void heroShouldCanBuyMoreThanOneCreatureInFewStack()
    {
        economyEngine.buy( Products.CREATURE,creatureFactory.create( false, 1, 2 ) );
        economyEngine.buy( Products.CREATURE,creatureFactory.create( true, 2, 2 ) );

        assertEquals( 3000, hero1.getGold() );
    }

    @Test
    void heroCannotBuyCreatureWhenHasNotEnoughtGold()
    {
        assertThrows( IllegalStateException.class,
                () -> economyEngine.buy( Products.CREATURE,creatureFactory.create( false, 1, 200 ) ) );
        assertEquals( 4000, hero1.getGold() );
        assertEquals( 0, hero1.getCreatures().size() );
    }

}
