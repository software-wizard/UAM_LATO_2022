package pl.psi.creatures;

import java.util.Random;

public class DoubleAttackCreature extends AbstractCreature{
    private final Creature decorated;

    public DoubleAttackCreature(Creature aDecorated) {
        super(aDecorated);
        decorated = aDecorated;
    }

    @Override
    public void attack( final Creature aDefender )
    {
        decorated.attack( aDefender );
        decorated.attack( aDefender );
    }
}
