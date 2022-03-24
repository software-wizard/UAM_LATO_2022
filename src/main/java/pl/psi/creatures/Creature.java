package pl.psi.creatures;//  ******************************************************************
//
//  Copyright 2022 PSI Software AG. All rights reserved.
//  PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
//  ******************************************************************

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import lombok.Getter;

import pl.psi.TurnQueue;

import com.google.common.collect.Range;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
public class Creature implements PropertyChangeListener
{
    private CreatureStatistic stats;
    private int amount;
    private int currentHp;
    private int counterAttackCounter = 1;
    private DamageCalculatorIf calculator;

    Creature()
    {
    }

    private Creature( final CreatureStatistic aStats, final DamageCalculatorIf aCalculator,
        final int aAmount )
    {
        stats = aStats;
        amount = aAmount;
        currentHp = stats.getMaxHp();
        calculator = aCalculator;
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

    protected void setCurrentHp( final int aCurrentHp )
    {
        currentHp = aCurrentHp;
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
        return stats.getDefence();
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

    static class Builder
    {
        private String name;
        private int amount = 1;
        private int hp;
        private int attack;
        private Range< Integer > damage = Range.closed( 0, 0 );
        private int defence;
        private DamageCalculatorIf calculator = new DefaultDamageCalculator( new Random() );
        private int moveRange;
        private int tier;
        private boolean isUpgraded;
        private String description;

        Builder name( final String name )
        {
            this.name = name;
            return this;
        }

        Builder amount( final int amount )
        {
            this.amount = amount;
            return this;
        }

        Builder hp( final int hp )
        {
            this.hp = hp;
            return this;
        }

        Builder attack( final int attack )
        {
            this.attack = attack;
            return this;
        }

        Builder damage( final Range< Integer > damage )
        {
            this.damage = damage;
            return this;
        }

        Builder defence( final int defence )
        {
            this.defence = defence;
            return this;
        }

        Builder tier( final int tier )
        {
            this.tier = tier;
            return this;
        }

        Builder moveRange( final int moveRange )
        {
            this.moveRange = moveRange;
            return this;
        }

        Builder isUpgraded( final boolean isUpgraded )
        {
            this.isUpgraded = isUpgraded;
            return this;
        }

        Builder calculator( final DamageCalculatorIf aCalc )
        {
            calculator = aCalc;
            return this;
        }

        Builder description( final String description )
        {
            this.description = description;
            return this;
        }

        Creature build()
        {
            return new Creature( new CreatureStatistic( name, hp, damage, attack, defence, moveRange, tier,
                isUpgraded, description ), calculator, amount );
        }
    }
}
