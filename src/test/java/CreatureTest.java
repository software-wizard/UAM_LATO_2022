import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Range;

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
            .build();
        final Creature dragon = new Creature.Builder().hp( 100 )
            .attack( NOT_IMPORTANT )
            .defence( 10 )
            .build();
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
            .build();
        final Creature dragon = new Creature.Builder().hp( 100 )
            .attack( NOT_IMPORTANT )
            .defence( 10 )
            .build();
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
            .build();
        final Creature defender = new Creature.Builder().hp( NOT_IMPORTANT )
            .attack( 20 )
            .defence( 5 )
            .build();
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
            .build();
        final Creature defender = new Creature.Builder().hp( NOT_IMPORTANT )
            .attack( 20 )
            .defence( 5 )
            .build();
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
            .build();
        final Creature defender = new Creature.Builder().hp( NOT_IMPORTANT )
            .attack( 20 )
            .defence( 5 )
            .build();
        // when
        attacker.attack( defender );
        attacker.attack( defender );
        // then
        assertThat( attacker.getCurrentHp() ).isEqualTo( 90 );
    }

    @Test
    void attackerShouldNotCounterAttack()
    {
        final Random randomMock = mock( Random.class );
        when( randomMock.nextInt( anyInt() ) ).thenReturn( 3 );

        final Creature attacker = new Creature.Builder().hp( 100 )
            .damage( Range.closed( 5, 5 ) )
            .attack( NOT_IMPORTANT )
            .defence( NOT_IMPORTANT )
            .build();
        final Creature defender = new Creature.Builder().hp( NOT_IMPORTANT )
            .damage( Range.closed( 1, 10 ) )
            .calculator( new DefaultDamageCalculator( randomMock ) )
            .attack( NOT_IMPORTANT )
            .defence( NOT_IMPORTANT )
            .build();
        // when
        attacker.attack( defender );
        // then
        assertThat( defender.getCurrentHp() ).isEqualTo( 95 );
        assertThat( attacker.getCurrentHp() ).isEqualTo( 96 );
    }
}