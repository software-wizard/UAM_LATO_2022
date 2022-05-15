package pl.psi.creatures;

public class EconomyCastleFactory
{

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    public EconomyCreature create( final boolean aIsUpgraded, final int aTier, final int aAmount )
    {
        if( !aIsUpgraded )
        {
            switch( aTier )
            {
                case 1:
                    return new EconomyCreature( CreatureStatistic.PIKEMAN, aAmount, 60 );
                case 2:
                    // TODO
                    // add archer
                    return new EconomyCreature( CreatureStatistic.GRIFFIN, aAmount, 200 );
                case 3:
                    return new EconomyCreature( CreatureStatistic.SWORDSMAN, aAmount, 300 );
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
        else
        {
            switch( aTier )
            {
                case 1:
                    return new EconomyCreature( CreatureStatistic.HALBERDIER, aAmount, 75 );
                case 2:
                    return new EconomyCreature( CreatureStatistic.ROYAL_GRIFFIN, aAmount, 240 );
                case 3:
                    return new EconomyCreature( CreatureStatistic.CRUSADER, aAmount, 400 );
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
    }
}
