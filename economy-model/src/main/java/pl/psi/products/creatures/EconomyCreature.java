package pl.psi.products.creatures;

//import pl.psi.creatures.CreatureStatistic;

import pl.psi.products.BuyProductInterface;

public class EconomyCreature implements BuyProductInterface
{

    private final CreatureStatistic stats;
    private int amount;
    private final int goldCost;

    EconomyCreature( final CreatureStatistic aStats, final int aAmount, final int aGoldCost )
    {
        stats = aStats;
        amount = aAmount;
        goldCost = aGoldCost;
    }

    public int getAmount()
    {
        return amount;
    }

    public void increaseAmount(int aAmount){
        amount = amount + aAmount;
    }

    public int getGoldCost()
    {
        return goldCost;
    }

    public String getName()
    {
        return stats.getTranslatedName();
    }

    public boolean isUpgraded()
    {
        return stats.isUpgraded();
    }

    public int getTier()
    {
        return stats.getTier();
    }


}
