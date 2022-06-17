package pl.psi.creatures;

import lombok.Getter;

@Getter

public class ShooterCreatureDecorator extends AbstractCreature {
    private final Creature decorated;
    private final int maxShots;
    private final double MELEE_PENALTY = 0.5;
    private final ReducedDamageCalculator meleeDamageCalculator = new ReducedDamageCalculator(MELEE_PENALTY);
    private final DamageCalculatorIf rangeDamageCalculator;
    private int shots;
    private boolean isInMelee = false;
    private int range = Integer.MAX_VALUE;

    public ShooterCreatureDecorator(final Creature aDecorated, final int aShots) {
        super(aDecorated);
        decorated = aDecorated;
        shots = aShots;
        maxShots = aShots;
        rangeDamageCalculator = decorated.getCalculator();
    }

    @Override
    public void attack(final Creature aDefender) {
        if (isInMelee) {
            decorated.attack(aDefender);
        } else if (canShoot()) {
            attackRange(aDefender);
        }
    }

    private void attackRange(final Creature aDefender) {
        final int damage = getCalculator().calculateDamage(decorated, aDefender);
        decorated.applyDamage(aDefender, damage);
        shots -= 1;
    }

    public void setInMelee(boolean value) {
        if (value) {
            isInMelee = true;
            range = 1;
            decorated.setCalculator(meleeDamageCalculator);
        } else {
            isInMelee = false;
            range = Integer.MAX_VALUE;
            decorated.setCalculator(rangeDamageCalculator);
        }
    }

    public boolean canShoot() {
        if (shots > 0) {
            return true;
        } else {
            throw new RuntimeException("No more shots");
        }
    }

    public void resetShots() {
        shots = maxShots;
    }
}
