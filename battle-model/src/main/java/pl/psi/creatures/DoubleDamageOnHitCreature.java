package pl.psi.creatures;

import com.google.common.collect.Range;

import java.util.Random;

public class DoubleDamageOnHitCreature extends AbstractCreature{
    private final Creature decorated;
    private final ReducedDamageCalculator doubleDamageCalculator = new ReducedDamageCalculator( new Random(), 2 );
    private final DamageCalculatorIf defaultDamageCalculator;

    public DoubleDamageOnHitCreature(Creature aDecorated) {
        super(aDecorated);
        decorated = aDecorated;
        defaultDamageCalculator = decorated.getCalculator();
    }

    @Override
    public void attack( final Creature aDefender )
    {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if(chance <= 20){
            attackWithDoubleDamage( aDefender );
        }
        else{
            decorated.attack( aDefender );
        }
    }

    public void attackWithDoubleDamage( final Creature aDefender ){
        decorated.setCalculator( doubleDamageCalculator );
        decorated.attack( aDefender );
        decorated.setCalculator( defaultDamageCalculator );
    }
}
