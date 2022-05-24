package pl.psi.creatures;

import pl.psi.shop.BuyProductInterface;
import pl.psi.shop.Money;

public class EconomyCreature implements BuyProductInterface
{

    private final CreatureStatistic stats;
    private int amount;
    private final Money goldCost;

    public EconomyCreature( final CreatureStatistic aStats, final int aAmount, final Money aGoldCost )
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

    public Money getGoldCost()
    {
        return goldCost;
    }

    public CreatureStatistic getStats() {
        return stats;
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
