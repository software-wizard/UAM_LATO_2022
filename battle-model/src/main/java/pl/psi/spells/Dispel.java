package pl.psi.spells;

import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

public class Dispel extends Spell<Creature> {

    private final List<SpellAlignment> acceptableAlignments;

    public Dispel(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, SpellAlignment spellAlignment, int manaCost, List<SpellAlignment> acceptableAlignments) {
        super(category, name, spellMagicClass, rang, spellAlignment, manaCost);
        this.acceptableAlignments = acceptableAlignments;
    }

    @Override
    public void castSpell(Creature aDefender, BiConsumer<String, PropertyChangeListener> consumer) {
        Iterator<Spell> spellIterator = aDefender.getRunningSpells().iterator();

        while (spellIterator.hasNext()) {
            Spell spell = spellIterator.next();
            if(acceptableAlignments.contains(spell.getSpellAlignment())){
                spell.unCastSpell(aDefender);
                spellIterator.remove();
            }
        }
    }

    @Override
    public void unCastSpell(Creature aDefender) {

    }

}