package pl.psi.creatures;


public class DoubleAttackCreatureDecorator extends AbstractCreature {
    private final Creature decorated;

    public DoubleAttackCreatureDecorator(Creature aDecorated) {
        super(aDecorated);
        decorated = aDecorated;
    }

    @Override
    public void attack(final Creature aDefender) {
        decorated.attack(aDefender);
        decorated.attack(aDefender);
    }
}
