package pl.psi.spells;

import lombok.Getter;
import pl.psi.shop.BuyProductInterface;
import pl.psi.shop.Money;

@Getter
public class EconomySpell implements BuyProductInterface {

    private final SpellStats spellStats;
    private SpellRang spellRang;
    private final Money goldCost;

    public EconomySpell(SpellStats spellStats, SpellRang spellRang, int goldCost) {
        this.spellStats = spellStats;
        this.spellRang = spellRang;
        this.goldCost = new Money(goldCost);
    }
    public void upgradeSpell(SpellRang aNewSpellRang) {
        this.spellRang = aNewSpellRang;
    }
}
