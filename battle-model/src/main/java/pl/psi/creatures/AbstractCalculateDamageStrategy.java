package pl.psi.creatures;

import java.util.Random;

abstract class AbstractCalculateDamageStrategy implements DamageCalculatorIf
{

    public static final int MAX_ATTACK_DIFF = 60;
    public static final int MAX_DEFENCE_DIFF = 12;
    public static final double DEFENCE_BONUS = 0.025;
    public static final double ATTACK_BONUS = 0.05;
    private final Random rand;

    protected AbstractCalculateDamageStrategy( final Random aRand )
    {
        rand = aRand;
    }

    @Override
    public int calculateDamage( final Creature aAttacker, final Creature aDefender )
    {
        final double armor = getArmor( aDefender );
        double randValue = rand.nextInt( (aAttacker.getDamage()
                .upperEndpoint()
                - aAttacker.getDamage()
                .lowerEndpoint()
                + 1)) + aAttacker.getDamage()
                .lowerEndpoint();
        if( minimalDamageRange() ) {
            randValue = aAttacker.getDamage().lowerEndpoint();
        }
        else if( maximalDamageRange() ){
            randValue = aAttacker.getDamage().upperEndpoint();
        }

        double oneCreatureDamageToDeal;
        if( aAttacker.getAttack() >= armor )
        {
            double attackPoints = aAttacker.getAttack() - armor;
            if( attackPoints > MAX_ATTACK_DIFF )
            {
                attackPoints = MAX_ATTACK_DIFF;
            }
            oneCreatureDamageToDeal = randValue * (1 + attackPoints * ATTACK_BONUS);
        }
        else
        {
            double defencePoints = armor - aAttacker.getAttack();
            if( defencePoints > MAX_DEFENCE_DIFF )
            {
                defencePoints = MAX_DEFENCE_DIFF;
            }
            oneCreatureDamageToDeal = randValue * (1 - defencePoints * DEFENCE_BONUS);
        }

        if( oneCreatureDamageToDeal < 0 )
        {
            oneCreatureDamageToDeal = 0;
        }
        return (int)( aAttacker.getAmount() * oneCreatureDamageToDeal );
    }

    boolean minimalDamageRange(){ return false; }
    boolean maximalDamageRange(){ return false; }

    protected double getArmor( final Creature aDefender )
    {
        return aDefender.getArmor();
    }
}
