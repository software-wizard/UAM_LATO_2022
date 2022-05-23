package pl.psi.creatures;

import com.google.common.collect.Range;

import java.beans.PropertyChangeEvent;
import java.util.Random;

public class WarMachinesCreatureDecorator extends Creature {

    private Creature decorated;
    private CreatureStatisticIf statistic;
    private DamageCalculatorIf calculator;
    private int amount;

    public WarMachinesCreatureDecorator(final Creature aDecorated) {
        decorated = aDecorated;
    }

    public WarMachinesCreatureDecorator(CreatureStatisticIf aStatistic, DamageCalculatorIf aCalculator, int aAmount) {
        Creature.Builder builder = new Creature.Builder();
        builder.statistic(aStatistic);
        builder.calculator(aCalculator);
        builder.amount(aAmount);

        decorated = builder.build();
        statistic = aStatistic;
        calculator = aCalculator;
        amount = aAmount;
    }

    public WarMachinesCreatureDecorator() {

    }

    @Override
    public void attack(Creature aDefender) {
        decorated.attack(aDefender);
    }

    @Override
    public boolean isAlive() {
        return decorated.isAlive();
    }

    @Override
    public int getCurrentHp() {
        return decorated.getCurrentHp();
    }

    @Override
    protected void setCurrentHp(final int aCurrentHp) {
        decorated.setCurrentHp(aCurrentHp);
    }

    @Override
    Range<Integer> getDamage() {
        return decorated.getDamage();
    }

    @Override
    int getAttack() {
        return decorated.getAttack();
    }

    @Override
    int getArmor() {
        return decorated.getArmor();
    }

    @Override
    public String getName() {
        return decorated.getName();
    }

    @Override
    public int getCounterAttackCounter() {
        return 0;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        decorated.propertyChange(evt);
    }
    //heal do namiotu

    public static class Builder {
        private int amount = 1;
        private DamageCalculatorIf calculator = new DefaultDamageCalculator(new Random());
        private CreatureStatisticIf statistic;

        public WarMachinesCreatureDecorator.Builder statistic(final CreatureStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }

        public WarMachinesCreatureDecorator.Builder amount(final int aAmount) {
            amount = aAmount;
            return this;
        }

        WarMachinesCreatureDecorator.Builder calculator(final DamageCalculatorIf aCalc) {
            calculator = aCalc;
            return this;
        }

        public WarMachinesCreatureDecorator build() {
            return new WarMachinesCreatureDecorator(statistic, calculator, amount);
        }
    }

}
