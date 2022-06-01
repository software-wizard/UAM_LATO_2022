package pl.psi.spells;


import lombok.Getter;
import pl.psi.shop.BuyProductInterface;
import pl.psi.shop.Money;


@Getter
public class EconomySpell implements BuyProductInterface {

    private final SpellStats spellStats;
    private final SpellRang spellRang;
    private final int requiredMagicGuildLevel;
    private final Money goldCost;

    public EconomySpell(SpellStats spellStats, SpellRang spellRang, int requiredMagicGuildLevel, int goldCost) {
        this.spellStats = spellStats;
        this.spellRang = spellRang;
        this.requiredMagicGuildLevel = requiredMagicGuildLevel;
        this.goldCost = new Money(goldCost);
    }
}
