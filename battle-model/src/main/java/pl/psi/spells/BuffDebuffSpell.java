package pl.psi.spells;

import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.beans.PropertyChangeEvent;


public class BuffDebuffSpell extends Spell<Creature> {

    private final CreatureStats creatureStats;
    private final int timer;
    private int currentTimer;

    public BuffDebuffSpell(SpellTypes category, String name, SpellRang rang, int manaCost, CreatureStats creatureStats, int timer) {
        super(category, name, rang, manaCost);
        this.creatureStats = creatureStats;
        this.timer = timer;
        this.currentTimer = -1;
    }

    @Override
    public void castSpell(Creature aDefender) {
        currentTimer = timer;
        aDefender.setStatsWithSpells(creatureStats);
    } // ToDo: change set stats to apply stats ( sum stats)


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (currentTimer == -1) return;
        if (currentTimer == 0) {
            currentTimer = currentTimer - 1;
            return;//ToDo: find better way to stop observer
        }
        if (TurnQueue.END_OF_TURN.equals(evt.getPropertyName())) {
            currentTimer = currentTimer - 1;
        }
    }
}
