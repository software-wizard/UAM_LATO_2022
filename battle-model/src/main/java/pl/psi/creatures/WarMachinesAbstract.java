package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.Getter;

import java.beans.PropertyChangeEvent;

@Getter
public abstract class WarMachinesAbstract extends Creature{

    protected CreatureStatisticIf stats;
    protected int amount;
    protected int currentHp;
    protected DamageCalculatorIf calculator;
    protected Creature decorated;


    @Override
    public int getCounterAttackCounter() {
        return 0;
    }

   public abstract void performAction(final Creature aDefender);



}
