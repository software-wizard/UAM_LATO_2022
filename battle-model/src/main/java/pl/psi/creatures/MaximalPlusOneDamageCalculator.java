package pl.psi.creatures;

import java.util.Random;

public class MaximalPlusOneDamageCalculator extends AbstractCalculateDamageStrategy {


    public MaximalPlusOneDamageCalculator() {
        super(new Random());
    }

    @Override
    protected double getRandomValueFromAttackRange(final Creature aAttacker, final Creature aDefender) {
        return aAttacker.getDamage().upperEndpoint() + 1;
    }

}