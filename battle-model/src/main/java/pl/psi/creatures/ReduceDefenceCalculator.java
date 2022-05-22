package pl.psi.creatures;

import java.util.Random;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
class ReduceDefenceCalculator extends AbstractCalculateDamageStrategy
{

    private final double factor;

    public ReduceDefenceCalculator( final double aFactor )
    {
        super( new Random() );
        factor = aFactor;
    }

    @Override
    protected double getArmor( final Creature aDefender )
    {
        return (aDefender.getArmor() * factor);
    }
}
