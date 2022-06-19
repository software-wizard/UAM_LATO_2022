package pl.psi.spells;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.PropertyChangeListener;
import java.util.function.BiConsumer;


@Getter
@Setter
@ToString
public abstract class Spell<T extends SpellableIf>{

    private final SpellTypes category;
    private final SpellNames name;
    private final SpellMagicClass spellMagicClass;
    private final SpellRang rang;
    private final int manaCost;
    private final String imagePath;

    public Spell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, int manaCost) {
        this.category = category;
        this.name = name;
        this.spellMagicClass = spellMagicClass;
        this.rang = rang;
        this.manaCost = manaCost;
        this.imagePath = "/images/spells images/" + name.getValue() + ".png";
    }

    public abstract void castSpell(T aDefender, BiConsumer<String, PropertyChangeListener> consumer);

    public abstract void unCastSpell(T aDefender);

}
