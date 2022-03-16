/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class ReduceDefenceCalculator implements DamageCalculatorIf
{
    public int calculateDamage( final int aAttackerAttack, final int aDefenderDefence )
    {
        return aAttackerAttack - (int)(aDefenderDefence * 0.2);
    }
}
