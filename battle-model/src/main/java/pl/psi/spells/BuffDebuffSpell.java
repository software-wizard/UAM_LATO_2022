package pl.psi.spells;

import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;


public class BuffDebuffSpell extends Spell<Creature> implements PropertyChangeListener {

    private final CreatureStats creatureStats;
    private final int timer;
    private final Map<Creature, Integer> spellTimer = new HashMap<>();

    public BuffDebuffSpell(SpellTypes category, String name, SpellRang rang, int manaCost, CreatureStats creatureStats, int timer) {
        super(category, name, rang, manaCost);
        this.creatureStats = creatureStats;
        this.timer = timer;
    }

    @Override
    public void castSpell(Creature aDefender) {
        aDefender.buff(creatureStats);
        spellTimer.put(aDefender, timer);
    }

    private CreatureStats convertToNegative(CreatureStats creatureStats) { // ToDo: find better way to do this if it possible
        return CreatureStats.builder()
                .attack(-creatureStats.getAttack())
                .armor(-creatureStats.getArmor())
                .moveRange(-creatureStats.getMoveRange())
                .build();
    }

    @Override
    public void unCastSpell(Creature creature) {
        creature.applyStatsWithSpells(convertToNegative(creatureStats));
    }
}
