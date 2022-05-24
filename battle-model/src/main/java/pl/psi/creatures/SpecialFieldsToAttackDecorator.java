package pl.psi.creatures;

import com.google.common.collect.Range;

import java.beans.PropertyChangeEvent;
import java.util.Random;

public class SpecialFieldsToAttackDecorator extends Creature {

    private Creature decorated;
    private CreatureStatisticIf statistic;
    private DamageCalculatorIf calculator;
    private int amount;

    public SpecialFieldsToAttackDecorator(final Creature aDecorated) {
        decorated = aDecorated;
    }

    public SpecialFieldsToAttackDecorator(CreatureStatisticIf aStatistic, DamageCalculatorIf aCalculator, int aAmount) {
        Creature.Builder builder = new Creature.Builder();
        builder.statistic(aStatistic);
        builder.calculator(aCalculator);
        builder.amount(aAmount);

        decorated = builder.build();
        statistic = aStatistic;
        calculator = aCalculator;
        amount = aAmount;
    }

    public SpecialFieldsToAttackDecorator() {

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
    public double getCurrentHp() {
        return decorated.getCurrentHp();
    }

    @Override
    protected void setCurrentHp(final double aCurrentHp) {
        decorated.setCurrentHp(aCurrentHp);
    }

    @Override
    Range<Integer> getDamage() {
        return decorated.getDamage();
    }

    @Override
    double getAttack() {
        return decorated.getAttack();
    }

    @Override
    double getArmor() {
        return decorated.getArmor();
    }

    @Override
    public String getName() {
        return decorated.getName();
    }

    @Override
    protected boolean canCounterAttack( final Creature aDefender )
    {
        return false;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        decorated.propertyChange(evt);
    }


    public static class Builder {
        private int amount = 1;
        private DamageCalculatorIf calculator = new DefaultDamageCalculator(new Random());
        private CreatureStatisticIf statistic;

        public SpecialFieldsToAttackDecorator.Builder statistic(final CreatureStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }

        public SpecialFieldsToAttackDecorator.Builder amount(final int aAmount) {
            amount = aAmount;
            return this;
        }

        SpecialFieldsToAttackDecorator.Builder calculator(final DamageCalculatorIf aCalc) {
            calculator = aCalc;
            return this;
        }

        public SpecialFieldsToAttackDecorator build() {
            return new SpecialFieldsToAttackDecorator(statistic, calculator, amount);
        }
    }

}
