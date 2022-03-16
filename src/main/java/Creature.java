//  ******************************************************************
//
//  Copyright 2022 PSI Software AG. All rights reserved.
//  PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
//  ******************************************************************

import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
public class Creature
{
    private final CreatureStatistic stats;
    private int currentHp;
    private final int counterAttackCounter = 1;
    private final DamageCalculatorIf calculator;

    private Creature( final CreatureStatistic aStats, final DamageCalculatorIf aCalculator )
    {
        stats = aStats;
        currentHp = stats.getMaxHp();
        calculator = aCalculator;
    }

    void attack( final Creature aDefender )
    {
        if( stats.getAttack() > aDefender.getStats()
            .getDefence() && currentHp > 0 )
        {
            final int damage = calculator.calculateDamage( stats.getAttack(), aDefender.getStats()
                .getDefence() );
            applyDamage( aDefender, damage );
            if( canCounterAttack( aDefender ) )
            {
                counterAttack( aDefender );
            }
        }
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
        final int damage = calculator.calculateDamage( aAttacker.getStats()
            .getAttack(),
            this.getStats()
                .getDefence() );
        applyDamage( this, damage );
    }

    int getCurrentHp()
    {
        return currentHp;
    }

    static class Builder
    {
        private int hp;
        private int attack;
        private int defence;
        private DamageCalculatorIf calculator;

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

        Builder defence( final int defence )
        {
            this.defence = defence;
            return this;
        }

        Builder calculator( final DamageCalculatorIf aCalc )
        {
            calculator = aCalc;
            return this;
        }

        Creature bulid()
        {
            return new Creature( new CreatureStatistic( hp, attack, defence ), calculator );
        }

    }
}
