package pl.psi.creatures;

import java.util.Random;

public class MinimalDamageCalculator extends AbstractCalculateDamageStrategy {


    protected MinimalDamageCalculator() {
        super(new Random());
    }

    @Override
    protected double getRandomValueFromAttackRange(final Creature aAttacker, final Creature aDefender) {
        return aAttacker.getDamage().lowerEndpoint();
    }

}
