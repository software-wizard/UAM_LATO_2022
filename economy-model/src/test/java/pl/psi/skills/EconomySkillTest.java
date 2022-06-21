package pl.psi.skills;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.EconomyEngine;
import pl.psi.hero.EconomyHero;

public class EconomySkillTest {

    private final EconomySkillFactory economySkillFactory = new EconomySkillFactory();
    private EconomyHero hero1;
    private EconomyEngine economyEngine;
    private EconomyHero hero2;

    @BeforeEach
    void init() {
        hero1 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS);
        hero2 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS);
        economyEngine = new EconomyEngine(hero1, hero2);
    }

    @Test
    public void heroShouldBeAbleToLearnSkill() {
        EconomySkill skillToLearn = economySkillFactory.create(SkillType.ARCHERY, SkillLevel.BASIC);
    }

    @Test
    public void heroDoesNotHaveEnoughMoneyToBuySkill() {
        int moneyBeforeBuyingSkill = hero1.getGold().getPrice();

    }

    @Test
    public void heroHasLearnedSkill() {
        EconomySkill skillToLearn = economySkillFactory.create(SkillType.OFFENCE, SkillLevel.BASIC);

    }

    @Test
    public void heroHasMaxAmountOfSkills() {

    }

    @Test
    public void heroCannotLearnSkillBecauseHeHasNoCreaturesWithCorrespondingType() {

    }

    @Test
    public void heroHasUpdatedPreviouslyLearnedSkill() {

    }
}
