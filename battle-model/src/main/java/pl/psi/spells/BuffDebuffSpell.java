package pl.psi.spells;

import lombok.Getter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;


public class BuffDebuffSpell extends Spell<Creature> {

    private final CreatureStats creatureStats;
    private final int time;
    @Getter
    private RoundTimer roundTimer;

    public BuffDebuffSpell(SpellTypes category, String name, SpellRang rang, int manaCost, CreatureStats creatureStats, int time) {
        super(category, name, rang, manaCost);
        this.creatureStats = creatureStats;
        this.time = time;
    }

    public BuffDebuffSpell(BuffDebuffSpell buffDebuffSpell, Creature creature) {
        super(buffDebuffSpell.getCategory(), buffDebuffSpell.getName(), buffDebuffSpell.getRang(), buffDebuffSpell.getManaCost());
        this.creatureStats = buffDebuffSpell.creatureStats;
        this.time = buffDebuffSpell.time;
        this.roundTimer = new RoundTimer(buffDebuffSpell.time, this, creature);
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

    public void unCastSpell(Creature creature) {
        creature.applyStatsWithSpells(convertToNegative(creatureStats));
    }
}
