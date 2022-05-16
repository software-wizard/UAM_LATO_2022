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
    private final double MELEE_PENALTY = 0.5;
    private int range = Integer.MAX_VALUE;

    public ShooterCreature( final Creature aDecorated, final int aShots )
    {
        super( aDecorated );
        decorated = aDecorated;
        shots = aShots;
    }

    @Override
    public void attack( final Creature aDefender )
    {
        if( canShoot() && !isInMelee ){
            attackRange( aDefender );
        }
        else{
            attackMelee( aDefender );
        }
    }

    public void attackRange( final Creature aDefender ){
        final int damage = getCalculator().calculateDamage( decorated, aDefender );
        decorated.applyDamage( aDefender, damage );
        shots -= 1;
    }

    public void attackMelee( final Creature aDefender ){
        decorated.attackWithReducedDamage( aDefender, getMELEE_PENALTY() );
    }

    public void setInMelee( boolean value ){
        if( value ){
            isInMelee = true;
            range = 1;
        }
        else {
            isInMelee = false;
            range = Integer.MAX_VALUE;
        }
    }

    public boolean canShoot(){
        if( shots > 0 ){
            return true;
        }
        else{
            throw new RuntimeException( "No more shots" );
        }
    }
}
