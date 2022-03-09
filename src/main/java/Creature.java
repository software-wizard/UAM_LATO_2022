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
    private final int hp;
    private final int attack;
    private final int defence;
    private int currentHp;

    private Creature( final int aHp, final int aAttack, final int aDefence )
    {
        hp = aHp;
        currentHp = aHp;
        attack = aAttack;
        defence = aDefence;
    }

    void attack( final Creature aDefender )
    {
        aDefender.currentHp = aDefender.currentHp - attack + aDefender.defence;
    }

    int getCurrentHp()
    {
        return currentHp;
    }

    static class Bulider
    {
        private int hp;
        private int attack;
        private int defence;

        Bulider hp( final int hp )
        {
            this.hp = hp;
            return this;
        }

        Bulider attack( final int attack )
        {
            this.attack = attack;
            return this;
        }

        Bulider defence( final int defence )
        {
            this.defence = defence;
            return this;
        }

        Creature bulid()
        {
            return new Creature( hp, attack, defence );
        }

    }
}
