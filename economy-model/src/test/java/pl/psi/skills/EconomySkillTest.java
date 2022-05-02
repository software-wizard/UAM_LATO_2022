package pl.psi.skills;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.EconomyEngine;
import pl.psi.hero.EconomyHero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EconomySkillTest {

    private EconomyHero hero1;
    private EconomyEngine economyEngine;
    private EconomyHero hero2;

    private final EconomySkillFactory economySkillFactory = new EconomySkillFactory();

    @BeforeEach
    void init() {
        hero1 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        hero2 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        economyEngine = new EconomyEngine( hero1, hero2 );
    }

    @Test
    public void heroShouldBeAbleToLearnSkill() {
        EconomySkill skillToLearn = economySkillFactory.create();
    }

    @Test
    public void heroDoesNotHaveEnoughMoneyToBuySkill() {
        int moneyBeforeBuyingSkill = hero1.getGold();

    }

    @Test
    public void heroHasLearnedSkill() {
        EconomySkill skillToLearn = economySkillFactory.create();

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
