package pl.psi.creatures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.Range;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;
import pl.psi.TurnQueue;
import pl.psi.artifacts.Artifact;
import pl.psi.artifacts.ArtifactApplierTarget;
import pl.psi.artifacts.ArtifactApplyingMode;
import pl.psi.artifacts.ArtifactEffect;
import pl.psi.artifacts.ArtifactInvalidException;
import pl.psi.artifacts.ArtifactType;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class CreatureTest {

    private static final int NOT_IMPORTANT = 100;

    @Test
    void creatureShouldAttackProperly() {
        // given
        final Creature angel = new Creature.Builder().hp(NOT_IMPORTANT)
            .attack(50)
            .defence(NOT_IMPORTANT)
            .build();
        final Creature dragon = new Creature.Builder().hp(100)
            .attack(NOT_IMPORTANT)
            .defence(10)
            .build();
        // when
        angel.attack(dragon);
        // then
        assertThat(dragon.getCurrentHp()).isEqualTo(60);
    }

    @Test
    void creatureShouldNotHealCreatureEvenHasLowerAttackThanDefenderArmor() {
        final Creature angel = new Creature.Builder().hp(NOT_IMPORTANT)
            .attack(1)
            .defence(NOT_IMPORTANT)
            .build();
        final Creature dragon = new Creature.Builder().hp(100)
            .attack(NOT_IMPORTANT)
            .defence(10)
            .build();
        // when
        angel.attack(dragon);
        // then
        assertThat(dragon.getCurrentHp()).isEqualTo(100);
    }

    @Test
    void defenderShouldCounterAttack() {
        final Creature attacker = new Creature.Builder().hp(100)
            .attack(NOT_IMPORTANT)
            .defence(10)
            .build();
        final Creature defender = new Creature.Builder().hp(NOT_IMPORTANT)
            .attack(20)
            .defence(5)
            .build();
        // when
        attacker.attack(defender);
        // then
        assertThat(attacker.getCurrentHp()).isEqualTo(90);
    }

    @Test
    void defenderShouldNotCounterAttackWhenIsDie() {
        final Creature attacker = new Creature.Builder().hp(100)
            .attack(1000)
            .defence(10)
            .build();
        final Creature defender = new Creature.Builder().hp(NOT_IMPORTANT)
            .attack(20)
            .defence(5)
            .build();
        // when
        attacker.attack(defender);
        // then
        assertThat(attacker.getCurrentHp()).isEqualTo(100);
    }

    @Test
    void defenderShouldCounterAttackOnlyOncePerTurn() {
        final Creature attacker = new Creature.Builder().hp(100)
            .attack(NOT_IMPORTANT)
            .defence(10)
            .build();
        final Creature defender = new Creature.Builder().hp(NOT_IMPORTANT)
            .attack(20)
            .defence(5)
            .build();
        // when
        attacker.attack(defender);
        attacker.attack(defender);
        // then
        assertThat(attacker.getCurrentHp()).isEqualTo(90);
    }

    @Test
    void attackerShouldNotCounterAttack() {
        final Random randomMock = mock(Random.class);
        when(randomMock.nextInt(anyInt())).thenReturn(3);

        final Creature attacker = new Creature.Builder().hp(100)
            .damage(Range.closed(5, 5))
            .attack(NOT_IMPORTANT)
            .defence(NOT_IMPORTANT)
            .build();
        final Creature defender = new Creature.Builder().hp(NOT_IMPORTANT)
            .damage(Range.closed(1, 10))
            .calculator(new DefaultDamageCalculator(randomMock))
            .attack(NOT_IMPORTANT)
            .defence(NOT_IMPORTANT)
            .build();
        // when
        attacker.attack(defender);
        // then
        assertThat(defender.getCurrentHp()).isEqualTo(95);
        assertThat(attacker.getCurrentHp()).isEqualTo(96);
    }

    @Test
    void counterAttackCounterShouldResetAfterEndOfTurn() {
        final Creature creature1 = new Creature.Builder().hp(100)
            .build();
        final Creature creature2 = new Creature.Builder().hp(100)
            .damage(Range.closed(10, 10))
            .build();
        final TurnQueue turnQueue = new TurnQueue(List.of(creature1), List.of(creature2));

        creature1.attack(creature2);
        creature1.attack(creature2);
        assertThat(creature1.getCurrentHp()).isEqualTo(90);
        turnQueue.next();
        turnQueue.next();
        creature1.attack(creature2);
        assertThat(creature1.getCurrentHp()).isEqualTo(80);
        // end of turn
    }

    @Test
    void creatureShouldHealAfterEndOfTurn() {
        final Creature creature1 = new Creature.Builder().hp(100)
            .damage(Range.closed(10, 10))
            .build();
        final Creature selfHealAfterEndOfTurnCreature =
            new SelfHealAfterTurnCreature(new Creature.Builder().hp(100)
                .build());
        final TurnQueue turnQueue =
            new TurnQueue(List.of(creature1), List.of(selfHealAfterEndOfTurnCreature));

        creature1.attack(selfHealAfterEndOfTurnCreature);
        assertThat(selfHealAfterEndOfTurnCreature.getCurrentHp()).isEqualTo(90);
        turnQueue.next();
        turnQueue.next();
        assertThat(selfHealAfterEndOfTurnCreature.getCurrentHp()).isEqualTo(100);
    }
}
