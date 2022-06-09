package pl.psi.creatures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Catapult extends WarMachinesAbstract {

    public Catapult(CreatureStatisticIf aStatistic, DamageCalculatorIf aCalculator, int aAmount, int aSkillLevel) {
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
                    .filter(creature -> creature instanceof SpecialFieldsToAttackDecorator)
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

        public Catapult.Builder skillLevel(final int aSkillLevel) {
            skillLevel = aSkillLevel;
            return this;
        }


        public Catapult build() {
            return new Catapult(statistic, calculator, amount, skillLevel);
        }
    }
}
