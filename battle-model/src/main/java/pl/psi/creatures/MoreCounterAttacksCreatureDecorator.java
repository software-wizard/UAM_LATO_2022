package pl.psi.creatures;

public class MoreCounterAttacksCreatureDecorator extends AbstractCreature {

    private final Creature decorated;
    private int counterAttackCounter;
    private final int maxCounterAttackCounter;

    public MoreCounterAttacksCreatureDecorator(Creature aDecorated, final int counter) {
        super(aDecorated);
        decorated = aDecorated;
        counterAttackCounter = counter;
        maxCounterAttackCounter = counter;
    }

    @Override
    public void setCanCounterAttack(final boolean value) {
        if (value) {
            counterAttackCounter = maxCounterAttackCounter;
        } else {
            counterAttackCounter -= 1;
        }
    }

    @Override
    public boolean getCanCounterAttack(){
        return counterAttackCounter > 0;
    }
}