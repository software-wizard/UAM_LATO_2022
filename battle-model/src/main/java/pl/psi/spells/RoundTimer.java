package pl.psi.spells;

import lombok.Getter;
import lombok.Setter;
import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@Getter
@Setter
public class RoundTimer implements PropertyChangeListener {

    private final int timer;
    private int currentTimer;
    private final BuffDebuffSpell buffDebuffSpell;
    private final Creature creature;

    public RoundTimer(int timer, BuffDebuffSpell buffDebuffSpell, Creature creature) {
        this.timer = timer;
        this.buffDebuffSpell = buffDebuffSpell;
        currentTimer = timer;
        this.creature = creature;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TurnQueue.END_OF_TURN.equals(evt.getPropertyName())) {
            currentTimer = currentTimer - 1;
            if (currentTimer == 0) {
                buffDebuffSpell.unCastSpell(creature);
            }
        }
    }


}
