package pl.psi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.creatures.EconomyCreature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.creatures.EconomyWarMachineFactory;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.HeroStatistics;

import static org.junit.jupiter.api.Assertions.*;

class EconomyEngineTest {

    private EconomyEngine economyEngine;
    private EconomyHero h1;
    private EconomyHero h2;
    private EconomyNecropolisFactory economyNecropolisFactory;
    private EconomyWarMachineFactory economyWarMachineFactory;
    private int startGold;

    @BeforeEach
    void init() {
        h1 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, HeroStatistics.NECROMANCER);
        h2 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, HeroStatistics.NECROMANCER);
        economyEngine = new EconomyEngine(h1, h2);
        economyNecropolisFactory = new EconomyNecropolisFactory();
        economyWarMachineFactory = new EconomyWarMachineFactory();
        startGold = h1.getGold().getPrice();
    }

    @Test
    void shouldChangeActiveHeroAfterPass() {
        assertEquals(h1.getHeroNumber(), economyEngine.getActiveHero().getHeroNumber());
        economyEngine.buy(ProductType.CREATURE, economyNecropolisFactory.create(false, 2, 1));
        economyEngine.pass();
        assertEquals(h2.getHeroNumber(), economyEngine.getActiveHero().getHeroNumber());
    }

    @Test
    void shouldCountRoundCorrectly() {
        assertEquals(1, economyEngine.getRoundNumber());
        economyEngine.buy(ProductType.CREATURE, economyNecropolisFactory.create(false, 2, 1));
        economyEngine.pass();
        assertEquals(2, economyEngine.getRoundNumber());
    }

    @Test
    void shouldBuyCreatureToCorrectHero() {
        economyEngine.buy(ProductType.CREATURE, economyNecropolisFactory.create(false, 1, 1));
        assertEquals(startGold - 60, h1.getGold().getPrice());
        assertEquals(startGold, h2.getGold().getPrice());
        economyEngine.pass();
        economyEngine.buy(ProductType.CREATURE, economyNecropolisFactory.create(false, 2, 1));
        assertEquals(startGold - 60, h1.getGold().getPrice());
        assertEquals(startGold - 100, h2.getGold().getPrice());
    }

    @Test
    void acvtiveHeroCannotPassIfHeDidntBuyAnyCreature() {
        // hero1 cannot pass
        assertThrows(IllegalStateException.class, () -> economyEngine.pass());
        // hero1 bought creature and can pass
        EconomyCreature creature = economyNecropolisFactory.create(false, 2, 1);
        economyEngine.buy(ProductType.CREATURE, creature);
        economyEngine.pass();
        // hero2 cannot pass
        assertThrows(IllegalStateException.class, () -> economyEngine.pass());
        economyEngine.buy(ProductType.CREATURE, creature);
        economyEngine.pass();

    }

    @Test
    void acvtiveHeroCannotPassIfHeDidntBuyCreatureButBuyMachine() {
        assertThrows(IllegalStateException.class, () -> economyEngine.pass());
        economyEngine.buy(ProductType.CREATURE, economyWarMachineFactory.create(2));
        assertThrows(IllegalStateException.class, () -> economyEngine.pass());
        economyEngine.buy(ProductType.CREATURE, economyNecropolisFactory.create(false,1,1));
        assertDoesNotThrow(() -> economyEngine.pass());

    }

}