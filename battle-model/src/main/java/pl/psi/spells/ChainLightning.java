package pl.psi.spells;

import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;
import java.util.function.BiConsumer;

public class ChainLightning extends Spell<SpellCreatureList> {

    private final int value;

    public ChainLightning(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, int manaCost, Integer value) {
        super(category, name, spellMagicClass, rang, manaCost);
        this.value = value;
    }

    @Override
    public void castSpell(SpellCreatureList creatureList, BiConsumer<String, PropertyChangeListener> consumer) {
        creatureList.getCreatureList().forEach(creature -> creature.applySpellDamage(creature, value));
    }

    @Override
    public void unCastSpell(SpellCreatureList aDefender) {

    }
}
