package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.Builder;

import java.beans.PropertyChangeEvent;
/**
 * This class should always decorate first
 */

public class NoCounterCreature extends AbstractCreature
{
    private final Creature decorated;

    public NoCounterCreature( final Creature aDecorated )
    {
        super( aDecorated );
        decorated = aDecorated;
    }

    @Override
    public void attack( final Creature aDefender )
    {
        if( isAlive() )
        {
            final int damage = getCalculator().calculateDamage( decorated, aDefender );
            decorated.applyDamage( aDefender, damage );
        }
    }
}
