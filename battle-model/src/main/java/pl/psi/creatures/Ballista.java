package pl.psi.creatures;

import com.google.common.collect.Range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Ballista extends WarMachinesAbstract {


    public Ballista(CreatureStatisticIf aStatistic, DamageCalculatorIf aCalculator, int aAmount, int aSkillLevel) {
        stats = aStatistic;
        calculator = aCalculator;
        amount = aAmount;
        skillLevel = aSkillLevel;
    }


    @Override
    public void performAction(List<Creature> creatureList) {
        if (isAlive()) {

            List<Creature> creatures = new ArrayList<>(creatureList);
            Collections.shuffle(creatures);

            creatures.stream()
                    .filter(creature -> this.getHeroNumber() != creature.getHeroNumber())
                    .findAny()
                    .ifPresent(this::calculateAndApplyDamge);
        }
    }

    private void calculateAndApplyDamge(Creature aDefender) {
        final int damage = getCalculator().calculateDamage(this, aDefender);
        applyDamage(aDefender, damage);
    }

    protected void applyDamage(final Creature aDefender, final double aDamage) {
        aDefender.setCurrentHp(aDefender.getCurrentHp() - aDamage);
    }

    @Override
    double getAttack() {
        return stats.getAttack();
    }

    @Override
    public double getArmor() {
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
    public Range<Integer> getDamage() {
        return stats.getDamage();
    }


    public static class Builder {
        private int amount = 1;
        private int skillLevel;
        private DamageCalculatorIf calculator = new DefaultDamageCalculator(new Random());
        private CreatureStatisticIf statistic;

        public Ballista.Builder statistic(final CreatureStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }

        public Ballista.Builder amount(final int aAmount) {
            amount = aAmount;
            return this;
        }

        public Ballista.Builder skillLevel(final int aSkillLevel) {
            skillLevel = aSkillLevel;
            return this;
        }

        public Ballista.Builder calculator(final DamageCalculatorIf aCalc) {
            calculator = aCalc;
            return this;
        }

        public Ballista build() {
            return new Ballista(statistic, calculator, amount, skillLevel);
        }
    }
}
