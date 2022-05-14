package pl.psi.creatures;

import com.google.common.collect.Range;

import java.beans.PropertyChangeEvent;

public class HealFromAttackCreature extends AbstractCreature
{
    private final Creature decorated;

    public HealFromAttackCreature( final Creature aDecorated )
    {
        super( aDecorated );
        decorated = aDecorated;
    }

    /*@Override
    public CreatureStatisticIf getStats()
    {
        return decorated.getStats();
    }*/

   /* @Override
    public int getAmount()
    {
        return decorated.getAmount();
    }

    @Override
    public DamageCalculatorIf getCalculator()
    {
        return decorated.getCalculator();
    }*/

    @Override
    public void attack( final Creature aDefender )
    {
        final int initialDefenderHp = aDefender.getCurrentHp();
        decorated.attack( aDefender );
        if( aDefender.getBasicStats().getType().equals(CreatureStatistic.CreatureType.ALIVE) ){
            decorated.heal( initialDefenderHp - aDefender.getCurrentHp() );
        }

    }
}
