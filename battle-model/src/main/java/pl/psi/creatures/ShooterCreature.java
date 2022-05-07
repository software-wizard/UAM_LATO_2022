package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.Getter;

import java.beans.PropertyChangeEvent;

@Getter

public class ShooterCreature extends AbstractCreature
{
    private final Creature decorated;
    private int shots;
    private boolean isInMelee = false;

    public ShooterCreature( final Creature aDecorated, final int aShots )
    {
        super( aDecorated );
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
    public DamageCalculatorIf getCalculator()
    {
        return decorated.getCalculator();
    }

    @Override
    public void attack( final Creature aDefender )
    {
        decorated.attack( aDefender );
    }

    public void attackRange( final Creature aDefender ){
        if( canShoot() ){
            final int damage = getCalculator().calculateDamage( decorated, aDefender );
            decorated.applyDamage( aDefender, damage );
            shots -= 1;
        }
        else{
            throw new RuntimeException( "Not enough shots or creature in melee range" );
        }
    }

    public void attackMelee( final Creature aDefender ){
        final int hp = aDefender.getCurrentHp();
        decorated.attack( aDefender );
        aDefender.heal( ( hp - aDefender.getCurrentHp() ) / 2 );
    }

    public boolean canShoot(){
        return shots > 0 && !isInMelee;
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
