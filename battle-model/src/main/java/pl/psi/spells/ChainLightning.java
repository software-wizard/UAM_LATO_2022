package pl.psi.spells;

import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;
import java.util.function.BiConsumer;

public class ChainLightning extends Spell<SpellCreatureList> {

    private double value;

    public ChainLightning(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, SpellAlignment spellAlignment, int manaCost, double value) {
        super(category, name, spellMagicClass, rang, spellAlignment, manaCost);
        this.value = value;
    }

    @Override
    public void castSpell(SpellCreatureList creatureList, BiConsumer<String, PropertyChangeListener> consumer) {
        for(Creature creature: creatureList.getCreatureList()){
            creature.applySpellDamage(value);
            value = value / 2;
        }
    }

    @Override
    public void unCastSpell(SpellCreatureList aDefender) {

    }
}
