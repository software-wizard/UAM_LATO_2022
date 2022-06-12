package pl.psi.creatures;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
class AttackTwiceCreatureDecorator extends AbstractCreature {

    public AttackTwiceCreatureDecorator(final Creature aDecorated) {
        super(aDecorated);
    }

    @Override
    public void attack(final Creature aDefender) {
        getDecorated().attack(aDefender);
        getDecorated().attack(aDefender);
    }
}
