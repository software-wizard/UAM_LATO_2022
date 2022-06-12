package pl.psi.spells;

import lombok.Getter;
import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.beans.PropertyChangeListener;
import java.util.function.BiConsumer;


public class BuffDebuffSpell extends Spell<Creature> {

    private final CreatureStats creatureStats;
    private final int time;
    @Getter
    private RoundTimer roundTimer;

    public BuffDebuffSpell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, int manaCost, CreatureStats creatureStats, int time) {
        super(category, name, spellMagicClass, rang, manaCost);
        this.creatureStats = creatureStats;
        this.time = time;
    }

    private BuffDebuffSpell(BuffDebuffSpell buffDebuffSpell, Creature creature) {
        super(buffDebuffSpell.getCategory(), buffDebuffSpell.getName(), buffDebuffSpell.getSpellMagicClass(), buffDebuffSpell.getRang(), buffDebuffSpell.getManaCost());
        this.creatureStats = buffDebuffSpell.creatureStats;
        this.time = buffDebuffSpell.time;
        this.roundTimer = new RoundTimer(buffDebuffSpell.time, this, creature);
    }

    @Override
    public void castSpell(Creature aDefender, BiConsumer<String, PropertyChangeListener> consumer) {
        if (aDefender.getRunningSpells().size() < 3) aDefender.addRunningSpell(this);
        else return;
        BuffDebuffSpell buffDebuffSpell = new BuffDebuffSpell(this, aDefender);
        consumer.accept(TurnQueue.END_OF_TURN, buffDebuffSpell.getRoundTimer());
        aDefender.applyStatsWithSpells(creatureStats);
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
