package pl.psi.creatures;

public class HealFromAttackCreature extends AbstractCreature {
    private final Creature decorated;

    public HealFromAttackCreature(final Creature aDecorated) {
        super(aDecorated);
        decorated = aDecorated;
    }

    @Override
    public void attack(final Creature aDefender) {
        decorated.attack(aDefender);
        if (aDefender.getBasicStats().getType().equals(CreatureStatistic.CreatureType.ALIVE)) {
            decorated.heal(decorated.getLastAttackDamage());
        }
    }
}
