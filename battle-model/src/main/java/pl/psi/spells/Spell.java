package pl.psi.spells;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public abstract class Spell<T> {

    private final SpellTypes category;
    private final SpellNames name;
    private final SpellMagicClass spellMagicClass;
    private final SpellRang rang;
    private final int manaCost;

    public Spell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, int manaCost) {
        this.category = category;
        this.name = name;
        this.spellMagicClass = spellMagicClass;
        this.rang = rang;
        this.manaCost = manaCost;
    }

    public abstract void castSpell(T aDefender);

    public abstract void unCastSpell(T aDefender);

}
