package pl.psi.creatures;

import pl.psi.shop.Money;

// this factory should delivery group
public class EconomyNecropolisFactory implements FactoryInterface {

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    public EconomyCreature create(final boolean aIsUpgraded, final int aTier, final int aAmount) {
        if (!aIsUpgraded) {
            switch (aTier) {
                case 1:
                    return new EconomyCreature(CreatureStatistic.SKELETON, aAmount, new Money(60));
                case 2:
                    return new EconomyCreature(CreatureStatistic.WALKING_DEAD, aAmount, new Money(100));
                case 3:
                    return new EconomyCreature(CreatureStatistic.WIGHT, aAmount, new Money(200));
                case 4:
                    return new EconomyCreature(CreatureStatistic.VAMPIRE, aAmount, new Money(360));
                case 5:
                    return new EconomyCreature(CreatureStatistic.LICH, aAmount, new Money(550));
                case 6:
                    return new EconomyCreature(CreatureStatistic.BLACK_KNIGHT, aAmount, new Money(1200));
                case 7:
                    return new EconomyCreature(CreatureStatistic.BONE_DRAGON, aAmount, new Money(1800));
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        } else {
            switch (aTier) {
                case 1:
                    return new EconomyCreature(CreatureStatistic.SKELETON_WARRIOR, aAmount, new Money(70));
                case 2:
                    return new EconomyCreature(CreatureStatistic.ZOMBIE, aAmount, new Money(125));
                case 3:
                    return new EconomyCreature(CreatureStatistic.WRAITH, aAmount, new Money(230));
                case 4:
                    return new EconomyCreature(CreatureStatistic.VAMPIRE_LORD, aAmount, new Money(500));
                case 5:
                    return new EconomyCreature(CreatureStatistic.POWER_LICH, aAmount, new Money(600));
                case 6:
                    return new EconomyCreature(CreatureStatistic.DREAD_KNIGHT, aAmount, new Money(1500));
                case 7:
                    return new EconomyCreature(CreatureStatistic.GHOST_DRAGON, aAmount, new Money(3000));
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        }
    }
}