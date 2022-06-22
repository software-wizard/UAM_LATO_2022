package pl.psi.spells;

import lombok.Getter;
import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Getter
public class MagicMirror extends Spell<Creature> {

    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private final int time;
    int probability;
    @Getter
    private RoundTimer roundTimer;

    public MagicMirror(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, SpellAlignment spellAlignment, int manaCost, int probability, int time) {
        super(category, name, spellMagicClass, rang, spellAlignment, manaCost);
        this.probability = probability;
        this.time = time;
    }

    private MagicMirror(MagicMirror magicMirror, Creature creature) {
        super(magicMirror.getCategory(), magicMirror.getName(), magicMirror.getSpellMagicClass(), magicMirror.getRang(), magicMirror.getSpellAlignment(), magicMirror.getManaCost());
        this.probability = magicMirror.probability;
        this.time = magicMirror.time;
        this.roundTimer = new RoundTimer(magicMirror.time, this, creature, null);
    }

    @Override
    public void castSpell(Creature aDefender, BiConsumer<String, PropertyChangeListener> consumer) {
        if (aDefender.getRunningSpells().stream().map(Spell::getName).collect(Collectors.toList()).contains(this.getName()) && !getName().equals(SpellNames.DISRUPTING_RAY)) {
            observerSupport.firePropertyChange(RoundTimer.RESET_TIMER, null, null);
            return;
        }
        aDefender.addRunningSpell(this);
        MagicMirror buffDebuffSpell = new MagicMirror(this, aDefender);
        consumer.accept(TurnQueue.END_OF_TURN, buffDebuffSpell.getRoundTimer());
        observerSupport.addPropertyChangeListener(RoundTimer.RESET_TIMER, buffDebuffSpell.getRoundTimer());
    }

    @Override
    public void unCastSpell(Creature creature) {
    }

}
