package pl.psi.spells;

import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;
import java.util.function.BiConsumer;

public class SpawningCreatureSpell extends Spell<Creature> {

    public SpawningCreatureSpell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, int manaCost) {
        super(category, name, spellMagicClass, rang, manaCost);
    }

    @Override
    public void castSpell(Creature aDefender, BiConsumer<String, PropertyChangeListener> consumer) {
        consumer.accept("Earth Elemental", null);
    }

    @Override
    public void unCastSpell(Creature aDefender) {

    }
}
