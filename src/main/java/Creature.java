//  ******************************************************************
//
//  Copyright 2022 PSI Software AG. All rights reserved.
//  PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
//  ******************************************************************

import lombok.Getter;

import com.google.common.collect.Range;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
public class Creature
{
    private final CreatureStatistic stats;
    private final int amount;
    private int currentHp;
    private int counterAttackCounter = 1;
    private final DamageCalculatorIf calculator;

    private Creature( final CreatureStatistic aStats, final DamageCalculatorIf aCalculator,
        final int aAmount )
    {
        stats = aStats;
        amount = aAmount;
        currentHp = stats.getMaxHp();
        calculator = aCalculator;
    }

    void attack( final Creature aDefender )
    {
        if( isAlive() )
        {
            final int damage = calculator.calculateDamage( this, aDefender );
            applyDamage( aDefender, damage );
            if( canCounterAttack( aDefender ) )
            {
                counterAttack( aDefender );
            }
        }
    }

    public boolean isAlive()
    {
        return amount > 0;
    }

    private void applyDamage( final Creature aDefender, final int aDamage )
    {
        aDefender.currentHp = aDefender.currentHp - aDamage;
    }

    private boolean canCounterAttack( final Creature aDefender )
    {
        return aDefender.counterAttackCounter > 0 && aDefender.getCurrentHp() > 0;
    }

    private void counterAttack( final Creature aAttacker )
    {
        final int damage = calculator.calculateDamage( aAttacker, this );
        applyDamage( this, damage );
        aAttacker.counterAttackCounter--;
    }

    int getCurrentHp()
    {
        return currentHp;
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

    static class Builder
    {
        private String name;
        private int amount = 1;
        private int hp;
        private int attack;
        private Range< Integer > damage = Range.closed( 0, 0 );
        private int defence;
        private DamageCalculatorIf calculator = new DefaultDamageCalculator();
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
