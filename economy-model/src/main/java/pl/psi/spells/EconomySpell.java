package pl.psi.spells;

import lombok.Getter;
import pl.psi.shop.Money;

@Getter
public class EconomySpell {

    private final SpellStats spellStats;
    private final SpellRang spellRang;
    private final Money cost;

    public EconomySpell(SpellStats spellStats, SpellRang spellRang, int cost) {
        this.spellStats = spellStats;
        this.spellRang = spellRang;
        this.cost = new Money(cost);
    }
}
