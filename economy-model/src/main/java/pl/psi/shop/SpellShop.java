package pl.psi.shop;

import pl.psi.hero.EconomyHero;
import pl.psi.skills.EconomySkill;
import pl.psi.spells.EconomySpell;

public class SpellShop extends AbstractShop<EconomySpell> {

    @Override
    public void addToHero(EconomySpell economySpell, EconomyHero hero) {
        if (hero.canAddSpell(economySpell)) {
            hero.substractGold(economySpell.getGoldCost().getPrice());
            hero.addSpell(economySpell);
        } else {
            throw new IllegalStateException("hero cannot buy this skill");
        }
    }
}