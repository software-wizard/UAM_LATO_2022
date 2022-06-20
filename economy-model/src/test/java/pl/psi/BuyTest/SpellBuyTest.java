package pl.psi.BuyTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.EconomyEngine;
import pl.psi.ProductType;
import pl.psi.hero.EconomyHero;
import pl.psi.spells.EconomySpellFactory;
import pl.psi.spells.SpellRang;
import pl.psi.spells.SpellStats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpellBuyTest {

    private final EconomySpellFactory factory = new EconomySpellFactory();
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
    void shouldHeroBuySpell() {
        economyEngine.buy(ProductType.SPELL,
                factory.create(SpellStats.MAGIC_ARROW, SpellRang.BASIC));
        assertEquals(1, hero1.getSpellList().size());
        assertEquals(startGold - 100, hero1.getGold().getPrice());
    }

    @Test
    void shouldHeroBuyDifferentTypesOfSpells() {
        economyEngine.buy(ProductType.SPELL,
                factory.create(SpellStats.MAGIC_ARROW, SpellRang.BASIC));
        economyEngine.buy(ProductType.SPELL,
                factory.create(SpellStats.FIRE_BALL, SpellRang.BASIC));
        assertEquals(2, hero1.getSpellsList().size());
        assertEquals(startGold - 205, hero1.getGold().getPrice());
    }

    @Test
    void shouldThrowExceptionWhenHeroTryToBuySpellOfTheTypeHeHas() {
        economyEngine.buy(ProductType.SPELL,
                factory.create(SpellStats.MAGIC_ARROW, SpellRang.BASIC));
        assertThrows(IllegalStateException.class,
                () -> economyEngine.buy(ProductType.SPELL,
                        factory.create(SpellStats.MAGIC_ARROW, SpellRang.ADVANCED)));
    }
}
