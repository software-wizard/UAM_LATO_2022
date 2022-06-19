package pl.psi.creatures;

import java.util.Random;

public class CurseOnHitCreatureDecorator extends AbstractCreature {

    private final Creature decorated;
    private final MinimalDamageCalculator minimalDamageCalculator = new MinimalDamageCalculator();

    public CurseOnHitCreatureDecorator(Creature aDecorated) {
        super(aDecorated);
        decorated = aDecorated;
    }

    @Override
    public void attack(final Creature aDefender) {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if (chance <= 20) {
            attackWithCurse(aDefender);
        } else {
            decorated.attack(aDefender);
        }
    }

    public void attackWithCurse(final Creature aDefender) {
        aDefender.setCalculator(minimalDamageCalculator);
        decorated.attack(aDefender);
    }
}
