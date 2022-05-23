package pl.psi.spells;

import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map;


public class BuffDebuffSpell extends Spell<Creature> {

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
        aDefender.applyStatsWithSpells(creatureStats);
        spellTimer.put(aDefender, timer);
    }

    private CreatureStats convertToNegative(CreatureStats creatureStats) { // ToDo: find better way to do this if it possible
        return CreatureStats.builder()
                .attack(-creatureStats.getAttack())
                .armor(-creatureStats.getArmor())
                .moveRange(-creatureStats.getMoveRange())
                .build();
    }

    public void unCastSpell(Creature creature){
        creature.applyStatsWithSpells(convertToNegative(creatureStats));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TurnQueue.END_OF_TURN.equals(evt.getPropertyName())) {
            spellTimer.forEach(((creature, currentTimer) -> {
                currentTimer = currentTimer - 1;
                spellTimer.put(creature, currentTimer);
            }));

            spellTimer.forEach(((creature, currentTimer) -> {
                if(currentTimer == 0) unCastSpell(creature);
            }));

            spellTimer.values().removeIf(currentTimer -> (currentTimer == 0));
        }
    }
}
