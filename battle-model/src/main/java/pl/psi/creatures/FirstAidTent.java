package pl.psi.creatures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    public void performAction(List<Creature> creatureList) {
        if (isAlive()) {

            int maxHp = calculateHealHp(getSkillLevel());
            double hp = random.nextInt(maxHp - 1) + 1;

            List<Creature> creatures = new ArrayList<>(creatureList);
            Collections.shuffle(creatures);

            creatures.stream()
                    .filter(creature -> this.getHeroNumber() == creature.getHeroNumber())
                    .filter(Creature::isAlive)
                    .findAny()
                    .ifPresent(creature -> creature.heal(hp));
        }

    }

    public void healCreature(Creature creature) {
        int maxHp = calculateHealHp(getSkillLevel());
        double hp = random.nextInt(maxHp - 1) + 1;

        creature.heal(hp);
    }

    private int calculateHealHp(int skillLevel) {
        switch (skillLevel) {
            case 0:
                return 25;
            case 1:
                return 50;
            case 2:
                return 75;
            case 3:
                return 100;
            default:
                return 0;
        }
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

        public FirstAidTent.Builder skillLevel(final int aSkillLevel) {
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
