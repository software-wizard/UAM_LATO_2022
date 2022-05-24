package pl.psi.creatures;

import pl.psi.shop.ProductPrice;

// this factory should delivery group
public class EconomyNecropolisFactory {

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    public EconomyCreature create(final boolean aIsUpgraded, final int aTier, final int aAmount) {
        if (!aIsUpgraded) {
            switch (aTier) {
                case 1:
                    return new EconomyCreature(CreatureStatistic.SKELETON, aAmount, new ProductPrice(100));
                case 2:
                    return new EconomyCreature(CreatureStatistic.WALKING_DEAD, aAmount, new ProductPrice(200));
                case 3:
                    return new EconomyCreature(CreatureStatistic.WIGHT, aAmount, new ProductPrice(300));
                case 4:
                    return new EconomyCreature(CreatureStatistic.VAMPIRE, aAmount, new ProductPrice(400));
                case 5:
                    return new EconomyCreature(CreatureStatistic.LICH, aAmount, new ProductPrice(500));
                case 6:
                    return new EconomyCreature(CreatureStatistic.BLACK_KNIGHT, aAmount, new ProductPrice(600));
                case 7:
                    return new EconomyCreature(CreatureStatistic.BONE_DRAGON, aAmount, new ProductPrice(700));
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        } else {
            switch (aTier) {
                case 1:
                    return new EconomyCreature(CreatureStatistic.SKELETON_WARRIOR, aAmount, new ProductPrice(200));
                case 2:
                    return new EconomyCreature(CreatureStatistic.ZOMBIE, aAmount, new ProductPrice(400));
                case 3:
                    return new EconomyCreature(CreatureStatistic.WRAITH, aAmount, new ProductPrice(600));
                case 4:
                    return new EconomyCreature(CreatureStatistic.VAMPIRE_LORD, aAmount, new ProductPrice(800));
                case 5:
                    return new EconomyCreature(CreatureStatistic.POWER_LICH, aAmount, new ProductPrice(1000));
                case 6:
                    return new EconomyCreature(CreatureStatistic.DREAD_KNIGHT, aAmount, new ProductPrice(1200));
                case 7:
                    return new EconomyCreature(CreatureStatistic.GHOST_DRAGON, aAmount, new ProductPrice(1400));
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        }
    }
}