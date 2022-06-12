package pl.psi.spells;

import lombok.Getter;

import java.beans.PropertyChangeListener;
import java.util.function.BiConsumer;

public class AreaDamageSpell extends Spell<SpellCreatureList> {

    @Getter
    private final boolean[][] area;
    private final int value;

    public AreaDamageSpell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, int manaCost, boolean[][] area, int value) {
        super(category, name, spellMagicClass, rang, manaCost);
        this.area = area;
        this.value = value;
    }

    @Override
    public void castSpell(SpellCreatureList creatureList, BiConsumer<String, PropertyChangeListener> consumer) {
        creatureList.getCreatureList().forEach(creature -> creature.applySpellDamage(creature, value));
    }

    @Override
    public void unCastSpell(SpellCreatureList creatureList) {

    }
}
