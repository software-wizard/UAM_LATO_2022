package pl.psi.shop;

import pl.psi.creatures.EconomyCreature;
import pl.psi.hero.EconomyHero;

public class CreatureShop extends AbstractShop<EconomyCreature> {

    @Override
    public void addToHero(EconomyCreature economyCreature, EconomyHero hero) {
        if (hero.canAddCreature(economyCreature)) {
            hero.substractGold(economyCreature.getGoldCost() * economyCreature.getAmount());
            hero.addCreature(economyCreature);
        } else {
            throw new IllegalStateException("hero cannot consume more creature");
        }
    }


}
