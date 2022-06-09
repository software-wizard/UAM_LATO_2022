package pl.psi.spells;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class AreaBuffDebuffSpell extends Spell<List<Creature>> {

    private final CreatureStats creatureStats;

    public AreaBuffDebuffSpell(SpellTypes category, String name, SpellRang rang, int manaCost, CreatureStats creatureStats) {
        super(category, name, rang, manaCost);
        this.creatureStats = creatureStats;
    }

    @Override
    public void castSpell(List<Creature> aDefender) {
        aDefender.forEach(creature -> creature.applyStatsWithSpells(creatureStats));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
