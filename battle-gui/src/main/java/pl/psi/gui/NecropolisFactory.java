package pl.psi.gui;

import pl.psi.products.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.creatures.HealFromAttackCreature;
import pl.psi.creatures.NoCounterCreature;

public class NecropolisFactory
{

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    public Creature create( final boolean aIsUpgraded, final int aTier, final int aAmount )
    {
        if( !aIsUpgraded )
        {
            switch( aTier )
            {
                case 1:
                    return new Creature.Builder().statistic( CreatureStatistic.SKELETON )
                        .amount( aAmount )
                        .build();
                case 2:
                    return new Creature.Builder().statistic( CreatureStatistic.WALKING_DEAD )
                        .amount( aAmount )
                        .build();
                case 3:
                    return new Creature.Builder().statistic( CreatureStatistic.WIGHT )
                        .amount( aAmount )
                        .build();
                case 4:
                    Creature decorated = new Creature.Builder().statistic( CreatureStatistic.VAMPIRE )
                            .amount( aAmount )
                            .build();
                    return new NoCounterCreature( decorated );
                case 5:
                    return new Creature.Builder().statistic( CreatureStatistic.LICH )
                        .amount( aAmount )
                        .build();
                case 6:
                    return new Creature.Builder().statistic( CreatureStatistic.BLACK_KNIGHT )
                        .amount( aAmount )
                        .build();
                case 7:
                    return new Creature.Builder().statistic( CreatureStatistic.BONE_DRAGON )
                        .amount( aAmount )
                        .build();
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
        else
        {
            switch( aTier )
            {
                case 1:
                    return new Creature.Builder().statistic( CreatureStatistic.SKELETON_WARRIOR )
                        .amount( aAmount )
                        .build();
                case 2:
                    return new Creature.Builder().statistic( CreatureStatistic.ZOMBIE )
                        .amount( aAmount )
                        .build();
                case 3:
                    return new Creature.Builder().statistic( CreatureStatistic.WRAITH )
                        .amount( aAmount )
                        .build();
                case 4:
                    Creature decorated = new Creature.Builder().statistic( CreatureStatistic.VAMPIRE_LORD )
                        .amount( aAmount )
                        .build();
                    Creature decoratedNoCounter = new NoCounterCreature( decorated );
                    return new HealFromAttackCreature( decoratedNoCounter );
                case 5:
                    return new Creature.Builder().statistic( CreatureStatistic.POWER_LICH )
                        .amount( aAmount )
                        .build();
                case 6:
                    return new Creature.Builder().statistic( CreatureStatistic.DREAD_KNIGHT )
                        .amount( aAmount )
                        .build();
                case 7:
                    return new Creature.Builder().statistic( CreatureStatistic.GHOST_DRAGON )
                        .amount( aAmount )
                        .build();
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
    }
}
