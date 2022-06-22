package pl.psi.creatures;

import java.util.Random;

public class MaximalDamageCalculator extends AbstractCalculateDamageStrategy {


    public MaximalDamageCalculator() {
        super(new Random());
    }

    @Override
    protected double getRandomValueFromAttackRange(final Creature aAttacker, final Creature aDefender) {
        System.out.println("MAX");
        System.out.println(aAttacker.getDamage().upperEndpoint());
        return aAttacker.getDamage().upperEndpoint();
    }

}