package pl.psi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.psi.spells.Spell;
import pl.psi.spells.SpellableIf;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class SpellsBook {

    private final List<? extends Spell<? extends SpellableIf>> spells;
    private boolean isHeroCastingSpell = false;
    private boolean isHeroCastedSpell = false;
    private int mana;

    public SpellsBook(List<? extends Spell<? extends SpellableIf>> spells, int mana) {
        this.spells = spells;
        this.mana = mana;
    }


}
