package pl.psi.spells;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.psi.creatures.Creature;

import java.util.List;
import java.util.Optional;


@Getter
@Setter
@ToString
public abstract class Spell<T> {

    private final SpellsCategories category;
    private final String name;
    private final int rang;
    private final int manaCost;
    private final int multiplier;
    private T value;

    public Spell(SpellsCategories category, String name, int rang, int manaCost, int multiplier, T value) {
        this.category = category;
        this.name = name;
        this.rang = rang;
        this.manaCost = manaCost;
        this.multiplier = multiplier;
        this.value = value;
    }

    public abstract void castSpell(final Creature aDefender);

    public abstract void castSpell(final List<Optional<Creature>> aDefender);
}
