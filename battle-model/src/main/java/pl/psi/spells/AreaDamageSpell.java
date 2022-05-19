package pl.psi.spells;

import pl.psi.creatures.Creature;

import java.util.List;
import java.util.Optional;

public class AreaDamageSpell extends Spell<List<Optional<Creature>>> {

    private final int value;

    public AreaDamageSpell(SpellsCategories category, String name, SpellRang rang, int manaCost, int value) {
        super(category, name, rang, manaCost);
        this.value = value;
    }

    @Override
    public void castSpell(List<Optional<Creature>> aDefender) {
        aDefender.forEach(
                optionalCreature -> optionalCreature.ifPresent(
                        creature -> creature.applySpellDamage(creature, value)));
    }
}
