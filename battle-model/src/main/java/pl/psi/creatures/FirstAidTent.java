package pl.psi.creatures;

import java.util.Random;

public class FirstAidTent extends WarMachinesAbstract {

    public FirstAidTent(CreatureStatisticIf statistic, DamageCalculatorIf calculator, int amount) {
        super();
    }

    public FirstAidTent() {
    }

    @Override
    public void performAction(final Creature aDefender) {

    }

    public static class Builder {
        private int amount = 1;
        private DamageCalculatorIf calculator = new DefaultDamageCalculator(new Random());
        private CreatureStatisticIf statistic;

        public FirstAidTent.Builder statistic(final CreatureStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }

        public FirstAidTent.Builder amount(final int aAmount) {
            amount = aAmount;
            return this;
        }

        FirstAidTent.Builder calculator(final DamageCalculatorIf aCalc) {
            calculator = aCalc;
            return this;
        }

        public FirstAidTent build() {
            return new FirstAidTent(statistic, calculator, amount);
        }
    }
}
