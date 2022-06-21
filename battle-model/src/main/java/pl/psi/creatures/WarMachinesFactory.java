package pl.psi.creatures;

public class WarMachinesFactory implements WarMachinesIf<AbstractWarMachines> {

    @Override
    public AbstractWarMachines create(
            final int aTier,
            final int aAmount,
            final DamageCalculatorIf aCalculator,
            int aSkillLevel
    ) {

        switch (aTier) {
            case 1:
                return new Ballista(
                        new Creature.
                                Builder().statistic(WarMachinesStatistic.BALLISTA)
                                .amount(1)
                                .calculator(aCalculator)
                                .build(),
                        WarMachineActionType.ATTACK_CREATURE,aSkillLevel
                );
            case 2:
                return new FirstAidTent(
                        new Creature.
                                Builder().
                                statistic(WarMachinesStatistic.FIRST_AID_TENT)
                                .amount(1)
                                .calculator(aCalculator)
                                .build(),
                        WarMachineActionType.HEAL_CREATURE,aSkillLevel);
            case 3:
                return new Catapult(
                        new Creature.
                                Builder().
                                statistic(WarMachinesStatistic.CATAPULT)
                                .amount(1)
                                .calculator(aCalculator)
                                .build(),
                        WarMachineActionType.ATTACK_STRUCTURE,aSkillLevel
                );
            case 4:
                return new AmmoCart(
                        new Creature.
                                Builder().
                                statistic(WarMachinesStatistic.AMMO_CART)
                                .amount(1)
                                .calculator(aCalculator)
                                .build(),
                        WarMachineActionType.NO_ACTION,aSkillLevel
                );
            default:
                throw new IllegalArgumentException("Illegal Argument Exception");
        }

    }
}
