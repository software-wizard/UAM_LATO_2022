package pl.psi.gui;

import pl.psi.creatures.*;

public class NecropolisFactory {

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    public Creature create(final boolean aIsUpgraded, final int aTier, final int aAmount) {
        if (!aIsUpgraded) {
            switch (aTier) {
                case 1:
                    return new Creature.Builder().statistic(CreatureStatistic.SKELETON)
                            .amount(aAmount)
                            .build();
                case 2:
                    return new Creature.Builder().statistic(CreatureStatistic.WALKING_DEAD)
                            .amount(aAmount)
                            .build();
                case 3:
                    Creature wight = new Creature.Builder().statistic(CreatureStatistic.WIGHT)
                            .amount(aAmount)
                            .build();
                    return new SelfHealAfterTurnCreature(wight);
                case 4:
                    Creature decorated = new Creature.Builder().statistic(CreatureStatistic.VAMPIRE)
                            .amount(aAmount)
                            .build();
                    return new NoCounterCreature(decorated);
                case 5:
                    return new Creature.Builder().statistic(CreatureStatistic.LICH)
                            .amount(aAmount)
                            .build();
                case 6:
                    Creature blackKnight = new Creature.Builder().statistic(CreatureStatistic.BLACK_KNIGHT)
                            .amount(aAmount)
                            .build();
                    return new CurseOnHitCreature(blackKnight);
                case 7:
                    return new Creature.Builder().statistic(CreatureStatistic.BONE_DRAGON)
                            .amount(aAmount)
                            .build();
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        } else {
            switch (aTier) {
                case 1:
                    return new Creature.Builder().statistic(CreatureStatistic.SKELETON_WARRIOR)
                            .amount(aAmount)
                            .build();
                case 2:
                    Creature zombie = new Creature.Builder().statistic(CreatureStatistic.ZOMBIE)
                            .amount(aAmount)
                            .build();
                    return new DiseaseOnHitCreature(zombie);
                case 3:
                    Creature wraith = new Creature.Builder().statistic(CreatureStatistic.WRAITH)
                            .amount(aAmount)
                            .build();
                    return new SelfHealAfterTurnCreature(wraith);
                case 4:
                    Creature decorated = new Creature.Builder().statistic(CreatureStatistic.VAMPIRE_LORD)
                            .amount(aAmount)
                            .build();
                    Creature decoratedNoCounter = new NoCounterCreature(decorated);
                    return new HealFromAttackCreature(decoratedNoCounter);
                case 5:
                    return new Creature.Builder().statistic(CreatureStatistic.POWER_LICH)
                            .amount(aAmount)
                            .build();
                case 6:
                    Creature blackKnight = new Creature.Builder().statistic(CreatureStatistic.DREAD_KNIGHT)  // if making dread knight by hand remember to first do double damage then curse on hit
                            .amount(aAmount)
                            .build();
                    DoubleDamageOnHitCreature dreadKnight = new DoubleDamageOnHitCreature(blackKnight);
                    return new CurseOnHitCreature(dreadKnight);
                case 7:
                    Creature ghostDragon = new Creature.Builder().statistic(CreatureStatistic.GHOST_DRAGON)
                            .amount(aAmount)
                            .build();
                    return new AgeOnHitCreature(ghostDragon);
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        }
    }
}
