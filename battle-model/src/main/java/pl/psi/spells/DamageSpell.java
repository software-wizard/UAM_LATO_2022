package pl.psi.spells;

import pl.psi.creatures.Creature;

import java.util.List;
import java.util.Optional;

public class DamageSpell extends Spell<Creature> {

    private final Integer value;

    public DamageSpell(SpellsCategories category, String name, SpellRang rang, int manaCost, Integer value) {
        super(category, name, rang, manaCost);
        this.value = value;
    }

    @Override
    public void castSpell(Creature aDefender) {
        aDefender.applySpellDamage(aDefender, value);
    }

}
