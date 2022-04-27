package pl.psi.creatures;

import com.google.common.collect.Range;

import java.beans.PropertyChangeEvent;

public class NoCounterCreature extends Creature
{
    private final Creature decorated;

    public NoCounterCreature( final Creature aDecorated )
    {
        decorated = aDecorated;
    }

    @Override
    public CreatureStatisticIf getStats()
    {
        return decorated.getStats();
    }

    @Override
    public int getAmount()
    {
        return decorated.getAmount();
    }

    @Override
    public int getCounterAttackCounter()
    {
        return decorated.getCounterAttackCounter();
    }

    @Override
    public DamageCalculatorIf getCalculator()
    {
        return decorated.getCalculator();
    }

    @Override
    public void attack( final Creature aDefender )
    {
        final int oldCounterAttackCounter = aDefender.getCounterAttackCounter();
        aDefender.setCounterAttackCounter(0);
        decorated.attack( aDefender );
        aDefender.setCounterAttackCounter(oldCounterAttackCounter);
    }

    @Override
    public boolean isAlive()
    {
        return decorated.isAlive();
    }

    @Override
    public int getCurrentHp()
    {
        return decorated.getCurrentHp();
    }

    @Override
    protected void setCurrentHp( final int aCurrentHp )
    {
        decorated.setCurrentHp( aCurrentHp );
    }

    @Override
    Range< Integer > getDamage()
    {
        return decorated.getDamage();
    }

    @Override
    int getAttack()
    {
        return decorated.getAttack();
    }

    @Override
    int getArmor()
    {
        return decorated.getArmor();
    }

    @Override
    protected void restoreCurrentHpToMax()
    {
        decorated.restoreCurrentHpToMax();
    }

    @Override
    public void propertyChange( final PropertyChangeEvent evt )
    {
        decorated.propertyChange( evt );
    }

}
