package pl.psi.gui;

import pl.psi.creatures.*;

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
                    Creature wight = new Creature.Builder().statistic( CreatureStatistic.WIGHT )
                            .amount( aAmount )
                            .build();
                    return new SelfHealAfterTurnCreatureDecorator( wight );
                case 4:
                    Creature decorated = new Creature.Builder().statistic( CreatureStatistic.VAMPIRE )
                            .amount( aAmount )
                            .build();
                    return new NoCounterCreatureDecorator( decorated );
                case 5:
                    Creature creature = new Creature.Builder().statistic( CreatureStatistic.LICH )
                            .amount( aAmount )
                            .build();
                    Integer[][] area = new Integer[3][3];
                    for(int i = 0; i<3;i++){
                        for(int j = 0; j<3;j++){
                            area[i][j] = 1;
                        }
                    }
                    AreaDamageOnAttackCreatureDecorator areaDamageOnAttackCreature = new AreaDamageOnAttackCreatureDecorator( creature,area );
                    return new LichDecorator( areaDamageOnAttackCreature,12);
                case 6:
                    Creature blackKnight = new Creature.Builder().statistic( CreatureStatistic.BLACK_KNIGHT )
                            .amount( aAmount )
                            .build();
                    return new CurseOnHitCreatureDecorator( blackKnight );
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
                    Creature zombie = new Creature.Builder().statistic( CreatureStatistic.ZOMBIE )
                            .amount( aAmount )
                            .build();
                    return new DiseaseOnHitCreatureDecorator( zombie );
                case 3:
                    Creature wraith = new Creature.Builder().statistic( CreatureStatistic.WRAITH )
                            .amount( aAmount )
                            .build();
                    return new SelfHealAfterTurnCreatureDecorator( wraith );
                case 4:
                    Creature decorated = new Creature.Builder().statistic( CreatureStatistic.VAMPIRE_LORD )
                            .amount( aAmount )
                            .build();
                    Creature decoratedNoCounter = new NoCounterCreatureDecorator( decorated );
                    return new HealFromAttackCreatureDecorator( decoratedNoCounter );
                case 5:
                    Creature creature = new Creature.Builder().statistic( CreatureStatistic.POWER_LICH )
                            .amount( aAmount )
                            .build();
                    Integer[][] area = new Integer[3][3];
                    for(int i = 0; i<3;i++){
                        for(int j = 0; j<3;j++){
                            area[i][j] = 1;
                        }
                    }
                    AreaDamageOnAttackCreatureDecorator areaDamageOnAttackCreature = new AreaDamageOnAttackCreatureDecorator( creature,area );
                    return new LichDecorator( areaDamageOnAttackCreature,24);
                case 6:
                    Creature blackKnight = new Creature.Builder().statistic( CreatureStatistic.DREAD_KNIGHT )  // if making dread knight by hand remember to first do double damage then curse on hit
                            .amount( aAmount )
                            .build();
                    DoubleDamageOnHitCreatureDecorator dreadKnight = new DoubleDamageOnHitCreatureDecorator( blackKnight );
                    return new CurseOnHitCreatureDecorator( dreadKnight );
                case 7:
                    Creature ghostDragon = new Creature.Builder().statistic( CreatureStatistic.GHOST_DRAGON )
                            .amount( aAmount )
                            .build();
                    return new AgeOnHitCreatureDecorator( ghostDragon );
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
    }
}