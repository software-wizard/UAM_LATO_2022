package pl.psi.creatures;

import com.google.common.collect.Range;

import java.util.Random;

public class FirstAidTent extends WarMachinesAbstract {

    final private Random random = new Random();

    public FirstAidTent(CreatureStatisticIf aStatistic, DamageCalculatorIf aCalculator, int aAmount, int aSkillLevel) {
        stats = aStatistic;
        calculator = aCalculator;
        amount = aAmount;
        skillLevel = aSkillLevel;
    }


    @Override
    public void performAction(final Creature aDefender) {
        int aSkillLevel = getSkillLevel();
        int min = 1;
        int max;
        switch(aSkillLevel)
        {
            case 0:
                max = 25;
                break;
            case 1:
                max = 50;
                break;
            case 2:
                max = 75;
                break;
            case 3:
                max = 100;
                break;
            default:
                max = 0;
        }
        int heal = random.nextInt((max - min) + 1) + min;
        aDefender.setCurrentHp( aDefender.getCurrentHp() + heal );

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
        private int skillLevel;
        private DamageCalculatorIf calculator = new DefaultDamageCalculator(new Random());
        private CreatureStatisticIf statistic;

        public FirstAidTent.Builder statistic(final CreatureStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }

        public FirstAidTent.Builder skillLevel(final int aSkillLevel)
        {
            skillLevel = aSkillLevel;
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
            return new FirstAidTent(statistic, calculator, amount, skillLevel);
        }
    }
}
