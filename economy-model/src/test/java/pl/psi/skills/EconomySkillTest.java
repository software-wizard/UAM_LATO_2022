package pl.psi.skills;

import org.junit.jupiter.api.Test;
import pl.psi.hero.EconomyHero;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EconomySkillTest {

    private final EconomySkillFactory economySkillFactory = new EconomySkillFactory();
    private final EconomyHero aHero = new EconomyHero(EconomyHero.Fraction.NECROPOLIS);

    @Test
    void heroHasUpdatedLuck() {
        EconomySkill skillToApply = this.economySkillFactory.create(SkillType.LUCK, SkillLevel.BASIC);
        skillToApply.apply(aHero);
        assertEquals(aHero.getHeroStats().getLuck(), 1);
    }


}
