package pl.psi.creatures;

import java.util.Random;

public class ReducedDamageCalculator extends AbstractCalculateDamageStrategy{

    private final double reduceBy;

    protected ReducedDamageCalculator( double aReduceBy) {
        super( new Random() );
        reduceBy = aReduceBy;
    }

    @Override
    public final int calculateDamage( final Creature aAttacker, final Creature aDefender ){
        AbstractCalculateDamageStrategy defaultCalculator = new DefaultDamageCalculator( new Random() );
        return (int)( defaultCalculator.calculateDamage( aAttacker, aDefender ) * reduceBy );
    }
}
