package pl.psi.spells;

import lombok.Getter;

@Getter
public class EconomySpell {

    private final SpellStats spellStats;
    private final SpellRang spellRang;
    private final SpellCostValueObject cost;

    public EconomySpell(SpellStats spellStats, SpellRang spellRang, int cost) {
        this.spellStats = spellStats;
        this.spellRang = spellRang;
        this.cost = new SpellCostValueObject(cost);
    }
}
