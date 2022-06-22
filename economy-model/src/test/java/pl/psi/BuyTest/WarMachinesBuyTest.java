package pl.psi.BuyTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.EconomyEngine;
import pl.psi.ProductType;
import pl.psi.creatures.EconomyWarMachineFactory;
import pl.psi.hero.EconomyHero;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WarMachinesBuyTest {

    private final EconomyWarMachineFactory factory = new EconomyWarMachineFactory();
    private EconomyHero hero1;
    private EconomyEngine economyEngine;
    private EconomyHero hero2;
    private int startGold = 10000;

    @BeforeEach
    void init() {
        hero1 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS);
        hero2 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS);
        economyEngine = new EconomyEngine(hero1, hero2);
    }

    @Test
    void shouldHeroBuyMachine() {
        economyEngine.buy(ProductType.CREATURE,
                factory.create(1));
        assertEquals(1, hero1.getWarMachines().size());
        assertEquals(0, hero1.getCreatureList().size());
        assertEquals(startGold - 2500, hero1.getGold().getPrice());
    }

    @Test
    void shouldHeroBuyDifferentTypesOfMachines() {
        economyEngine.buy(ProductType.CREATURE,
                factory.create(1));
        economyEngine.buy(ProductType.CREATURE,
                factory.create(2));
        assertEquals(2, hero1.getWarMachines().size());
        assertEquals(startGold - 3250, hero1.getGold().getPrice());
    }

    @Test
    void shouldThrowExceptionWhenHeroTryToBuyMachinesOfTheTypeHeHas() {
        economyEngine.buy(ProductType.CREATURE,factory.create(1));
        assertThrows(IllegalStateException.class,
                () -> economyEngine.buy(ProductType.CREATURE, factory.create(1)));
    }
}
