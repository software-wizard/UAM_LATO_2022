package pl.psi;

import lombok.Getter;
import lombok.Setter;
import pl.psi.spells.Spell;
import pl.psi.spells.SpellableIf;

import java.util.List;

@Setter
@Getter
public class SpellBook {

    private final List<? extends Spell<? extends SpellableIf>> spells;
    private boolean isHeroCastingSpell = false;
    private boolean isHeroCastedSpell = false;

    public SpellBook(List<? extends Spell<? extends SpellableIf>> spells) {
        this.spells = spells;
    }
}
