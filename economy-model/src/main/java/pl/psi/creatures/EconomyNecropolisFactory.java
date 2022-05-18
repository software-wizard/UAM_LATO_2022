package pl.psi.creatures;

// this factory should delivery group
public class EconomyNecropolisFactory
{

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    public EconomyCreature create( final boolean aIsUpgraded, final int aTier, final int aAmount )
    {
        if( !aIsUpgraded )
        {
            switch( aTier )
            {
                case 1:
                    return new EconomyCreature( CreatureStatistic.SKELETON, aAmount, 100 );
                case 2:
                    return new EconomyCreature( CreatureStatistic.WALKING_DEAD, aAmount, 200 );
                case 3:
                    return new EconomyCreature( CreatureStatistic.WIGHT, aAmount, 300 );
                case 4:
                    return new EconomyCreature( CreatureStatistic.VAMPIRE, aAmount, 400 );
                case 5:
                    return new EconomyCreature( CreatureStatistic.LICH, aAmount, 500 );
                case 6:
                    return new EconomyCreature( CreatureStatistic.BLACK_KNIGHT, aAmount, 600 );
                case 7:
                    return new EconomyCreature( CreatureStatistic.BONE_DRAGON, aAmount, 700 );
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
        else
        {
            switch( aTier )
            {
                case 1:
                    return new EconomyCreature( CreatureStatistic.SKELETON_WARRIOR, aAmount, 200 );
                case 2:
                    return new EconomyCreature( CreatureStatistic.ZOMBIE, aAmount, 400 );
                case 3:
                    return new EconomyCreature( CreatureStatistic.WRAITH, aAmount, 600 );
                case 4:
                    return new EconomyCreature( CreatureStatistic.VAMPIRE_LORD, aAmount, 800 );
                case 5:
                    return new EconomyCreature( CreatureStatistic.POWER_LICH, aAmount, 1000 );
                case 6:
                    return new EconomyCreature( CreatureStatistic.DREAD_KNIGHT, aAmount, 1200 );
                case 7:
                    return new EconomyCreature( CreatureStatistic.GHOST_DRAGON, aAmount, 1400 );
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
    }
}
