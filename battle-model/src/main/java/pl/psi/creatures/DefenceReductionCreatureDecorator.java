package pl.psi.creatures;

public class DefenceReductionCreatureDecorator extends AbstractCreature {

    private final Creature decorated;

    public DefenceReductionCreatureDecorator(Creature aDecorated, final double aFactor) {
        super(aDecorated);
        decorated = aDecorated;
        decorated.setCalculator(new ReducedDefenceCalculator(aFactor));
    }

    @Override
    public void attack(final Creature aDefender) {
        decorated.attack(aDefender);
    }

}
