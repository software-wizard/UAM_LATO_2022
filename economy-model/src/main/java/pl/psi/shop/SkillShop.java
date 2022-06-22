package pl.psi.shop;

import pl.psi.hero.EconomyHero;
import pl.psi.skills.EconomySkill;

public class SkillShop extends AbstractShop<EconomySkill> {

    @Override
    public void addToHero(EconomySkill economySkill, EconomyHero hero) {
        if (hero.canAddSkill(economySkill)) {
            hero.substractGold(economySkill.getGoldCost().getPrice());
            hero.addSkill(economySkill);
        } else {
            throw new IllegalStateException("hero cannot buy this spell");
        }
    }
}
