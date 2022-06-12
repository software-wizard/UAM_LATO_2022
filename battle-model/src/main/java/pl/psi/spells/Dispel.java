package pl.psi.spells;

import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.function.BiConsumer;

public class Dispel extends Spell<Creature> {

    public Dispel(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, int manaCost) {
        super(category, name, spellMagicClass, rang, manaCost);
    }

    @Override
    public void castSpell(Creature aDefender, BiConsumer<String, PropertyChangeListener> consumer) {
        Iterator<Spell> spellIterator = aDefender.getRunningSpells().iterator();

        while (spellIterator.hasNext()) {
            Spell spell = spellIterator.next();
            spell.unCastSpell(aDefender);
            spellIterator.remove();
        }
    }

    @Override
    public void unCastSpell(Creature aDefender) {

    }

}