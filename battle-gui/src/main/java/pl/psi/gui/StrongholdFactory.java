package pl.psi.gui;

import pl.psi.creatures.*;
import pl.psi.spells.SpellRang;

public class StrongholdFactory {

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    public Creature create(final boolean aIsUpgraded, final int aTier, final int aAmount) {
        if (!aIsUpgraded) {
            switch (aTier) {
                case 1:
                    return new Creature.Builder().statistic(CreatureStatistic.GOBLIN)
                            .amount(aAmount)
                            .build();
                case 2:
                    return new Creature.Builder().statistic(CreatureStatistic.WOLF_RIDER)
                            .amount(aAmount)
                            .build();
                case 3:
                    Creature orc = new Creature.Builder().statistic(CreatureStatistic.ORC)
                            .amount(aAmount)
                            .build();
                    return new ShooterCreature(orc, 12);
                case 4:
                    Creature decorated = new Creature.Builder().statistic(CreatureStatistic.OGRE)
                            .amount(aAmount)
                            .build();
                    return new NoCounterCreature(decorated);
                case 5:
                    return new Creature.Builder().statistic(CreatureStatistic.ROC)
                            .amount(aAmount)
                            .build();
                case 6:
                    Creature cyclops = new Creature.Builder().statistic(CreatureStatistic.CYCLOPS)
                            .amount(aAmount)
                            .build();
                    return new ShooterCreature(cyclops, 16);
                case 7:
                    Creature ancientBehemoth = new Creature.Builder().statistic(CreatureStatistic.ANCIENT_BEHEMOTH)
                            .amount(aAmount)
                            .build();
                    return new DefenceReductionCreature(ancientBehemoth, 0.6);
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        } else {
            switch (aTier) {
                case 1:
                    return new Creature.Builder().statistic(CreatureStatistic.HOBGOBLIN)
                            .amount(aAmount)
                            .build();
                case 2:
                    Creature wolfRaider = new Creature.Builder().statistic(CreatureStatistic.WOLF_RAIDER)
                            .amount(aAmount)
                            .build();
                    return new DoubleAttackCreature(wolfRaider);
                case 3:
                    Creature orcChieftain = new Creature.Builder().statistic(CreatureStatistic.ORC_CHIEFTAIN)
                            .amount(aAmount)
                            .build();
                    return new ShooterCreature(orcChieftain, 24);
                case 4:
                    Creature creature = new Creature.Builder().statistic(CreatureStatistic.OGRE_MAGI)
                            .amount(aAmount)
                            .build();

                    return new CanCastSpellCreatureDecorator(creature,"Bloodlust",3,6, SpellRang.ADVANCED);
                case 5:
                    Creature thunderbird = new Creature.Builder().statistic(CreatureStatistic.THUNDERBIRD)
                            .amount(aAmount)
                            .build();
                    return new ThunderboltOnHitCreature(thunderbird);
                case 6:
                    Creature cyclopsKing = new Creature.Builder().statistic(CreatureStatistic.CYCLOPS_KING)
                            .amount(aAmount)
                            .build();
                    return new ShooterCreature(cyclopsKing, 24);
                case 7:
                    Creature ancientBehemoth = new Creature.Builder().statistic(CreatureStatistic.ANCIENT_BEHEMOTH)
                            .amount(aAmount)
                            .build();
                    return new DefenceReductionCreature(ancientBehemoth, 0.2);
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        }
    }
}
