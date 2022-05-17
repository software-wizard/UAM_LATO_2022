package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.Getter;

import java.beans.PropertyChangeEvent;
import java.util.Random;

@Getter

public class ShooterCreature extends AbstractCreature
{
    private final Creature decorated;
    private int shots;
    private boolean isInMelee = false;
    private final double MELEE_PENALTY = 0.5;
    private int range = Integer.MAX_VALUE;
    private final ReducedDamageCalculator meleeDamageCalculator = new ReducedDamageCalculator( new Random(), MELEE_PENALTY);
    private final DamageCalculatorIf defaultDamageCalculator;

    public ShooterCreature( final Creature aDecorated, final int aShots )
    {
        super( aDecorated );
        decorated = aDecorated;
        shots = aShots;
        defaultDamageCalculator = decorated.getCalculator();
    }

    @Override
    public void attack( final Creature aDefender )
    {
        if( isInMelee ){
            decorated.attack( aDefender );
        }
        else if( canShoot() ){
            attackRange( aDefender );
        }
    }

    private void attackRange( final Creature aDefender ){
        final int damage = getCalculator().calculateDamage( decorated, aDefender );
        decorated.applyDamage( aDefender, damage );
        shots -= 1;
    }

    public void setInMelee( boolean value ){
        if( value ){
            isInMelee = true;
            range = 1;
            decorated.setCalculator( meleeDamageCalculator );
        }
        else {
            isInMelee = false;
            range = Integer.MAX_VALUE;
            decorated.setCalculator( defaultDamageCalculator );
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
