package pl.psi.creatures;

import pl.psi.shop.Money;

public class EconomyWarMachineFactory {

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    public EconomyCreature create(final int aTier) {
        switch (aTier) {
            case 1:
                return new EconomyCreature(WarMachinesStatistic.BALLISTA, 1, new Money(2500));
            case 2:
                return new EconomyCreature(WarMachinesStatistic.FIRST_AID_TENT, 1, new Money(750));
            case 4:
                return new EconomyCreature(WarMachinesStatistic.AMMO_CART, 1, new Money(300));
            default:
                throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }
}
