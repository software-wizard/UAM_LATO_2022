package pl.psi.creatures;

import pl.psi.shop.BuyProductInterface;
import pl.psi.shop.ProductPrice;

public class EconomyCreature implements BuyProductInterface
{

    private final CreatureStatistic stats;
    private int amount;
    private ProductPrice goldCost;

    EconomyCreature( final CreatureStatistic aStats, final int aAmount, final ProductPrice aGoldCost )
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

    public ProductPrice getGoldCost()
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


    public Object clone()  {
        EconomyCreature aClone = new EconomyCreature(this.stats,this.amount,this.goldCost);
        return aClone;
    }
}
