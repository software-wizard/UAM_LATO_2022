package pl.psi.BuyTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.EconomyEngine;
import pl.psi.ProductType;
import pl.psi.hero.EconomyHero;
import pl.psi.skills.EconomySkillFactory;
import pl.psi.skills.SkillLevel;
import pl.psi.skills.SkillType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SkillBuyTest {

    private final EconomySkillFactory factory = new EconomySkillFactory();
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
    void shouldHeroBuySkill() {
        economyEngine.buy(ProductType.SKILL,
                factory.create(SkillType.ARCHERY, SkillLevel.BASIC));
        assertEquals(1, hero1.getSkillsList().size());
        assertEquals(startGold - 100, hero1.getGold().getPrice());
    }

    @Test
    void shouldHeroBuyDifferentTypesOfSkills() {
        economyEngine.buy(ProductType.SKILL,
                factory.create(SkillType.ARCHERY, SkillLevel.BASIC));
        economyEngine.buy(ProductType.SKILL,
                factory.create(SkillType.OFFENCE, SkillLevel.ADVANCED));
        assertEquals(2, hero1.getSkillsList().size());
        assertEquals(startGold - 300, hero1.getGold().getPrice());
    }

    @Test
    void shouldThrowExceptionWhenHeroTryToBuySkillOfTheTypeHeHas() {
        economyEngine.buy(ProductType.SKILL,
                factory.create(SkillType.ARCHERY, SkillLevel.BASIC));
        assertThrows(IllegalStateException.class,
                () -> economyEngine.buy(ProductType.SKILL,
                        factory.create(SkillType.ARCHERY, SkillLevel.ADVANCED)));
    }
}
