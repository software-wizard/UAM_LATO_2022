package pl.psi.creatures;

import pl.psi.TurnQueue;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ballista extends AbstractWarMachines {


    public Ballista(Creature aDecorated, WarMachineActionType actionType, int aSkillLevel) {
        super(aDecorated, actionType, aSkillLevel);
    }

    private double range = Integer.MAX_VALUE;

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {

        super.propertyChange(evt);
        if (TurnQueue.NEW_TURN.equals(evt.getPropertyName())) {
            List<Creature> creatureList = (List<Creature>) evt.getOldValue();
            List<Creature> creatures = new ArrayList<>(creatureList);
            Collections.shuffle(creatures);

            creatures.stream()
                    .filter(Creature::isAlive)
                    .findAny()
                    .ifPresent(this::attackRange);
        }
    }

    @Override
    public double getAttackRange(){
        return range;
    }

    @Override
    public boolean isRange(){
        return true;
    }

    @Override
    public void attack(final Creature aDefender) {
            attackRange(aDefender);
    }

    private void attackRange(final Creature aDefender) {
        final int damage = getCalculator().calculateDamage(this, aDefender);
        applyDamage(aDefender, damage);
    }
}