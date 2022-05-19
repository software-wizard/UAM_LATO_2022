package pl.psi.spells;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public abstract class Spell<T> {

    private final SpellTypes category;
    private final String name;
    private final SpellRang rang;
    private final int manaCost;

    public Spell(SpellTypes category, String name, SpellRang rang, int manaCost) {
        this.category = category;
        this.name = name;
        this.rang = rang;
        this.manaCost = manaCost;
    }

    public abstract void castSpell(T aDefender);

}
