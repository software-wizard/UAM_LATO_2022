package pl.psi.creatures;

import java.util.Random;

public class CurseDamageCalculator extends AbstractCalculateDamageStrategy {


    public CurseDamageCalculator() {
        super(new Random());
    }

    @Override
    protected double getRandomValueFromAttackRange(final Creature aAttacker, final Creature aDefender) {
        return (aAttacker.getDamage().lowerEndpoint() * 0.8) + 1;
    }

}
