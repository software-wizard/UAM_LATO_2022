package pl.psi.spells;

import lombok.Getter;
import lombok.Setter;
import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
public class RoundTimer implements PropertyChangeListener {

    public static final String RESET_TIMER = "RESET_TIMER";
    private final int timer;
    private int currentTimer;
    private final Spell spell;
    private final Creature creature;

    public RoundTimer(int timer, Spell spell, Creature creature, SpellNames counterSpell) {
        this.timer = timer;
        this.spell = spell;
        currentTimer = timer;
        this.creature = creature;
        removeCounterSpell(creature, counterSpell);
        ifRunningSpellFullRemoveFirstElement(creature);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(currentTimer <= 0) return;

        if(RESET_TIMER.equals(evt.getPropertyName())){
            resetTimer();
        }

        if (TurnQueue.END_OF_TURN.equals(evt.getPropertyName()) && isSpellOnRunningSpellList()) {
            currentTimer = currentTimer - 1;
            if (currentTimer == 0) {
                spell.unCastSpell(creature);
                creature.getRunningSpells().removeIf(aSpell -> aSpell.getName().equals(spell.getName()));
            }
        }
    }

    private boolean isSpellOnRunningSpellList() {
        return creature.getRunningSpells().stream().map(Spell::getName).collect(Collectors.toList()).contains(spell.getName());
    }

    public void resetTimer() {
        currentTimer = timer + 1;
    }

    public void removeCounterSpell(Creature aDefender, SpellNames counterSpell){
        Optional<Spell> optionalCounterSpell = aDefender.getRunningSpells().stream()
                .filter(spell -> spell.getName().equals(counterSpell))
                .findAny();
        if (optionalCounterSpell.isPresent()) {
            optionalCounterSpell.get().unCastSpell(aDefender);
            aDefender.getRunningSpells().removeIf(spell -> spell.getName().equals(counterSpell));
        }
    }

    public void ifRunningSpellFullRemoveFirstElement(Creature aDefender){
        if (!aDefender.isRunningSpellsSlotsFull()){
            aDefender.getRunningSpells().poll().unCastSpell(aDefender);
        }
    }
}
