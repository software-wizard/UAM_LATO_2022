package pl.psi.shop;

import pl.psi.hero.EconomyHero;

public abstract class AbstractShop <T extends BuyProductInterface> {

    public abstract void addToHero(T t, EconomyHero hero);
}
