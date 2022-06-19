package pl.psi.spells;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EconomySpell {

    private final SpellStats spellStats;
    private SpellRang spellRang;
    private final int requiredMagicGuildLevel;
    private final int goldCost;

    public void upgradeSpell(SpellRang aNewSpellRang) {
        this.spellRang = aNewSpellRang;
    }
}
