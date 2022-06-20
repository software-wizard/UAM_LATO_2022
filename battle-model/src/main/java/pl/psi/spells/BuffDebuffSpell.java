package pl.psi.spells;

import lombok.Getter;
import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;


public class BuffDebuffSpell extends Spell<Creature> {

    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private final CreatureStats creatureStats;
    private final int time;
    @Getter
    private RoundTimer roundTimer;
    private final SpellNames counterSpell;

    public BuffDebuffSpell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, SpellAlignment spellAlignment, int manaCost, CreatureStats creatureStats, int time, SpellNames counterSpell) {
        super(category, name, spellMagicClass, rang, spellAlignment, manaCost);
        this.creatureStats = creatureStats;
        this.time = time;
        this.counterSpell = counterSpell;
    }

    private BuffDebuffSpell(BuffDebuffSpell buffDebuffSpell, Creature creature) {
        super(buffDebuffSpell.getCategory(), buffDebuffSpell.getName(), buffDebuffSpell.getSpellMagicClass(), buffDebuffSpell.getRang(), buffDebuffSpell.getSpellAlignment(), buffDebuffSpell.getManaCost());
        this.creatureStats = buffDebuffSpell.creatureStats;
        this.time = buffDebuffSpell.time;
        this.counterSpell = buffDebuffSpell.counterSpell;
        this.roundTimer = new RoundTimer(buffDebuffSpell.time, this, creature, buffDebuffSpell.counterSpell);
    }

    @Override
    public void castSpell(Creature aDefender, BiConsumer<String, PropertyChangeListener> consumer) {
        if (aDefender.getRunningSpells().stream().map(Spell::getName).collect(Collectors.toList()).contains(this.getName()) && !getName().equals(SpellNames.DISRUPTING_RAY)) {
            observerSupport.firePropertyChange(RoundTimer.RESET_TIMER, null, null);
            return;
        }
        aDefender.addRunningSpell(this);
        BuffDebuffSpell buffDebuffSpell = new BuffDebuffSpell(this, aDefender);
        consumer.accept(TurnQueue.END_OF_TURN, buffDebuffSpell.getRoundTimer());
        observerSupport.addPropertyChangeListener(RoundTimer.RESET_TIMER, buffDebuffSpell.getRoundTimer());
        aDefender.buff(creatureStats);
    }

    private CreatureStats convertToNegative(CreatureStats creatureStats) {
        return CreatureStats.builder()
                .attack(-creatureStats.getAttack())
                .armor(-creatureStats.getArmor())
                .moveRange(-creatureStats.getMoveRange())
                .build();
    }

    @Override
    public void unCastSpell(Creature creature) {
        creature.buff(convertToNegative(creatureStats));
    }

}
