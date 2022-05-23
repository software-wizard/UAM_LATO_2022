package pl.psi.creatures;

import com.google.common.collect.Range;

import java.util.*;

public class FirstAidTent extends WarMachinesAbstract {

    final private Random random = new Random();

    public FirstAidTent(CreatureStatisticIf aStatistic, DamageCalculatorIf aCalculator, int aAmount, int aSkillLevel) {
        stats = aStatistic;
        calculator = aCalculator;
        amount = aAmount;
        skillLevel = aSkillLevel;
    }


    @Override
    public void performAction(List<Creature> creatureList) {
        if (isAlive()) {

            int maxHp = calculateHealHp(getSkillLevel());
            double heal = random.nextInt(maxHp - 1) + 1;

            List<Creature> creatures = new ArrayList<>(creatureList);
            Collections.shuffle(creatures);

            creatures.stream()
                    .filter(creature -> this.getHeroNumber() == creature.getHeroNumber())
                    .filter(Creature::isAlive)
                    .findAny()
                    .ifPresent(creature -> creature.heal(heal));
        }

    }

    private int calculateHealHp (int skillLevel) {
        switch(skillLevel)
        {
            case 0:
                return  25;
            case 1:
                return  50;
            case 2:
                return  75;
            case 3:
                return  100;
            default:
                return  0;
        }
    }

    @Override
    double getAttack() {
        return stats.getAttack();
    }

    @Override
    double getArmor() {
        return stats.getArmor();
    }

    @Override
    public String getName() {
        return stats.getName();
    }

    @Override
    public double getMoveRange() {
        return stats.getMoveRange();
    }

    @Override
    public Range<Integer> getDamage()
    {
        return stats.getDamage();
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
