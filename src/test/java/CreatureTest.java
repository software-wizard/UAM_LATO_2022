import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class CreatureTest
{

    private static final int NOT_IMPORTANT = 100;

    @Test
    void creatureShouldAttackProperly()
    {
        // given
        final Creature angel = new Creature.Builder().hp( NOT_IMPORTANT )
            .attack( 50 )
            .defence( NOT_IMPORTANT )
            .bulid();
        final Creature dragon = new Creature.Builder().hp( 100 )
            .attack( NOT_IMPORTANT )
            .defence( 10 )
            .bulid();
        // when
        angel.attack( dragon );
        // then
        assertThat( dragon.getCurrentHp() ).isEqualTo( 60 );
    }

    @Test
    void creatureShouldNotHealCreatureEvenHasLowerAttackThanDefenderArmor()
    {
        final Creature angel = new Creature.Builder().hp( NOT_IMPORTANT )
            .attack( 1 )
            .defence( NOT_IMPORTANT )
            .bulid();
        final Creature dragon = new Creature.Builder().hp( 100 )
            .attack( NOT_IMPORTANT )
            .defence( 10 )
            .bulid();
        // when
        angel.attack( dragon );
        // then
        assertThat( dragon.getCurrentHp() ).isEqualTo( 100 );
    }

    @Test
    void defenderShouldCounterAttack()
    {
        final Creature attacker = new Creature.Builder().hp( 100 )
            .attack( NOT_IMPORTANT )
            .defence( 10 )
            .bulid();
        final Creature defender = new Creature.Builder().hp( NOT_IMPORTANT )
            .attack( 20 )
            .defence( 5 )
            .bulid();
        // when
        attacker.attack( defender );
        // then
        assertThat( attacker.getCurrentHp() ).isEqualTo( 90 );
    }

    @Test
    void defenderShouldNotCounterAttackWhenIsDie()
    {
        final Creature attacker = new Creature.Builder().hp( 100 )
            .attack( 1000 )
            .defence( 10 )
            .bulid();
        final Creature defender = new Creature.Builder().hp( NOT_IMPORTANT )
            .attack( 20 )
            .defence( 5 )
            .bulid();
        // when
        attacker.attack( defender );
        // then
        assertThat( attacker.getCurrentHp() ).isEqualTo( 100 );
    }

    @Test
    void defenderShouldCounterAttackOnlyOncePerTurn()
    {
        final Creature attacker = new Creature.Builder().hp( 100 )
            .attack( NOT_IMPORTANT )
            .defence( 10 )
            .bulid();
        final Creature defender = new Creature.Builder().hp( NOT_IMPORTANT )
            .attack( 20 )
            .defence( 5 )
            .bulid();
        // when
        attacker.attack( defender );
        attacker.attack( defender );
        // then
        assertThat( attacker.getCurrentHp() ).isEqualTo( 90 );
    }

    @Test
    void attackerShouldNotCounterAttack()
    {
        final Creature attacker = new Creature.Builder().hp( 100 )
            .attack( 10 )
            .defence( 10 )
            .bulid();
        final Creature defender = new Creature.Builder().hp( NOT_IMPORTANT )
            .attack( 20 )
            .defence( 5 )
            .bulid();
        // when
        attacker.attack( defender );
        // then
        assertThat( attacker.getCurrentHp() ).isEqualTo( 90 );
        assertThat( defender.getCurrentHp() ).isEqualTo( 95 );
    }
}