package pl.psi.creatures;

public class EconomyStrongholdFactory {

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    public EconomyCreature create( final boolean aIsUpgraded, final int aTier, final int aAmount )
    {
        if( !aIsUpgraded )
        {
            switch( aTier )
            {
                case 1:
                    return new EconomyCreature( CreatureStatistic.GOBLIN, aAmount, 40 );
                case 2:
                    return new EconomyCreature( CreatureStatistic.WOLF_RIDER, aAmount, 100 );
                case 3:
                    return new EconomyCreature( CreatureStatistic.ORC, aAmount, 150 );
                case 4:
                    return new EconomyCreature( CreatureStatistic.OGRE, aAmount, 300 );
                case 5:
                    return new EconomyCreature( CreatureStatistic.ROC, aAmount, 600 );
                case 6:
                    return new EconomyCreature( CreatureStatistic.CYCLOPS, aAmount, 750 );
                case 7:
                    return new EconomyCreature( CreatureStatistic.BEHEMOTH, aAmount, 1500 );
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
        else
        {
            switch( aTier )
            {
                case 1:
                    return new EconomyCreature( CreatureStatistic.HOBGOBLIN, aAmount, 50 );
                case 2:
                    return new EconomyCreature( CreatureStatistic.WOLF_RAIDER, aAmount, 140 );
                case 3:
                    return new EconomyCreature( CreatureStatistic.ORC_CHIEFTAIN, aAmount, 165 );
                case 4:
                    return new EconomyCreature( CreatureStatistic.OGRE_MAGI, aAmount, 400 );
                case 5:
                    return new EconomyCreature( CreatureStatistic.THUNDERBIRD, aAmount, 700 );
                case 6:
                    return new EconomyCreature( CreatureStatistic.CYCLOPS_KING, aAmount, 1100 );
                case 7:
                    return new EconomyCreature( CreatureStatistic.ANCIENT_BEHEMOTH, aAmount, 3000 );
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
    }
}
