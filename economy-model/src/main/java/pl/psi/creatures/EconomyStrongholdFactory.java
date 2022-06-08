package pl.psi.creatures;

import pl.psi.shop.Money;

public class EconomyStrongholdFactory implements FactoryInterface  {

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    public EconomyCreature create(final boolean aIsUpgraded, final int aTier, final int aAmount) {
        if (!aIsUpgraded) {
            switch (aTier) {
                case 1:
                    return new EconomyCreature(CreatureStatistic.GOBLIN, aAmount, new Money(40));
                case 2:
                    return new EconomyCreature(CreatureStatistic.WOLF_RIDER, aAmount, new Money(100));
                case 3:
                    return new EconomyCreature(CreatureStatistic.ORC, aAmount, new Money(150));
                case 4:
                    return new EconomyCreature(CreatureStatistic.OGRE, aAmount, new Money(300));
                case 5:
                    return new EconomyCreature(CreatureStatistic.ROC, aAmount, new Money(600));
                case 6:
                    return new EconomyCreature(CreatureStatistic.CYCLOPS, aAmount, new Money(750));
                case 7:
                    return new EconomyCreature(CreatureStatistic.BEHEMOTH, aAmount, new Money(1500));
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        } else {
            switch (aTier) {
                case 1:
                    return new EconomyCreature(CreatureStatistic.HOBGOBLIN, aAmount, new Money(50));
                case 2:
                    return new EconomyCreature(CreatureStatistic.WOLF_RAIDER, aAmount, new Money(140));
                case 3:
                    return new EconomyCreature(CreatureStatistic.ORC_CHIEFTAIN, aAmount, new Money(165));
                case 4:
                    return new EconomyCreature(CreatureStatistic.OGRE_MAGI, aAmount, new Money(400));
                case 5:
                    return new EconomyCreature(CreatureStatistic.THUNDERBIRD, aAmount, new Money(700));
                case 6:
                    return new EconomyCreature(CreatureStatistic.CYCLOPS_KING, aAmount, new Money(1100));
                case 7:
                    return new EconomyCreature(CreatureStatistic.ANCIENT_BEHEMOTH, aAmount, new Money(3000));
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        }
    }
}
