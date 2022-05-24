package pl.psi.creatures;

import java.util.Random;

public class ReduceDefenceCreature extends AbstractCreature{

    private final Creature decorated;

    public ReduceDefenceCreature(Creature aDecorated, final double aFactor) {
        super(aDecorated);
        decorated = aDecorated;
        decorated.setCalculator(new ReduceDefenceCalculator( aFactor ));
    }

    @Override
    public void attack( final Creature aDefender )
    {
        decorated.attack( aDefender );
    }

}
