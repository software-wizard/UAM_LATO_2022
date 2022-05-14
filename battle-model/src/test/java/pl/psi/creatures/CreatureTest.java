package pl.psi.creatures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.Test;

import pl.psi.TurnQueue;

import com.google.common.collect.Range;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class CreatureTest
{

    private static final int NOT_IMPORTANT = 100;
    private static final Range< Integer > NOT_IMPORTANT_DMG = Range.closed( 0, 0 );

    @Test
    void creatureShouldAttackProperly()
    {
        // given
        final Creature angel = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( NOT_IMPORTANT )
            .damage( Range.closed( 10, 10 ) )
            .attack( 50 )
            .armor( NOT_IMPORTANT )
            .build() )
            .build();
        final Creature dragon = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( NOT_IMPORTANT_DMG )
            .attack( NOT_IMPORTANT )
            .armor( 10 )
            .build() )
            .build();
        // when
        angel.attack( dragon );
        // then
        assertThat( dragon.getCurrentHp() ).isEqualTo( 70 );
    }

    @Test
    void creatureShouldNotHealCreatureEvenHasLowerAttackThanDefenderArmor()
    {
        final Creature angel = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( NOT_IMPORTANT )
            .damage( NOT_IMPORTANT_DMG )
            .attack( 1 )
            .armor( NOT_IMPORTANT )
            .build() )
            .build();
        final Creature dragon = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( NOT_IMPORTANT_DMG )
            .attack( NOT_IMPORTANT )
            .armor( 10 )
            .build() )
            .build();
        // when
        angel.attack( dragon );
        // then
        assertThat( dragon.getCurrentHp() ).isEqualTo( 100 );
    }

    @Test
    void defenderShouldCounterAttack()
    {
        final Creature attacker = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( NOT_IMPORTANT_DMG )
            .attack( NOT_IMPORTANT )
            .armor( 10 )
            .build() )
            .build();
        final Creature defender = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( NOT_IMPORTANT )
            .damage( Range.closed( 10, 10 ) )
            .attack( 10 )
            .build() )
            .build();
        // when
        attacker.attack( defender );
        // then
        assertThat( attacker.getCurrentHp() ).isEqualTo( 90 );
    }

    @Test
    void defenderShouldNotCounterAttackWhenIsDie()
    {
        final Creature attacker = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( NOT_IMPORTANT_DMG )
            .attack( 1000 )
            .armor( 10 )
            .build() )
            .build();
        final Creature defender = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( NOT_IMPORTANT )
            .damage( NOT_IMPORTANT_DMG )
            .attack( 20 )
            .armor( 5 )
            .build() )
            .build();
        // when
        attacker.attack( defender );
        // then
        assertThat( attacker.getCurrentHp() ).isEqualTo( 100 );
    }

    @Test
    void defenderShouldCounterAttackOnlyOncePerTurn()
    {
        final Creature attacker = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( NOT_IMPORTANT_DMG )
            .attack( NOT_IMPORTANT )
            .armor( 10 )
            .build() )
            .build();

        final Creature defender = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( NOT_IMPORTANT )
            .damage( Range.closed( 10, 10 ) )
            .attack( 10 )
            .build() )
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

        final Creature attacker = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( Range.closed( 5, 5 ) )
            .attack( NOT_IMPORTANT )
            .armor( NOT_IMPORTANT )
            .build() )
            .build();

        final Creature defender = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( NOT_IMPORTANT )
            .damage( Range.closed( 5, 5 ) )
            .attack( NOT_IMPORTANT )
            .armor( NOT_IMPORTANT )
            .build() )
            .build();

        // when
        attacker.attack( defender );
        // then
        assertThat( defender.getCurrentHp() ).isEqualTo( 95 );
        assertThat( attacker.getCurrentHp() ).isEqualTo( 95 );
    }

    @Test
    void counterAttackCounterShouldResetAfterEndOfTurn()
    {
        final Creature attacker = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( NOT_IMPORTANT_DMG )
            .build() )
            .build();

        final Creature defender = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( Range.closed( 10, 10 ) )
            .build() )
            .build();

        final TurnQueue turnQueue = new TurnQueue( List.of( attacker ), List.of( defender ) );

        attacker.attack( defender );
        attacker.attack( defender );
        assertThat( attacker.getCurrentHp() ).isEqualTo( 90 );
        turnQueue.next();
        turnQueue.next();
        attacker.attack( defender );
        assertThat( attacker.getCurrentHp() ).isEqualTo( 80 );
        // end of turn
    }

    @Test
    void creatureShouldHealAfterEndOfTurn()
    {
        final Creature attacker = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( Range.closed( 10, 10 ) )
            .build() )
            .build();

        final Creature selfHealAfterEndOfTurnCreature = new SelfHealAfterTurnCreature( new Creature.Builder()
            .statistic( CreatureStats.builder()
                .maxHp( 100 )
                .damage( NOT_IMPORTANT_DMG )
                .build() )
            .build() );

        final TurnQueue turnQueue =
            new TurnQueue( List.of( attacker ), List.of( selfHealAfterEndOfTurnCreature ) );

        attacker.attack( selfHealAfterEndOfTurnCreature );
        assertThat( selfHealAfterEndOfTurnCreature.getCurrentHp() ).isEqualTo( 90 );
        turnQueue.next();
        turnQueue.next();
        assertThat( selfHealAfterEndOfTurnCreature.getCurrentHp() ).isEqualTo( 100 );
    }

    @Test
    void creatureShouldNotBeCounterAttacked()
    {
        final Creature decorated = new Creature.Builder().statistic( CreatureStats.builder()
                .maxHp( 100 )
                .damage( NOT_IMPORTANT_DMG )
                .build() )
                .build();

        final NoCounterCreature noCounterCreature = new NoCounterCreature( decorated );


        final Creature attacker = new Creature.Builder().statistic( CreatureStats.builder()
                .maxHp( 100 )
                .damage( NOT_IMPORTANT_DMG )
                .build() )
                .build();

        final Creature defender = new Creature.Builder()
                .statistic( CreatureStats.builder()
                        .maxHp( NOT_IMPORTANT )
                        .damage( Range.closed(10,10) )
                        .build() )
                .build();

        noCounterCreature.attack( defender );
        attacker.attack( defender );

        assertThat( noCounterCreature.getClass() ).isEqualTo( NoCounterCreature.class );
        assertThat( noCounterCreature.getCurrentHp() ).isEqualTo( 100 );
        assertThat( attacker.getCurrentHp() ).isEqualTo( 90 );
    }

    @Test
    void creatureShouldShootWhenEnoughShots()
    {
        final Creature decorated = new Creature.Builder().statistic( CreatureStats.builder()
                .maxHp( 100 )
                .damage( Range.closed(10,10) )
                .build() )
                .build();

        final ShooterCreature shooterCreature = new ShooterCreature( decorated, 1 );

        final Creature defender = new Creature.Builder()
                .statistic( CreatureStats.builder()
                        .maxHp( 100 )
                        .damage( NOT_IMPORTANT_DMG )
                        .build() )
                .build();

        shooterCreature.attackRange( defender );

        assertThat( defender.getCurrentHp() ).isEqualTo( 90 );
    }

    @Test
    void creatureShouldDoHalfDamageWhenInMelee()
    {
        final Creature decorated = new Creature.Builder().statistic( CreatureStats.builder()
                .maxHp( NOT_IMPORTANT )
                .damage( Range.closed(10,10) )
                .build() )
                .build();

        final ShooterCreature shooterCreature = new ShooterCreature( decorated, 0 );

        final Creature defender = new Creature.Builder()
                .statistic( CreatureStats.builder()
                        .maxHp( 100 )
                        .damage( NOT_IMPORTANT_DMG )
                        .build() )
                .build();

        shooterCreature.attackMelee( defender );

        assertThat( defender.getCurrentHp() ).isEqualTo( 95 );
    }

    @Test
    void creatureShouldResurrectUnitsIfHpHighEnough()
    {
        final Creature decorated = new Creature.Builder().statistic( CreatureStats.builder()
                .maxHp( 40 )
                .damage( Range.closed( 5, 5 ) )
                .build() )
                .amount(10)
                .build();

        final HealFromAttackCreature healFromAttackCreature = new HealFromAttackCreature( decorated );

        final Creature defender = new Creature.Builder().statistic( CreatureStats.builder()
                .maxHp( NOT_IMPORTANT )
                .damage( NOT_IMPORTANT_DMG )
                .type( CreatureStatistic.CreatureType.ALIVE )
                .build() )
                .build();

        healFromAttackCreature.attack( defender );
        assertThat( healFromAttackCreature.getAmount() ).isEqualTo( 12 );
        assertThat( healFromAttackCreature.getCurrentHp() ).isEqualTo( 10 );
    }

    @Test
    void vampireCreatureTest()
    {
        final Creature vampireToDecorate = new Creature.Builder().statistic( CreatureStats.builder()
                .maxHp( CreatureStatistic.VAMPIRE_LORD.getMaxHp() )
                .damage( CreatureStatistic.VAMPIRE_LORD.getDamage() )
                .build() )
                .amount( 1 )
                .build();

        final NoCounterCreature noCounterVampire = new NoCounterCreature( vampireToDecorate );
        final HealFromAttackCreature vampireLord = new HealFromAttackCreature( noCounterVampire );

        final Creature defender = new Creature.Builder().statistic( CreatureStats.builder()
                .maxHp(100)
                .damage( Range.closed(100,100) )
                .type( CreatureStatistic.CreatureType.ALIVE )
                .build() )
                .build();

        vampireLord.attack( defender );
        assertThat( defender.getCurrentHp() ).isLessThanOrEqualTo( 95 );
        assertThat( vampireLord.getAmount() ).isEqualTo( 2 );
        assertThat( vampireLord.getCurrentHp() ).isGreaterThanOrEqualTo( 5 );
    }

    @Test
    void creatureShouldBeAbleToChangeCreatureStats()
    {
        final Creature decorated = new Creature.Builder().statistic( CreatureStats.builder()
                .maxHp( NOT_IMPORTANT )
                .damage( NOT_IMPORTANT_DMG )
                .build() )
                .build();

        final DiseaseOnHitCreature diseaseOnHitCreature = new DiseaseOnHitCreature( decorated );

        final Creature defender = new Creature.Builder().statistic( CreatureStats.builder()
                .maxHp(100)
                .damage( NOT_IMPORTANT_DMG )
                .attack( 5 )
                .type( CreatureStatistic.CreatureType.ALIVE )
                .build() )
                .build();

        final TurnQueue turnQueue =
                new TurnQueue( List.of( diseaseOnHitCreature ), List.of( defender ) );

        diseaseOnHitCreature.attack( defender );
        assertThat( defender.getAttack() ).isEqualTo( 3 );
    }

    @Test
    void creatureShouldAttackWithDoubleDamage()
    {
        final Creature decorated = new Creature.Builder().statistic( CreatureStats.builder()
                .maxHp( NOT_IMPORTANT )
                .damage( Range.closed(10,10) )
                .build() )
                .build();

        final DoubleDamageOnHitCreature doubleDamageOnHitCreature = new DoubleDamageOnHitCreature( decorated );

        final Creature defender = new Creature.Builder().statistic( CreatureStats.builder()
                .maxHp(100)
                .damage( NOT_IMPORTANT_DMG )
                .build() )
                .build();

        doubleDamageOnHitCreature.attackWithDoubleDamage( defender );
        assertThat( defender.getCurrentHp() ).isEqualTo( 80 );
    }

    @Test
    void creatureShouldAgeWhenHit()
    {
        final Creature decorated = new Creature.Builder().statistic( CreatureStats.builder()
                .maxHp( NOT_IMPORTANT )
                .damage( Range.closed(10,10) )
                .build() )
                .build();

        final AgeOnHitCreature ageOnHitCreature = new AgeOnHitCreature( decorated );

        final Creature defender = new Creature.Builder().statistic( CreatureStats.builder()
                .maxHp(100)
                .damage( NOT_IMPORTANT_DMG )
                .type( CreatureStatistic.CreatureType.ALIVE )
                .build() )
                .build();

        ageOnHitCreature.attackWithAge( defender );
        assertThat( defender.getMaxHp() ).isEqualTo( 50 );
        assertThat( defender.getCurrentHp() ).isEqualTo( 40 );
    }

}
