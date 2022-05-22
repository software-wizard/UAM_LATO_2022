package pl.psi.creatures;//  ******************************************************************

//
//  Copyright 2022 PSI Software AG. All rights reserved.
//  PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
//  ******************************************************************

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import pl.psi.TurnQueue;

import com.google.common.collect.Range;

import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
public class Creature implements PropertyChangeListener
{
    private CreatureStatisticIf stats;
    private int amount;
    private int currentHp;
    private int counterAttackCounter = 1;
    private int morale = 1; // range = < -3;3 >
    private int defense;
    private int luck;
    private Alignment alignment;
    private DamageCalculatorIf calculator;

    Creature()
    {
    }

    private Creature( final CreatureStatisticIf aStats, final DamageCalculatorIf aCalculator,
                      final int aAmount, final Alignment aAlignment, final int aDefense,
                      final int aLuck )
    {
        stats = aStats;
        amount = aAmount;
        currentHp = stats.getMaxHp();
        calculator = aCalculator;
        alignment = aAlignment;
        defense = aDefense;
        luck = aLuck;
    }

    public void attack( final Creature aDefender )
    {
        if( isAlive() )
        {
            final int damage = getCalculator().calculateDamage( this, aDefender );
            applyDamage( aDefender, damage );
            if( canCounterAttack( aDefender ) )
            {
                counterAttack( aDefender );
            }
        }
    }

    public boolean isAlive()
    {
        return getAmount() > 0;
    }

    private void applyDamage( final Creature aDefender, final int aDamage )
    {
        aDefender.setCurrentHp( aDefender.getCurrentHp() - aDamage );
    }

    public void reduceDefenseBy(int factor) {
        setDefense(defense - factor);
    }

    public void increaseLuckBy(int factor) {
        setLuck(luck + factor);
    }

    protected void setCurrentHp( final int aCurrentHp )
    {
        currentHp = aCurrentHp;
    }

    protected void setDefense(int aDefense) {
        defense = aDefense;
    }

    protected void setLuck(int aLuck) {
        luck = aLuck;
    }

    public void setMorale(final int aMorale) {
        if (aMorale > 3) {
            throw new IllegalArgumentException("Morale must not be greater than 3");
        }

        if (aMorale < -3) {
            throw new IllegalArgumentException("Morale must not be less than 3");
        }

        morale = aMorale;
    }

    private boolean canCounterAttack( final Creature aDefender )
    {
        return aDefender.getCounterAttackCounter() > 0 && aDefender.getCurrentHp() > 0;
    }

    private void counterAttack( final Creature aAttacker )
    {
        final int damage = aAttacker.getCalculator()
            .calculateDamage( aAttacker, this );
        applyDamage( this, damage );
        aAttacker.counterAttackCounter--;
    }

    Range< Integer > getDamage()
    {
        return stats.getDamage();
    }

    int getAttack()
    {
        return stats.getAttack();
    }

    int getArmor()
    {
        return stats.getArmor();
    }

    @Override
    public void propertyChange( final PropertyChangeEvent evt )
    {
        if( TurnQueue.END_OF_TURN.equals( evt.getPropertyName() ) )
        {
            counterAttackCounter = 1;
        }
    }

    protected void restoreCurrentHpToMax()
    {
        currentHp = stats.getMaxHp();
    }

    public String getName()
    {
        return stats.getName();
    }

    public int getMoveRange()
    {
        return stats.getMoveRange();
    }

    public static class Builder
    {
        private int amount = 1;
        private DamageCalculatorIf calculator = new DefaultDamageCalculator( new Random() );
        private int defense = 10;
        private int luck = 10;
        private Alignment alignment = Alignment.NEUTRAL;
        private CreatureStatisticIf statistic;

        public Builder statistic( final CreatureStatisticIf aStatistic )
        {
            statistic = aStatistic;
            return this;
        }

        public Builder amount( final int aAmount )
        {
            amount = aAmount;
            return this;
        }

        public Builder alignment( final Alignment aAlignment )
        {
            alignment = aAlignment;
            return this;
        }

        public Builder defense( final int aDefense )
        {
            defense = aDefense;
            return this;
        }

        public Builder luck( final int aLuck )
        {
            luck = aLuck;
            return this;
        }

        Builder calculator( final DamageCalculatorIf aCalc )
        {
            calculator = aCalc;
            return this;
        }

        public Creature build()
        {
            return new Creature( statistic, calculator, amount, alignment, defense, luck );
        }
    }
}
