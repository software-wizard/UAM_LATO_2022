package pl.psi.creatures;

import java.util.Random;

public class AgeOnHitCreature extends AbstractCreature {

    private final Creature decorated;

    public AgeOnHitCreature(Creature aDecorated) {
        super(aDecorated);
        decorated = aDecorated;
    }

    @Override
    public void attack(final Creature aDefender) {
        if (isAlive()) {
            Random rand = new Random();
            int chance = rand.nextInt(100);
            if (chance <= 20 && aDefender.getBasicStats().getType().equals(CreatureStatistic.CreatureType.ALIVE)) {
                attackWithAge(aDefender);
            } else {
                decorated.attack(aDefender);
            }
        }
    }

    public void attackWithAge(Creature aDefender) {
        final int damage = getCalculator().calculateDamage(decorated, aDefender);
        applyDamage(aDefender, damage);
        aDefender.age();
        if (canCounterAttack(aDefender)) {
            decorated.counterAttack(aDefender);
        }
    }
}
