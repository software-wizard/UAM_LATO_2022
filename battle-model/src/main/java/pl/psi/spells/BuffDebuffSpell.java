package pl.psi.spells;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;
import java.util.Optional;


public class BuffDebuffSpell extends Spell<CreatureStats> {

    public BuffDebuffSpell(SpellsCategories category, String name, int rang, int manaCost, int multiplier, CreatureStats value) {
        super(category, name, rang, manaCost, multiplier, value);
    }

    @Override
    public void castSpell(Creature aDefender) {
        aDefender.setStatsWithSpells(getValue());
    }

    @Override
    public void castSpell(List<Optional<Creature>> aDefender) {

    }
}
