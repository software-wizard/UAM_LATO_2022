package pl.psi.skill;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.EconomyEngine;
import pl.psi.hero.EconomyHero;
import pl.psi.skills.EconomySkill;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EconomySkillTest {

    private EconomyHero hero1;
    private EconomyEngine economyEngine;
    private EconomyHero hero2;

    private final EconomySkillFactory economySkillFactory = new EconomySkillFacotory();

    @BeforeEach
    void init() {
        hero1 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        hero2 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        economyEngine = new EconomyEngine( hero1, hero2 );
    }

    @Test
    public void heroShouldBeAbleToLearnSkill() {
        EconomySkill skillToLearn = economySkillFactory.create();
        // TODO find out how it should be checked if hero can learn a new secondary skill
        economyEngine.learnSkill( skillToLearn );
    }

    @Test
    public void heroDoesNotHaveEnoughMoneyToBuySkill() {

    }

    @Test
    public void heroHasLearnedSkill() {
        EconomySkill skillToLearn = economySkillFactory.create();
        economyEngine.learnSkill( skillToLearn );
        assertEquals(skillToLearn, hero1.getSkills(hero1.getSkills().size() - 1)); // new skill is the last element on
        // the skills list
    }

    @Test
    public void heroHasMaxAmountOfSkills() {

    }

    // TODO it is business decision and it may not have any sense
    @Test
    public void heroCannotLearnSkillBecauseHeHasNoCreaturesWithCorrespondingType() {

    }

    @Test
    public void heroHasUpdatedPreviouslyLearnedSkill() {

    }
}
