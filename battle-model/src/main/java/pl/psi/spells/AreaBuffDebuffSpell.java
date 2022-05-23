package pl.psi.spells;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Optional;

public class AreaBuffDebuffSpell extends Spell<List<Optional<Creature>>> {

    private final CreatureStats creatureStats;

    public AreaBuffDebuffSpell(SpellTypes category, String name, SpellRang rang, int manaCost, CreatureStats creatureStats) {
        super(category, name, rang, manaCost);
        this.creatureStats = creatureStats;
    }

    @Override
    public void castSpell(List<Optional<Creature>> aDefender) {
        aDefender.forEach(
                optionalCreature -> optionalCreature.ifPresent(
                        creature -> creature.setStatsWithSpells(creatureStats)));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
