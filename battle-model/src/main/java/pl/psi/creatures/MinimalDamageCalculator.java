package pl.psi.creatures;

import java.util.Random;

public class MinimalDamageCalculator extends AbstractCalculateDamageStrategy {


    public MinimalDamageCalculator() {
        super(new Random());
    }

    @Override
    protected double getRandomValueFromAttackRange(final Creature aAttacker, final Creature aDefender) {
        System.out.println("MIN");
        System.out.println(aAttacker.getDamage().lowerEndpoint());
        return aAttacker.getDamage().lowerEndpoint();
    }

}
