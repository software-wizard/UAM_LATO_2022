package pl.psi.creatures;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class CastleCreatureFactory {
    public Creature create(final int aTier, final boolean aIsUpgraded, final int aAmount) {
        if (!aIsUpgraded) {
            switch (aTier) {
                case 1:
                    return new Creature.Builder().statistic(CreatureStatistic.PIKEMAN)
                            .amount(aAmount)
                            .build();
                case 2:
//                    Creature creature = new Creature.Builder().statistic(CreatureStatistic.ARCHER)
//                        .amount(aAmount)
//                        .build();
//                    return new ShooterCreature( creature, 12 );
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
                    return new ShooterCreatureDecorator( areaDamageOnAttackCreature, 24 );
                case 3:
                    return new Creature.Builder().statistic(CreatureStatistic.GRIFFIN)
                            .amount(aAmount)
                            .build();
                case 4:
                    return new Creature.Builder().statistic(CreatureStatistic.SWORDSMAN)
                            .amount(aAmount)
                            .build();
                case 5:
                    Creature creature2 = new Creature.Builder().statistic(CreatureStatistic.MONK)
                            .amount(aAmount)
                            .build();
                    return new ShooterCreatureDecorator( creature2, 12 );
            }
        } else {
            switch (aTier) {
                case 1:
                    return new Creature.Builder().statistic(CreatureStatistic.HALBERDIER)
                            .amount(aAmount)
                            .build();
                case 2:
                    Creature creature = new Creature.Builder().statistic(CreatureStatistic.MARKSMAN)
                            .amount(aAmount)
                            .build();
                    return new ShooterCreatureDecorator( creature, 24 );
                case 3:
                    return new Creature.Builder().statistic(CreatureStatistic.ROYAL_GRIFFIN)
                            .amount(aAmount)
                            .build();
                case 4:
                    return new Creature.Builder().statistic(CreatureStatistic.CRUSADER)
                            .amount(aAmount)
                            .build();
                case 5:
                    Creature creature2 = new Creature.Builder().statistic(CreatureStatistic.MONK)
                            .amount(aAmount)
                            .build();
                    return new ShooterCreatureDecorator( creature2, 24 );
            }
        }
        throw new IllegalArgumentException("Cannot recognize creature by tier and upgrade or not.");
    }
}
