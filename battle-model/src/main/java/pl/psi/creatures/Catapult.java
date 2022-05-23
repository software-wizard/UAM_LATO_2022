package pl.psi.creatures;

import com.google.common.collect.Range;

import java.util.Random;

public class Catapult extends WarMachinesAbstract{

    public Catapult(CreatureStatisticIf aStatistic, DamageCalculatorIf aCalculator, int aAmount, int aSkillLevel) {
        stats = aStatistic;
        calculator = aCalculator;
        amount = aAmount;
        aSkillLevel = aSkillLevel;
    }


    public Catapult() {

    }

    @Override
    public void performAction(final Creature aDefender) {
        if( isAlive() )
        {
            final int damage = getCalculator().calculateDamage( this, aDefender );
            applyDamage( aDefender, damage );
        }
    }

    private void applyDamage(final Creature aDefender, final int aDamage )
    {
        aDefender.setCurrentHp( aDefender.getCurrentHp() - aDamage );
    }

    @Override
    int getAttack() {
        return stats.getAttack();
    }

    @Override
    int getArmor() {
        return stats.getArmor();
    }

    @Override
    public String getName() {
        return stats.getName();
    }

    @Override
    public int getMoveRange() {
        return stats.getMoveRange();
    }

    @Override
    public Range<Integer> getDamage()
    {
        return stats.getDamage();
    }

    @Override
    public int getCounterAttackCounter() {
        return 0;
    }

    public static class Builder {
        private int amount = 1;
        private DamageCalculatorIf calculator = new DefaultDamageCalculator(new Random());
        private CreatureStatisticIf statistic;
        private int skillLevel;

        public Catapult.Builder statistic(final CreatureStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }

        public Catapult.Builder amount(final int aAmount) {
            amount = aAmount;
            return this;
        }

        public Catapult.Builder calculator(final DamageCalculatorIf aCalc) {
            calculator = aCalc;
            return this;
        }

        public Catapult.Builder skillLevel(final int aSkillLevel)
        {
            skillLevel = aSkillLevel;
            return this;
        }


        public Catapult build() {
            return new Catapult(statistic, calculator, amount, skillLevel);
        }
    }
}
