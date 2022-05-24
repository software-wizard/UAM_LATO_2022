package pl.psi.creatures;

import java.util.Random;

public class ThunderboltOnHitCreature extends AbstractCreature{

    private Creature decorated;

    public ThunderboltOnHitCreature(Creature aDecorated) {
        super(aDecorated);
        decorated = aDecorated;
    }

    @Override
    public void attack( final Creature aDefender )
    {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if(chance <= 20){
            attackWithThunderbolt( aDefender );
        }
        else{
            decorated.attack( aDefender );
        }
    }

    public void attackWithThunderbolt( final Creature aDefender ){
        if( isAlive() )
        {
            final int damage = getCalculator().calculateDamage( this, aDefender );
            applyDamage( aDefender, damage );
            thunderbolt( aDefender );
            if( canCounterAttack( aDefender ) )
            {
                counterAttack( aDefender );
            }
        }
    }

    private void thunderbolt( final Creature aDefender ){
        final int damage = 10 * decorated.getAmount();
        applyDamage( aDefender, damage );
    }
}
