package pl.psi.creatures;

import java.util.Random;

public class MaximalDamageCalculator extends AbstractCalculateDamageStrategy {


    protected MaximalDamageCalculator() {
        super(new Random());
    }

    @Override
    protected double getRandomValueFromAttackRange(final Creature aAttacker, final Creature aDefender) {
        return aAttacker.getDamage().upperEndpoint();
    }

}