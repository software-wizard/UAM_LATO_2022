package pl.psi.creatures;

import java.util.Random;

public class AgeOnHitCreature extends AbstractCreature{

    private final Creature decorated;

    public AgeOnHitCreature(Creature aDecorated) {
        super(aDecorated);
        decorated = aDecorated;
    }

    @Override
    public void attack( final Creature aDefender )
    {
        if( isAlive() )
        {
            final int damage = getCalculator().calculateDamage( decorated, aDefender );
            applyDamage( aDefender, damage );
            Random rand = new Random();
            int chance = rand.nextInt(100);
            if( chance <= 20 && aDefender.getBasicStats().getType().equals( CreatureStatistic.CreatureType.ALIVE ) ){
                age( aDefender );
            }
            if( canCounterAttack( aDefender ) )
            {
                decorated.counterAttack( aDefender );
            }
        }
    }

    private void age( Creature defender ){
        CreatureStats reduceMaxHp = new CreatureStats
                .CreatureStatsBuilder()
                    .maxHp( -(defender.getStats().getMaxHp() / 2) )
                .build();
        defender.updateStats( reduceMaxHp );
        final int currentHpAfterAge = Math.max(defender.getCurrentHp() - ( defender.getBasicStats().getMaxHp() - defender.getStats().getMaxHp() ), 1);
        defender.setCurrentHp( currentHpAfterAge );
    }

    public void attackWithAge( Creature aDefender ){  // TEST PURPOSE ONLY
        final int damage = getCalculator().calculateDamage( decorated, aDefender );
        applyDamage( aDefender, damage );
        if( aDefender.getBasicStats().getType().equals( CreatureStatistic.CreatureType.ALIVE ) ){
            age( aDefender );
        }
        if( canCounterAttack( aDefender ) )
        {
            decorated.counterAttack( aDefender );
        }
    }
}
