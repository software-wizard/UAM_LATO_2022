package pl.psi.creatures;

import java.util.List;
import java.util.Random;

public class AmmoCart extends WarMachinesAbstract {

    public AmmoCart(CreatureStatisticIf aStatistic, DamageCalculatorIf aCalculator, int aAmount, int aSkillLevel) {
        stats = aStatistic;
        calculator = aCalculator;
        amount = aAmount;
        skillLevel = aSkillLevel;
    }

    @Override
    public void performAction(List<Creature> creatureList) {
        if (isAlive()) {
            creatureList.stream()
                    .filter(creature -> this.getHeroNumber() == creature.getHeroNumber())
                    .filter(creature -> creature instanceof ShooterCreature)
                    .map(ShooterCreature.class::cast)
                    .forEach(ShooterCreature::resetShots);
        }
    }

    public static class Builder {
        private int amount = 1;
        private int skillLevel;
        private DamageCalculatorIf calculator = new DefaultDamageCalculator(new Random());
        private CreatureStatisticIf statistic;

        public AmmoCart.Builder statistic(final CreatureStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }

        public AmmoCart.Builder skillLevel(final int aSkillLevel) {
            skillLevel = aSkillLevel;
            return this;
        }

        public AmmoCart.Builder amount(final int aAmount) {
            amount = aAmount;
            return this;
        }

        AmmoCart.Builder calculator(final DamageCalculatorIf aCalc) {
            calculator = aCalc;
            return this;
        }

        public AmmoCart build() {
            return new AmmoCart(statistic, calculator, amount, skillLevel);
        }
    }
}
