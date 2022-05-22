package pl.psi.gui;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.creatures.NoCounterCreature;

public class StrongholdFactory
{

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    public Creature create( final boolean aIsUpgraded, final int aTier, final int aAmount )
    {
        if( !aIsUpgraded )
        {
            switch( aTier )
            {
                case 1:
                    return new Creature.Builder().statistic( CreatureStatistic.GOBLIN )
                            .amount( aAmount )
                            .build();
                case 2:
                    return new Creature.Builder().statistic( CreatureStatistic.WOLF_RIDER )
                            .amount( aAmount )
                            .build();
                case 3:
                    return new Creature.Builder().statistic( CreatureStatistic.ORC )
                            .amount( aAmount )
                            .build();
                case 4:
                    Creature decorated = new Creature.Builder().statistic( CreatureStatistic.OGRE )
                            .amount( aAmount )
                            .build();
                    return new NoCounterCreature( decorated );
                case 5:
                    return new Creature.Builder().statistic( CreatureStatistic.ROC )
                            .amount( aAmount )
                            .build();
                case 6:
                    return new Creature.Builder().statistic( CreatureStatistic.CYCLOPS )
                            .amount( aAmount )
                            .build();
                case 7:
                    return new Creature.Builder().statistic( CreatureStatistic.BEHEMOTH )
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
                    return new Creature.Builder().statistic( CreatureStatistic.HOBGOBLIN )
                            .amount( aAmount )
                            .build();
                case 2:
                    return new Creature.Builder().statistic( CreatureStatistic.WOLF_RAIDER )
                            .amount( aAmount )
                            .build();
                case 3:
                    return new Creature.Builder().statistic( CreatureStatistic.ORC_CHIEFTAIN )
                            .amount( aAmount )
                            .build();
                case 4:
                    return new Creature.Builder().statistic( CreatureStatistic.OGRE_MAGI )
                            .amount( aAmount )
                            .build();
                case 5:
                    return new Creature.Builder().statistic( CreatureStatistic.THUNDERBIRD )
                            .amount( aAmount )
                            .build();
                case 6:
                    return new Creature.Builder().statistic( CreatureStatistic.CYCLOPS_KING )
                            .amount( aAmount )
                            .build();
                case 7:
                    return new Creature.Builder().statistic( CreatureStatistic.ANCIENT_BEHEMOTH )
                            .amount( aAmount )
                            .build();
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
    }
}
