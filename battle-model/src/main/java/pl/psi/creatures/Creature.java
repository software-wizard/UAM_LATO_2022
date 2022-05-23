package pl.psi.creatures;//  ******************************************************************

//
//  Copyright 2022 PSI Software AG. All rights reserved.
//  PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
//  ******************************************************************

import com.google.common.collect.Range;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.psi.TurnQueue;
import pl.psi.spells.Spell;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
@ToString
public class Creature implements PropertyChangeListener {
    private CreatureStatisticIf stats;
    private CreatureStatisticIf statsWithSpells;
    private int amount;
    private int currentHp;
    private int counterAttackCounter = 1;
    private DamageCalculatorIf calculator;

    Creature() {
    }

    private Creature(final CreatureStatisticIf aStats, final DamageCalculatorIf aCalculator,
                     final int aAmount) {
        stats = aStats;
        amount = aAmount;
        currentHp = stats.getMaxHp();
        calculator = aCalculator;
    }

    public void attack(final Creature aDefender) {
        if (isAlive()) {
            final int damage = getCalculator().calculateDamage(this, aDefender);
            applyDamage(aDefender, damage);
            if (canCounterAttack(aDefender)) {
                counterAttack(aDefender);
            }
        }
    }

    public void castSpell(final Creature aDefender, Spell spell) {
        if (isAlive())
            spell.castSpell(aDefender);
    }

    public void castSpell(final List<Creature> aDefender, Spell spell) {
        if (isAlive()) {
            spell.castSpell(aDefender);
        }
    }

    public boolean isAlive() {
        return getAmount() > 0;
    }

    private void applyDamage(final Creature aDefender, final int aDamage) {
        aDefender.setCurrentHp(aDefender.getCurrentHp() - aDamage);
    }

    public void applySpellDamage(Creature aDefender, Integer damage) {
        aDefender.setCurrentHp(aDefender.getCurrentHp() - damage); // ToDo: include magic resist
    }

    protected void setCurrentHp(final int aCurrentHp) {
        currentHp = aCurrentHp;
    }

    private boolean canCounterAttack(final Creature aDefender) {
        return aDefender.getCounterAttackCounter() > 0 && aDefender.getCurrentHp() > 0;
    }

    private void counterAttack(final Creature aAttacker) {
        final int damage = aAttacker.getCalculator()
                .calculateDamage(aAttacker, this);
        applyDamage(this, damage);
        aAttacker.counterAttackCounter--;
    }

    Range<Integer> getDamage() {
        return stats.getDamage();
    }

    int getAttack() {
        return stats.getAttack();
    }

    int getArmor() {
        return stats.getArmor();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (TurnQueue.END_OF_TURN.equals(evt.getPropertyName())) {
            counterAttackCounter = 1;
        }
    }

    protected void restoreCurrentHpToMax() {
        currentHp = stats.getMaxHp();
    }

    public String getName() {
        return stats.getName();
    }

    public int getMoveRange() {
        return stats.getMoveRange();
    }

    public void applyStatsWithSpells(CreatureStats aCreatureStats) {
        setStatsWithSpells(CreatureStats.builder()
                .attack((statsWithSpells == null) ? aCreatureStats.getAttack() : statsWithSpells.getAttack() + aCreatureStats.getAttack() )
                .armor((statsWithSpells == null) ? aCreatureStats.getArmor() : statsWithSpells.getArmor() + aCreatureStats.getArmor() )
                .moveRange((statsWithSpells == null) ? aCreatureStats.getMoveRange() : statsWithSpells.getMoveRange() + aCreatureStats.getMoveRange() )
                .build());
    }

    private void setStatsWithSpells(CreatureStatisticIf aStatsWithSpells) {
        statsWithSpells = aStatsWithSpells;
    }

    public static class Builder {
        private int amount = 1;
        private DamageCalculatorIf calculator = new DefaultDamageCalculator(new Random());
        private CreatureStatisticIf statistic;

        public Builder statistic(final CreatureStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }

        public Builder amount(final int aAmount) {
            amount = aAmount;
            return this;
        }

        Builder calculator(final DamageCalculatorIf aCalc) {
            calculator = aCalc;
            return this;
        }

        public Creature build() {
            return new Creature(statistic, calculator, amount);
        }
    }
}
