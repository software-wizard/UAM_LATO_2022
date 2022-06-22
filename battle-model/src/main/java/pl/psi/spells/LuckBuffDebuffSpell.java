package pl.psi.spells;

import lombok.Getter;
import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class LuckBuffDebuffSpell extends Spell<Creature> {

    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private final int luck;
    private final int time;
    @Getter
    private RoundTimer roundTimer;
    private final SpellNames counterSpell;

    public LuckBuffDebuffSpell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, SpellAlignment spellAlignment, int manaCost, int luck, int time, SpellNames counterSpell) {
        super(category, name, spellMagicClass, rang, spellAlignment, manaCost);
        this.luck = luck;
        this.time = time;
        this.counterSpell = counterSpell;
    }

    public LuckBuffDebuffSpell(LuckBuffDebuffSpell luckBuffDebuffSpell, Creature creature) {
        super(luckBuffDebuffSpell.getCategory(), luckBuffDebuffSpell.getName(), luckBuffDebuffSpell.getSpellMagicClass(), luckBuffDebuffSpell.getRang(), luckBuffDebuffSpell.getSpellAlignment(), luckBuffDebuffSpell.getManaCost());
        this.luck = luckBuffDebuffSpell.luck;
        this.time = luckBuffDebuffSpell.time;
        this.counterSpell = luckBuffDebuffSpell.counterSpell;
        this.roundTimer = new RoundTimer(luckBuffDebuffSpell.time, this, creature, luckBuffDebuffSpell.counterSpell);
    }

    @Override
    public void castSpell(Creature aDefender, BiConsumer<String, PropertyChangeListener> consumer) {
        if (aDefender.getRunningSpells().stream().map(Spell::getName).collect(Collectors.toList()).contains(this.getName()) && !getName().equals(SpellNames.DISRUPTING_RAY)) {
            observerSupport.firePropertyChange(RoundTimer.RESET_TIMER, null, null);
            return;
        }
        aDefender.addRunningSpell(this);
        LuckBuffDebuffSpell luckBuffDebuffSpell = new LuckBuffDebuffSpell(this, aDefender);
        consumer.accept(TurnQueue.END_OF_TURN, luckBuffDebuffSpell.getRoundTimer());
        observerSupport.addPropertyChangeListener(RoundTimer.RESET_TIMER, luckBuffDebuffSpell.getRoundTimer());
        aDefender.buffLuck(luck);
    }

    @Override
    public void unCastSpell(Creature creature) {
        creature.buffLuck(-luck);
    }
}
