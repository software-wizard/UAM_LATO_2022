package pl.psi.creatures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import pl.psi.TurnQueue;

import com.google.common.collect.Range;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Disabled
public class CreatureTest {

    private static final int NOT_IMPORTANT = 100;
    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);

    @Test
    void creatureShouldAttackProperly() {
        // given
        final Creature angel = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(Range.closed(10, 10))
                .attack(50)
                .armor(NOT_IMPORTANT)
                .build())
                .build();
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .attack(NOT_IMPORTANT)
                .armor(10)
                .build())
                .build();
        // when
        angel.attack(dragon);
        // then
        assertThat(dragon.getCurrentHp()).isEqualTo(70);
    }

    @Test
    void creatureShouldNotHealCreatureEvenHasLowerAttackThanDefenderArmor() {
        final Creature angel = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(NOT_IMPORTANT_DMG)
                .attack(1)
                .armor(NOT_IMPORTANT)
                .build())
                .build();
        final Creature dragon = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .attack(NOT_IMPORTANT)
                .armor(10)
                .build())
                .build();
        // when
        angel.attack(dragon);
        // then
        assertThat(dragon.getCurrentHp()).isEqualTo(100);
    }

    @Test
    void defenderShouldCounterAttack() {
        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .attack(NOT_IMPORTANT)
                .armor(10)
                .build())
                .build();
        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(Range.closed(10, 10))
                .attack(10)
                .build())
                .build();
        // when
        attacker.attack(defender);
        // then
        assertThat(attacker.getCurrentHp()).isEqualTo(90);
    }

    @Test
    void defenderShouldNotCounterAttackWhenIsDie() {
        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .attack(1000)
                .armor(10)
                .build())
                .build();
        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(NOT_IMPORTANT_DMG)
                .attack(20)
                .armor(5)
                .build())
                .build();
        // when
        attacker.attack(defender);
        // then
        assertThat(attacker.getCurrentHp()).isEqualTo(100);
    }

    @Test
    void defenderShouldCounterAttackOnlyOncePerTurn() {
        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .attack(NOT_IMPORTANT)
                .armor(10)
                .build())
                .build();

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(Range.closed(10, 10))
                .attack(10)
                .build())
                .build();

        // when
        attacker.attack(defender);
        attacker.attack(defender);
        // then
        assertThat(attacker.getCurrentHp()).isEqualTo(90);
    }

    @Test
    void attackerShouldNotCounterAttack() {

        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(Range.closed(5, 5))
                .attack(NOT_IMPORTANT)
                .armor(NOT_IMPORTANT)
                .build())
                .build();

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(Range.closed(5, 5))
                .attack(NOT_IMPORTANT)
                .armor(NOT_IMPORTANT)
                .build())
                .build();

        // when
        attacker.attack(defender);
        // then
        assertThat(defender.getCurrentHp()).isEqualTo(95);
        assertThat(attacker.getCurrentHp()).isEqualTo(95);
    }

    @Test
    void counterAttackCounterShouldResetAfterEndOfTurn() {
        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .build())
                .build();

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(Range.closed(10, 10))
                .build())
                .build();

        final TurnQueue turnQueue = new TurnQueue(List.of(attacker), List.of(defender));

        attacker.attack(defender);
        attacker.attack(defender);
        assertThat(attacker.getCurrentHp()).isEqualTo(90);
        turnQueue.next();
        turnQueue.next();
        attacker.attack(defender);
        assertThat(attacker.getCurrentHp()).isEqualTo(80);
        // end of turn
    }

    @Test
    void creatureShouldHealAfterEndOfTurn() {
        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(Range.closed(10, 10))
                .build())
                .build();

        final Creature selfHealAfterEndOfTurnCreature = new SelfHealAfterTurnCreatureDecorator(new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .build())
                .build());

        final TurnQueue turnQueue =
                new TurnQueue(List.of(attacker), List.of(selfHealAfterEndOfTurnCreature));

        attacker.attack(selfHealAfterEndOfTurnCreature);
        assertThat(selfHealAfterEndOfTurnCreature.getCurrentHp()).isEqualTo(90);
        turnQueue.next();
        turnQueue.next();
        assertThat(selfHealAfterEndOfTurnCreature.getCurrentHp()).isEqualTo(100);
    }

    @Test
    void creatureShouldNotBeCounterAttacked() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .build())
                .build();

        final NoCounterCreatureDecorator noCounterCreature = new NoCounterCreatureDecorator(decorated);

        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .build())
                .build();

        final Creature defender = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(NOT_IMPORTANT)
                        .damage(Range.closed(10, 10))
                        .build())
                .build();

        noCounterCreature.attack(defender);
        attacker.attack(defender);

        assertThat(noCounterCreature.getClass()).isEqualTo(NoCounterCreatureDecorator.class);
        assertThat(noCounterCreature.getCurrentHp()).isEqualTo(100);
        assertThat(attacker.getCurrentHp()).isEqualTo(90);
    }

    @Test
    void creatureShouldShootWhenEnoughShots() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(Range.closed(10, 10))
                .build())
                .build();

        final ShooterCreatureDecorator shooterCreature = new ShooterCreatureDecorator(decorated, 1);

        final Creature defender = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .build())
                .build();

        shooterCreature.attack(defender);

        assertThat(defender.getCurrentHp()).isEqualTo(90);
    }

    @Test
    void creatureShouldDoHalfDamageWhenInMelee() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(Range.closed(10, 10))
                .build())
                .build();

        final ShooterCreatureDecorator shooterCreature = new ShooterCreatureDecorator(decorated, 0);

        final Creature defender = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .build())
                .build();

        shooterCreature.setInMelee(true);
        shooterCreature.attack(defender);
        assertThat(defender.getCurrentHp()).isEqualTo(95);
    }

    @Test
    void creatureShouldResurrectUnitsIfHpHighEnough() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(40)
                .damage(Range.closed(5, 5))
                .build())
                .amount(10)
                .build();

        final NoCounterCreatureDecorator noCounterCreature = new NoCounterCreatureDecorator(decorated);
        final HealFromAttackCreatureDecorator healFromAttackCreature = new HealFromAttackCreatureDecorator(noCounterCreature);

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .type(CreatureStatistic.CreatureType.ALIVE)
                .build())
                .build();

        healFromAttackCreature.attack(defender);
        assertThat(healFromAttackCreature.getAmount()).isEqualTo(12);
        assertThat(healFromAttackCreature.getCurrentHp()).isEqualTo(10);
    }

    @Test
    void decoratedCreatureShouldCounterAttackProperly() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(40)
                .damage(Range.closed(5, 5))
                .build())
                .amount(1)
                .build();

        final NoCounterCreatureDecorator noCounterCreature = new NoCounterCreatureDecorator(decorated);
        final HealFromAttackCreatureDecorator healFromAttackCreature = new HealFromAttackCreatureDecorator(noCounterCreature);
        healFromAttackCreature.setCurrentHp(1); // this is here so creature cant resurrect units and mess up the math

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .type(CreatureStatistic.CreatureType.ALIVE)
                .build())
                .build();

        final TurnQueue turnQueue = new TurnQueue(List.of(healFromAttackCreature), List.of(defender));

        defender.attack( healFromAttackCreature );
        defender.attack( healFromAttackCreature );
        turnQueue.next();
        turnQueue.next();
        turnQueue.next();
        turnQueue.next();
        defender.attack( healFromAttackCreature );
        assertThat(defender.getCurrentHp()).isEqualTo(90);
    }


    @Test
    void vampireCreatureTest() {
        final Creature vampireToDecorate = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(CreatureStatistic.VAMPIRE_LORD.getMaxHp())
                .damage(CreatureStatistic.VAMPIRE_LORD.getDamage())
                .build())
                .amount(1)
                .build();

        final NoCounterCreatureDecorator noCounterVampire = new NoCounterCreatureDecorator(vampireToDecorate);
        final HealFromAttackCreatureDecorator vampireLord = new HealFromAttackCreatureDecorator(noCounterVampire);

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(Range.closed(100, 100))
                .type(CreatureStatistic.CreatureType.ALIVE)
                .build())
                .build();

        vampireLord.attack(defender);
        assertThat(defender.getCurrentHp()).isLessThanOrEqualTo(95);
        assertThat(vampireLord.getAmount()).isEqualTo(1);
        assertThat(vampireLord.getCurrentHp()).isGreaterThanOrEqualTo(5);
    }

    @Test
    void creatureShouldBeAbleToChangeCreatureStats() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(NOT_IMPORTANT_DMG)
                .build())
                .build();

        final DiseaseOnHitCreatureDecorator diseaseOnHitCreature = new DiseaseOnHitCreatureDecorator(decorated);

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .attack(5)
                .armor(5)
                .type(CreatureStatistic.CreatureType.ALIVE)
                .build())
                .build();

        diseaseOnHitCreature.attackWithDisease(defender);
        assertThat(defender.getAttack()).isEqualTo(3);
        assertThat(defender.getArmor()).isEqualTo(3);
    }

    @Test
    void damageCalculatorsTest() {
        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(Range.closed(5, 10))
                .build())
                .build();

        final Creature minimalDamageDefender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .build())
                .build();

        final Creature maximalDamageDefender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .build())
                .build();

        final Creature reducedDamageDefender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .build())
                .build();


        creature.setCalculator(new MinimalDamageCalculator());
        creature.attack(minimalDamageDefender);
        assertThat(minimalDamageDefender.getCurrentHp()).isEqualTo(95);

        creature.setCalculator(new MaximalDamageCalculator());
        creature.attack(maximalDamageDefender);
        assertThat(maximalDamageDefender.getCurrentHp()).isEqualTo(90);

        creature.setCalculator(new ReducedDamageCalculator(0.5));
        creature.attack(reducedDamageDefender);
        assertThat(reducedDamageDefender.getCurrentHp()).isBetween(95.0, 98.0);
    }

    @Test
    void creatureShouldAttackWithDoubleDamage() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(Range.closed(10, 10))
                .build())
                .build();

        final DoubleDamageOnHitCreatureDecorator doubleDamageOnHitCreature = new DoubleDamageOnHitCreatureDecorator(decorated);

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .build())
                .build();

        doubleDamageOnHitCreature.attackWithDoubleDamage(defender);
        assertThat(defender.getCurrentHp()).isEqualTo(80);
    }

    @Test
    void buffingWorksProperly() {
        final Creature creatureToDecorate = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(Range.closed(5, 5))
                .attack(5)
                .build())
                .build();
        final NoCounterCreatureDecorator creature = new NoCounterCreatureDecorator(creatureToDecorate);

        final CreatureStats stats = new CreatureStats.CreatureStatsBuilder()
                .attack(5)
                .maxHp(-10)
                .damage(Range.closed(10, 10))
                .build();

        creature.buff(stats);

        assertThat(creature.getStats().getAttack()).isEqualTo(10);
        assertThat(creature.getStats().getMaxHp()).isEqualTo(90);
        assertThat(creature.getStats().getDamage()).isEqualTo(Range.closed(10, 10));
    }

    @Test
    void increasingStatisticsWorksProperly() {
        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(Range.closed(5, 5))
                .attack(5)
                .build())
                .build();

        final CreatureStats buff = new CreatureStats.CreatureStatsBuilder()
                .attack(5)
                .maxHp(10)
                .damage(Range.closed(10, 10))
                .build();

        creature.increaseStats(buff);

        assertThat(creature.getStats().getAttack()).isEqualTo(10);
        assertThat(creature.getStats().getMaxHp()).isEqualTo(110);
        assertThat(creature.getStats().getDamage()).isEqualTo(Range.closed(10, 10));
    }


    @Test
    void creatureShouldAgeWhenHit() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(Range.closed(10, 10))
                .build())
                .build();

        final AgeOnHitCreatureDecorator ageOnHitCreature = new AgeOnHitCreatureDecorator(decorated);

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .type(CreatureStatistic.CreatureType.ALIVE)
                .build())
                .build();

        ageOnHitCreature.attackWithAge(defender);
        assertThat(defender.getMaxHp()).isEqualTo(50);
        assertThat(defender.getCurrentHp()).isEqualTo(40);
    }

    @Test
    void creatureShouldApplyCurseOnHit() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(1)
                .damage(Range.closed(10, 10))
                .build())
                .build();

        final CurseOnHitCreatureDecorator curseOnHitCreature = new CurseOnHitCreatureDecorator(decorated);

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(Range.closed(0, 100))
                .type(CreatureStatistic.CreatureType.ALIVE)
                .build())
                .build();

        curseOnHitCreature.attackWithCurse(defender);
        assertThat(curseOnHitCreature.getCurrentHp()).isEqualTo(1);
    }

    @Test
    void creatureShouldAttackTwice() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(6)
                .damage(Range.closed(10, 10))
                .build())
                .amount(2)
                .build();

        final DoubleAttackCreatureDecorator doubleAttackCreature = new DoubleAttackCreatureDecorator(decorated);

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(Range.closed(10, 10))
                .build())
                .build();

        doubleAttackCreature.attack(defender);

        assertThat(defender.getCurrentHp()).isEqualTo(70);
        assertThat(doubleAttackCreature.getCurrentHp()).isEqualTo(2);
    }

    @Test
    void shooterCreatureShouldAttackTwice() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(6)
                        .damage(Range.closed(5, 5))
                        .build())
                .amount(2)
                .build();

        final ShooterCreatureDecorator shooterCreature = new ShooterCreatureDecorator(decorated,12);
        final DoubleAttackCreatureDecorator doubleAttackCreature = new DoubleAttackCreatureDecorator(shooterCreature);


        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .build())
                .build();

        doubleAttackCreature.attack(defender);

        assertThat(defender.getCurrentHp()).isEqualTo(80);
        assertThat(doubleAttackCreature.getCurrentHp()).isEqualTo(6);
    }

    @Test
    void creatureShouldHaveTwoCounters() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(5, 5))
                        .build())
                .build();

        final MoreCounterAttacksCreatureDecorator moreCounterAttacksCreature = new MoreCounterAttacksCreatureDecorator(decorated,2);

        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .build())
                .build();

        attacker.attack(moreCounterAttacksCreature);
        attacker.attack(moreCounterAttacksCreature);

        assertThat(moreCounterAttacksCreature.getCurrentHp()).isEqualTo(80);
        assertThat(attacker.getCurrentHp()).isEqualTo(90);

        attacker.attack(moreCounterAttacksCreature);

        assertThat(attacker.getCurrentHp()).isEqualTo(90);
    }

    @Test
    void creatureShouldHaveInfiniteCounters() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(5, 5))
                        .build())
                .build();

        final MoreCounterAttacksCreatureDecorator moreCounterAttacksCreature = new MoreCounterAttacksCreatureDecorator(decorated,Integer.MAX_VALUE);

        final Creature attacker1 = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .build())
                .build();

        final Creature attacker2 = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .build())
                .build();

        attacker1.attack(moreCounterAttacksCreature);
        attacker1.attack(moreCounterAttacksCreature);
        attacker1.attack(moreCounterAttacksCreature);
        attacker1.attack(moreCounterAttacksCreature);

        assertThat(attacker1.getCurrentHp()).isEqualTo(80);

        attacker2.attack(moreCounterAttacksCreature);
        attacker2.attack(moreCounterAttacksCreature);

        assertThat(attacker2.getCurrentHp()).isEqualTo(90);
    }

    @Test
    void creatureShouldNotHaveMeleePenalty() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .build())
                .build();

        final NoMeleePenaltyShooterCreatureDecorator zealot = new NoMeleePenaltyShooterCreatureDecorator(decorated, 1);

        final Creature defender = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10,10))
                        .build())
                .build();

        zealot.setInMelee(true);
        zealot.attack(defender);
        assertThat(defender.getCurrentHp()).isEqualTo(90);
        assertThat(zealot.getCurrentHp()).isEqualTo(90);
    }

    @Test
    void creatureShouldAttackWithThunderbolt() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                .damage(Range.closed(5, 5))
                .build())
                .amount(2)
                .build();

        final ThunderboltOnHitCreatureDecorator thunderboltOnHitCreature = new ThunderboltOnHitCreatureDecorator(decorated);

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .build())
                .build();

        thunderboltOnHitCreature.attackWithThunderbolt(defender);
        assertThat(defender.getCurrentHp()).isEqualTo(70);
    }

    @Test
    void creatureShouldAttackWithReducedDefenderDefence() {
        final Creature decorated = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(CreatureStatistic.BEHEMOTH.getMaxHp())
                .attack(CreatureStatistic.BEHEMOTH.getAttack())
                .damage(Range.closed(30, 50))
                .build())
                .build();

        final DefenceReductionCreatureDecorator reduceDefenceCreature = new DefenceReductionCreatureDecorator(decorated, 0.6);


        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(CreatureStatistic.GHOST_DRAGON.getMaxHp())
                .damage(CreatureStatistic.GHOST_DRAGON.getDamage())
                .armor(CreatureStatistic.GHOST_DRAGON.getArmor())
                .build())
                .build();

        reduceDefenceCreature.attack(defender);
        assertThat(defender.getCurrentHp()).isBetween(133.0, 160.0);  // not sure if the calculation is correct, was referring to this http://imzdx.pl/h3dmg/
    }

    @Test
    void creatureShouldTakeReducedSpellDamage() {
        final Creature normalSpellDamage = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .build())
                .build();

        final Creature reducedSpellDamage = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .build())
                .build();

        reducedSpellDamage.setSpellDamageReduction(0.5);
        normalSpellDamage.applySpellDamage(10);
        reducedSpellDamage.applySpellDamage(10);
        assertThat(normalSpellDamage.getCurrentHp()).isEqualTo(90);
        assertThat(reducedSpellDamage.getCurrentHp()).isEqualTo(95);
    }

    @Test
    void shouldThrowExceptionWhenTryingToSetMoraleValueLessThanPossible() {
        final var creature = new Creature.Builder().statistic(CreatureStats.builder().build()).build(); // current morale value = 1
        final var exception = assertThrows(IllegalArgumentException.class, () -> creature.setMorale(-4));

        assertEquals("Morale must not be less than 3", exception.getMessage());
    }

    @Test
    void defendShouldIncreaseDefense() {
        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .armor(11)
                .build())
                .build();

        creature.defend(true);
        assertThat( creature.getArmor() ).isEqualTo(13.2);
        creature.defend( false );
        assertThat( creature.getArmor() ).isEqualTo(11);
    }

    @Test
    void creatureShouldHaveSplashDamageRange(){
        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .build())
                .build();

        assertThat(creature.getSplashDamageRange()[1][1]).isEqualTo(1);
        assertThat(creature.getSplashDamageRange()[0][1]).isEqualTo(0);
    }

    @Test
    void creatureShouldHaveInformationAboutLastAttack(){
        final Creature attackerToDecorate = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(Range.closed(11,20))
                .build())
                .build();
        final NoCounterCreatureDecorator attackerToDecorate2 = new NoCounterCreatureDecorator(attackerToDecorate);
        final HealFromAttackCreatureDecorator attacker = new HealFromAttackCreatureDecorator(attackerToDecorate2);
        attacker.setCurrentHp(50); // // this is here so creature cant resurrect units and mess up the math

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(Range.closed(1,10))
                .type(CreatureStatistic.CreatureType.ALIVE)
                .build())
                .build();
        
        attacker.attack(defender);
        assertThat(attacker.getLastAttackDamage()).isBetween(11.0,20.0);
        assertThat(attacker.getLastHealAmount()).isEqualTo(attacker.getLastAttackDamage());
        assertThat(defender.getLastCounterAttackDamage()).isEqualTo(0);

        defender.attack(attacker);
        assertThat(defender.getLastAttackDamage()).isBetween(1.0,10.0);
        assertThat(attacker.getLastCounterAttackDamage()).isBetween(11.0,20.0);
    }

}
