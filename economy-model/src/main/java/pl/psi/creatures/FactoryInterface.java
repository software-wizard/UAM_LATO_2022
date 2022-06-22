package pl.psi.creatures;

import pl.psi.shop.BuyProductInterface;

public interface FactoryInterface {

    BuyProductInterface create(final boolean aIsUpgraded, final int aTier, final int aAmount);
}
