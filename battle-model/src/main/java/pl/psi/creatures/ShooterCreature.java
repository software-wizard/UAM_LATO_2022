package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.Getter;

import java.beans.PropertyChangeEvent;

@Getter

public class ShooterCreature extends Creature
{
    private final Creature decorated;
    private int shots;

    public ShooterCreature( final Creature aDecorated, final int aShots )
    {
        decorated = aDecorated;
        shots = aShots;
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
        if( canShoot() ){
            decorated.attack( aDefender );
            shots -= 1;
        }
    }

    public boolean canShoot(){
        return shots > 0;
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
