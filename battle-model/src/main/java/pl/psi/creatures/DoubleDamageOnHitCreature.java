package pl.psi.creatures;

import com.google.common.collect.Range;

import java.util.Random;

public class DoubleDamageOnHitCreature extends AbstractCreature{
    private final Creature decorated;

    public DoubleDamageOnHitCreature(Creature aDecorated) {
        super(aDecorated);
        decorated = aDecorated;
    }

    @Override
    public void attack( final Creature aDefender )
    {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if(chance <= 20){
            decorated.attackWithReducedDamage( aDefender, 2 );
        }
        else{
            decorated.attack( aDefender );
        }
    }

    public void attackWithDoubleDamage( final Creature aDefender ){  /* TEST PURPOSE ONLY */
        decorated.attackWithReducedDamage( aDefender, 2 );
    }
}
