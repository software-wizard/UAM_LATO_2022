package pl.psi.creatures;

public interface DamageCalculatorIf
{
    int calculateDamage( Creature aAttacker, Creature aDefender );
    int calculateReducedDamage( final Creature aAttacker, final Creature aDefender, final double reducePercentage );
    int calculateMinimumDamage( final Creature aAttacker, final Creature aDefender);
}
