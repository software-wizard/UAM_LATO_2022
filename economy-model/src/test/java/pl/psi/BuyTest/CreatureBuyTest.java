package pl.psi.BuyTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.EconomyEngine;
import pl.psi.ProductType;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.HeroStatistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreatureBuyTest {

    private final EconomyNecropolisFactory creatureFactory = new EconomyNecropolisFactory();
    private EconomyHero hero1;
    private EconomyEngine economyEngine;
    private EconomyHero hero2;
    private int startGold = 10000;

    @BeforeEach
    void init() {
        hero1 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, HeroStatistics.NECROMANCER);
        hero2 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, HeroStatistics.NECROMANCER);
        economyEngine = new EconomyEngine(hero1, hero2);
    }

    @Test
    void heroCanBuyCreature() {
        economyEngine.buy(ProductType.CREATURE, creatureFactory.create(false, 1, 1));
        assertEquals(startGold - 60, hero1.getGold().getPrice());
        assertEquals(1, hero1.getCreatureList().size());
    }

    @Test
    void heroCanBuyMoreThanOneCreatureInOneStack() {
        economyEngine.buy(ProductType.CREATURE, creatureFactory.create(false, 1, 2));
        assertEquals(startGold - 120, hero1.getGold().getPrice());
        assertEquals(2, hero1.getCreatureList().get(0).getAmount());
        assertEquals(1, hero1.getCreatureList().size());
    }

    @Test
    void heroCanBuyMoreThanOneCreatureInFewStack() {
        economyEngine.buy(ProductType.CREATURE, creatureFactory.create(false, 1, 2));
        economyEngine.buy(ProductType.CREATURE, creatureFactory.create(true, 1, 2));
        assertEquals(startGold - 260, hero1.getGold().getPrice());
        assertEquals(2, hero1.getCreatureList().size());
        assertEquals(2, hero1.getCreatureList().get(0).getAmount());
        assertEquals(2, hero1.getCreatureList().get(1).getAmount());
    }

    @Test
    void heroCannotBuyAnotherTypeOfCreatureIfHeBought7TypesOfCreatures() {
        economyEngine.buy(ProductType.CREATURE, creatureFactory.create(false, 1, 1));
        economyEngine.buy(ProductType.CREATURE, creatureFactory.create(false, 2, 1));
        economyEngine.buy(ProductType.CREATURE, creatureFactory.create(false, 3, 1));
        economyEngine.buy(ProductType.CREATURE, creatureFactory.create(false, 4, 1));
        economyEngine.buy(ProductType.CREATURE, creatureFactory.create(false, 5, 1));
        economyEngine.buy(ProductType.CREATURE, creatureFactory.create(false, 6, 1));
        economyEngine.buy(ProductType.CREATURE, creatureFactory.create(false, 7, 1));

        assertThrows(IllegalStateException.class,
                () -> economyEngine.buy(ProductType.CREATURE, creatureFactory.create(true, 1, 1)));

        assertEquals(startGold - 4270, hero1.getGold().getPrice());
        assertEquals(7, hero1.getCreatureList().size());
    }

}
