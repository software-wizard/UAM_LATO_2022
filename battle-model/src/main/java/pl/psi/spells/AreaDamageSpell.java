package pl.psi.spells;

import pl.psi.creatures.Creature;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Optional;

public class AreaDamageSpell extends Spell<List<Creature>> {

    private final int value;

    public AreaDamageSpell(SpellTypes category, String name, SpellRang rang, int manaCost, int value) {
        super(category, name, rang, manaCost);
        this.value = value;
    }

    @Override
    public void castSpell(List<Creature> aDefender) {
        aDefender.forEach(creature -> creature.applySpellDamage(creature, value));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
