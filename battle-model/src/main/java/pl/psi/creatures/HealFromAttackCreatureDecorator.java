package pl.psi.creatures;

public class HealFromAttackCreatureDecorator extends AbstractCreature {
    private final Creature decorated;

    public HealFromAttackCreatureDecorator(final Creature aDecorated) {
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

    @Override
    protected void counterAttack(final Creature aAttacker){
        decorated.counterAttack(aAttacker);
        decorated.heal(decorated.getLastCounterAttackDamage());
    }
}
