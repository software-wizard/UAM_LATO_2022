package pl.psi.spells;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;


public class BuffDebuffSpell extends Spell<Creature> {

    private final CreatureStats creatureStats;

    public BuffDebuffSpell(SpellsCategories category, String name, SpellRang rang, int manaCost, CreatureStats creatureStats) {
        super(category, name, rang, manaCost);
        this.creatureStats = creatureStats;
    }


    @Override
    public void castSpell(Creature aDefender) {
        aDefender.setStatsWithSpells(creatureStats);
    }
}
