package pl.psi.spells;

import lombok.Getter;
import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Counterstrike extends Spell<Creature> implements PropertyChangeListener {

    private final int counterstrikeCounter;
    private final int time;
    @Getter
    private RoundTimer roundTimer;
    Creature creature;
    int currentCounterstrikeCounter;

    public Counterstrike(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, int manaCost, int counterstrikeCounter, int time) {
        super(category, name, spellMagicClass, rang, manaCost);
        this.counterstrikeCounter = counterstrikeCounter;
        this.time = time;

    }

    public Counterstrike(Counterstrike counterstrike, Creature creature, BiConsumer<String, PropertyChangeListener> consumer) {
        super(counterstrike.getCategory(), counterstrike.getName(), counterstrike.getSpellMagicClass(), counterstrike.getRang(), counterstrike.getManaCost());
        this.counterstrikeCounter = counterstrike.counterstrikeCounter;
        this.time = counterstrike.time;
        this.roundTimer = new RoundTimer(counterstrike.time, this, creature, null);
        this.creature = creature;
        currentCounterstrikeCounter = counterstrikeCounter;
        consumer.accept(TurnQueue.END_OF_TURN, counterstrike.getRoundTimer());
        consumer.accept(TurnQueue.NEXT_CREATURE, this);
    }

    @Override
    public void castSpell(Creature aDefender, BiConsumer<String, PropertyChangeListener> consumer) {
        aDefender.addRunningSpell(this);
        Counterstrike counterstrike = new Counterstrike(this, aDefender, consumer);
        aDefender.setCanCounterAttack(true);
    }

    @Override
    public void unCastSpell(Creature aDefender) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TurnQueue.NEXT_CREATURE.equals(evt.getPropertyName()) && currentCounterstrikeCounter >= 0) {
            creature.setCanCounterAttack(true);
            currentCounterstrikeCounter = currentCounterstrikeCounter - 1;
        }
    }
}
