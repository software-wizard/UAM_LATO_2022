package pl.psi.spells;

import com.google.common.collect.Range;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.psi.GameEngine;
import pl.psi.Hero;
import pl.psi.Point;
import pl.psi.SpellsBook;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.creatures.CreatureStats;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.psi.creatures.CreatureStatistic.AIR_ELEMENTAL;
import static pl.psi.creatures.CreatureStatistic.WATER_ELEMENTAL;
import static pl.psi.spells.SpellNames.*;
import static pl.psi.spells.SpellRang.*;


public class SpellTest {

    private static final int NOT_IMPORTANT_MANA = Integer.MAX_VALUE;
    private static final int EXAMPLE_SPELL_POWER_1 = 1;
    private static final int EXAMPLE_SPELL_POWER_2 = 2;
    private static final Spell<? extends SpellableIf> MAGIC_ARROW_RANG_1 = new SpellFactory().create(MAGIC_ARROW, BASIC, EXAMPLE_SPELL_POWER_1);
    private static final Spell<? extends SpellableIf> MAGIC_ARROW_RANG_2 = new SpellFactory().create(MAGIC_ARROW, ADVANCED, EXAMPLE_SPELL_POWER_1);
    private static final Spell<? extends SpellableIf> LIGHTNING_BOLT_RANG_1 = new SpellFactory().create(LIGHTNING_BOLT, BASIC, EXAMPLE_SPELL_POWER_1);
    private static final Spell<? extends SpellableIf> HASTE_BASIC = new SpellFactory().create(HASTE, BASIC, EXAMPLE_SPELL_POWER_2);
    private static final Spell<? extends SpellableIf> HASTE_EXPERT = new SpellFactory().create(HASTE, EXPERT, EXAMPLE_SPELL_POWER_2);
    private static final Spell<? extends SpellableIf> SLOW_BASIC = new SpellFactory().create(SLOW, BASIC, EXAMPLE_SPELL_POWER_2);
    private static final Spell<? extends SpellableIf> SLOW_EXPERT = new SpellFactory().create(SLOW, EXPERT, EXAMPLE_SPELL_POWER_2);
    private static final Spell<? extends SpellableIf> FIREBALL = new SpellFactory().create(FIRE_BALL, BASIC, EXAMPLE_SPELL_POWER_1);
    private static final Spell<? extends SpellableIf> DISPEL_RANG_1 = new SpellFactory().create(DISPEL, BASIC, EXAMPLE_SPELL_POWER_1);
    private static final Spell<? extends SpellableIf> DISPEL_RANG_2 = new SpellFactory().create(DISPEL, ADVANCED, EXAMPLE_SPELL_POWER_1);
    private static final Spell<? extends SpellableIf> STONESKIN_RANG_1 = new SpellFactory().create(STONESKIN, BASIC, EXAMPLE_SPELL_POWER_2);
    private static final Spell<? extends SpellableIf> MISFORTUNE_RANG_1 = new SpellFactory().create(MISFORTUNE, BASIC, EXAMPLE_SPELL_POWER_2);
    private static final Spell<? extends SpellableIf> PRAYER_RANG_1 = new SpellFactory().create(PRAYER, BASIC, EXAMPLE_SPELL_POWER_2);
    private static final Spell<? extends SpellableIf> SUMMON_AIR_ELEMENTAL_RANG_1 = new SpellFactory().create(SUMMON_AIR_ELEMENTAL, BASIC, EXAMPLE_SPELL_POWER_2);
    private static final Spell<? extends SpellableIf> SUMMON_WATER_ELEMENTAL_RANG_1 = new SpellFactory().create(SUMMON_WATER_ELEMENTAL, BASIC, EXAMPLE_SPELL_POWER_2);


    private final Creature EXAMPLE_CREATURE_1 = new Creature.Builder()
            .statistic(
                    CreatureStats.builder()
                            .moveRange(10)
                            .maxHp(100)
                            .armor(100)
                            .build())
            .build();

    private final Creature EXAMPLE_CREATURE_2 = new Creature.Builder()
            .statistic(
                    CreatureStats.builder()
                            .moveRange(10)
                            .maxHp(100)
                            .build())
            .build();

    private final Creature EXAMPLE_CREATURE_3 = new Creature.Builder()
            .statistic(
                    CreatureStats.builder()
                            .name("C0")
                            .damage(Range.closed(10, 10))
                            .attack(50)
                            .moveRange(25)
                            .maxHp(100)
                            .build())
            .build();
    private final Creature EXAMPLE_CREATURE_4 = new Creature.Builder()
            .statistic(
                    CreatureStats.builder()
                            .name("C1")
                            .damage(Range.closed(10, 10))
                            .attack(50)
                            .moveRange(25)
                            .maxHp(100)
                            .build())
            .build();

    @Test
    void shouldCastMagicArrowAndTakeDamage() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();


        //when
        gameEngine.castSpell(new Point(14, 1), MAGIC_ARROW_RANG_1);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getCurrentHp()).isEqualTo(80);
    }

    @Test
    void shouldCastHasteAndIncreaseMoveRange() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);


        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(HASTE_BASIC)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        //when
        gameEngine.castSpell(new Point(14, 1), HASTE_BASIC);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getBuffedStats().getMoveRange()).isEqualTo(10); // ToDo: change to checking get summed up stats
    }

    @Test
    void creatureShouldHaveSecondCounterAttackWhenCounterstrikeCasted() {
        //when
        Spell<? extends SpellableIf> counterstrike = new SpellFactory().create(COUNTERSTRIKE, BASIC, 1);

        Creature creature1 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .name("C0")
                                .damage(Range.closed(10, 10))
                                .attack(50)
                                .moveRange(25)
                                .maxHp(200)
                                .build())
                .build();
        Creature creature2 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .name("C1")
                                .damage(Range.closed(10, 10))
                                .attack(50)
                                .moveRange(25)
                                .maxHp(100)
                                .build())
                .build();
        Creature creature3 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .name("C2")
                                .damage(Range.closed(10, 10))
                                .attack(50)
                                .moveRange(25)
                                .maxHp(100)
                                .build())
                .build();
        Creature creature4 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .name("C3")
                                .damage(Range.closed(10, 10))
                                .attack(50)
                                .moveRange(25)
                                .maxHp(100)
                                .build())
                .build();

        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(creature1), SpellsBook.builder().spells(List.of(counterstrike)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(List.of(creature2, creature3, creature4), SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        gameEngine.move(new Point(8, 2));
        gameEngine.pass();
        gameEngine.move(new Point(8, 1));
        gameEngine.pass();
        gameEngine.move(new Point(9, 2));
        gameEngine.pass();
        gameEngine.move(new Point(8, 3));
        gameEngine.pass();

        Assertions.assertThat(gameEngine.getCreature(new Point(8, 2))
                .isPresent()).isTrue();
        Assertions.assertThat(gameEngine.getCreature(new Point(8, 1))
                .isPresent()).isTrue();
        Assertions.assertThat(gameEngine.getCreature(new Point(9, 2))
                .isPresent()).isTrue();
        Assertions.assertThat(gameEngine.getCreature(new Point(8, 3))
                .isPresent()).isTrue();


        //when
        gameEngine.castSpell(new Point(8, 2), counterstrike);


        gameEngine.pass();
        gameEngine.attack(new Point(8, 2));
        gameEngine.attack(new Point(8, 2));
        gameEngine.attack(new Point(8, 2));
        gameEngine.attack(new Point(8, 2));


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(8, 2)).get().getCurrentHp()).isEqualTo(95);
        Assertions.assertThat(gameEngine.getCreature(new Point(8, 1)).get().getCurrentHp()).isEqualTo(65);
        Assertions.assertThat(gameEngine.getCreature(new Point(9, 2)).get().getCurrentHp()).isEqualTo(65);
        Assertions.assertThat(gameEngine.getCreature(new Point(8, 3)).get().getCurrentHp()).isEqualTo(100);

    }


    @Test
    void shouldCastFireBallAndDamageFieldAndNeighbours() {
        //given
        Creature secondCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .moveRange(10)
                                .maxHp(100)
                                .build())
                .build();

        Creature thirdCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .moveRange(10)
                                .maxHp(100)
                                .build())
                .build();
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1, secondCreature, thirdCreature);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        final GameEngine gameEngine =
                new GameEngine(new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(FIREBALL)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        gameEngine.pass();
        gameEngine.move(new Point(14, 2));
        gameEngine.pass();
        gameEngine.move(new Point(14, 5));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 2))
                .isPresent()).isTrue();

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3))
                .isPresent()).isTrue();

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 5))
                .isPresent()).isTrue();

        //when
        gameEngine.castSpell(new Point(14, 2), FIREBALL);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3)).get().getCurrentHp()).isEqualTo(89);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 2)).get().getCurrentHp()).isEqualTo(89);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 5)).get().getCurrentHp()).isEqualTo(100);
    }

    @Test
    void shouldBeCastedEvenIfTargetFieldIsEmpty() {
        //given
        Creature secondCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .moveRange(10)
                                .maxHp(100)
                                .build())
                .build();
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1, secondCreature);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        final GameEngine gameEngine =
                new GameEngine(new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(FIREBALL)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        gameEngine.pass();
        gameEngine.move(new Point(14, 2));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 2))
                .isPresent()).isTrue();

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3))
                .isPresent()).isTrue();


        //when
        gameEngine.castSpell(new Point(13, 2), FIREBALL);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3)).get().getCurrentHp()).isEqualTo(89);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 2)).get().getCurrentHp()).isEqualTo(89);
    }

    @Test
    void creatureShouldOnlyHaveThreeRunningSpells() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(HASTE_BASIC, PRAYER_RANG_1, STONESKIN_RANG_1, MISFORTUNE_RANG_1)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        //when
        gameEngine.castSpell(new Point(14, 1), HASTE_BASIC);
        gameEngine.castSpell(new Point(14, 1), PRAYER_RANG_1);
        gameEngine.castSpell(new Point(14, 1), STONESKIN_RANG_1);
        gameEngine.castSpell(new Point(14, 1), MISFORTUNE_RANG_1);

        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getRunningSpells().size()).isLessThanOrEqualTo(3);

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1)).get().getBuffedStats().getArmor()).isEqualTo(7);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1)).get().getBuffedStats().getAttack()).isEqualTo(4);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1)).get().getBuffedStats().getMoveRange()).isEqualTo(4);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1)).get().getLuck()).isEqualTo(9);
    }

    @Test
    void shouldTakeAppropriateDamageDependedOnRang() {
        //given
        Creature secondCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .moveRange(10)
                                .maxHp(100)
                                .build())
                .build();
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1, secondCreature);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        final GameEngine gameEngine =
                new GameEngine(new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1, MAGIC_ARROW_RANG_2)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        gameEngine.pass();
        gameEngine.move(new Point(14, 2));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 2))
                .isPresent()).isTrue();

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3))
                .isPresent()).isTrue();


        //when
        gameEngine.castSpell(new Point(14, 2), MAGIC_ARROW_RANG_1);
        gameEngine.castSpell(new Point(14, 3), MAGIC_ARROW_RANG_2);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 2)).get().getCurrentHp()).isEqualTo(80);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3)).get().getCurrentHp()).isEqualTo(70);
    }


    @Test
    void shouldCastHasteForAllAlliedCreaturesOnly() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);


        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(HASTE_EXPERT)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(0, 1))
                .isPresent()).isTrue();

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        //when
        gameEngine.castSpell(null, HASTE_EXPERT);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(0, 1))
                .get().getBuffedStats().getMoveRange()).isEqualTo(30); // ToDo: change to checking get summed up stats

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getBuffedStats().getMoveRange()).isEqualTo(0);
    }

    @Test
    void shouldCastSlowForAllEnemyCreaturesOnly() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);


        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(SLOW_EXPERT)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        //when
        gameEngine.castSpell(null, SLOW_EXPERT);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getBuffedStats().getMoveRange()).isEqualTo(-30);

        Assertions.assertThat(gameEngine.getCreature(new Point(0, 1))
                .get().getBuffedStats().getMoveRange()).isEqualTo(0);
    }


    @Test
    void shouldCastDeathRippleForAllCreatures() {
        //given
        Spell<? extends SpellableIf> deathRipple = new SpellFactory().create(DEATH_RIPPLE, BASIC, 1);
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);


        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(deathRipple)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(0, 1))
                .isPresent()).isTrue();
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        //when
        gameEngine.castSpell(null, deathRipple);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(0, 1))
                .get().getCurrentHp()).isEqualTo(85);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getCurrentHp()).isEqualTo(85);
    }

    @Test
    void shouldRunningForSpecificAmountOfRounds() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);


        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(HASTE_BASIC)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        //when
        gameEngine.castSpell(new Point(14, 1), HASTE_BASIC);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getBuffedStats().getMoveRange()).isEqualTo(10);
        gameEngine.pass();
        gameEngine.pass();
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getBuffedStats().getMoveRange()).isEqualTo(10);
        gameEngine.pass();
        gameEngine.pass();
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getBuffedStats().getMoveRange()).isEqualTo(0);
    }

    @Test
    void roundTimerShouldRemoveSpellFromRunningSpellsQueue() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);


        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(HASTE_BASIC)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        //when
        gameEngine.castSpell(new Point(14, 1), HASTE_BASIC);

        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1)).get().getRunningSpells())
                .hasSize(1)
                .isEqualTo(List.of(HASTE_BASIC));

        for (int i = 0; i < 4; i++) {
            gameEngine.pass();
        }

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1)).get().getRunningSpells())
                .isEqualTo(Collections.emptyList());
    }

    @Test
    void shouldClearRunningSpellAndUnCastSpellsWhenDispelCasted() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(DISPEL_RANG_2)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        gameEngine.castSpell(new Point(14, 1), HASTE_BASIC);

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getBuffedStats().getMoveRange()).isEqualTo(10);


        //when
        gameEngine.castSpell(new Point(14, 1), DISPEL_RANG_2);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1)).get().getBuffedStats().getMoveRange()).isEqualTo(0);
        assertThat(gameEngine.getCreature(new Point(14, 1)).get().getRunningSpells()).isEqualTo(Collections.emptyList());
    }

    @Test
    void shouldBlockCastingBasicDispelOnEnemyCreature() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(HASTE_BASIC, DISPEL_RANG_1)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        gameEngine.castSpell(new Point(14, 1), HASTE_BASIC);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getBuffedStats().getMoveRange()).isEqualTo(10);


        //when
        gameEngine.castSpell(new Point(14, 1), DISPEL_RANG_1);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1)).get().getBuffedStats().getMoveRange()).isEqualTo(10);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1)).get().getRunningSpells()).contains(HASTE_BASIC);
    }

    @Test
    void shouldAllowCastingBasicDispelOnAlliedCreature() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(HASTE_BASIC, DISPEL_RANG_1)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(0, 1))
                .isPresent()).isTrue();


        gameEngine.castSpell(new Point(0, 1), HASTE_BASIC);
        Assertions.assertThat(gameEngine.getCreature(new Point(0, 1))
                .get().getBuffedStats().getMoveRange()).isEqualTo(10);


        //when
        gameEngine.castSpell(new Point(0, 1), DISPEL_RANG_1);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(0, 1)).get().getBuffedStats().getMoveRange()).isEqualTo(0);
        Assertions.assertThat(gameEngine.getCreature(new Point(0, 1)).get().getRunningSpells()).isEmpty();
    }

    @Test
    void shouldUnCastHasteAndCastSlow() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(HASTE_BASIC, SLOW_BASIC)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        //when
        gameEngine.castSpell(new Point(14, 1), HASTE_BASIC);
        gameEngine.castSpell(new Point(14, 1), SLOW_BASIC);

        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1)).get().getBuffedStats().getMoveRange()).isEqualTo(-10);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1)).get().getRunningSpells()).hasSize(1).isEqualTo(List.of(SLOW_BASIC));

    }

    @Test
    void shouldBlockCastingSpellWhenNotEnoughMana() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(3).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();


        //when
        gameEngine.castSpell(new Point(14, 1), MAGIC_ARROW_RANG_1);


        //then

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getCurrentHp()).isEqualTo(100);
        Assertions.assertThat(gameEngine.getCurrentHero()
                .getSpellBook().getMana()).isEqualTo(3);
    }

    @Test
    void shouldTakeAppropriateAmountOfMana() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(10).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();


        //when
        gameEngine.castSpell(new Point(14, 1), MAGIC_ARROW_RANG_1);


        //then
        Assertions.assertThat(gameEngine.getCurrentHero()
                .getSpellBook().getMana()).isEqualTo(5);
    }

    @Test
    void shouldSetGraterFactorProtectionWhenCastedProtectionWithAdvanceRang() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        Spell<? extends SpellableIf> protectionFromAir = new SpellFactory().create(PROTECTION_FROM_AIR, ADVANCED, 1);

        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(protectionFromAir, LIGHTNING_BOLT_RANG_1)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        //when
        gameEngine.castSpell(new Point(14, 1), protectionFromAir);
        gameEngine.castSpell(new Point(14, 1), LIGHTNING_BOLT_RANG_1);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getRunningSpells()).contains(protectionFromAir);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getCurrentHp()).isEqualTo(82.5);
    }

    @Test
    void shouldReduceLightningBoltDamageWhenCreatureHasAirProtection() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        Spell<? extends SpellableIf> protectionFromAir = new SpellFactory().create(PROTECTION_FROM_AIR, BASIC, 1);

        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(LIGHTNING_BOLT_RANG_1)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        //when
        gameEngine.castSpell(new Point(14, 1), protectionFromAir);
        gameEngine.castSpell(new Point(14, 1), LIGHTNING_BOLT_RANG_1);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getRunningSpells()).contains(protectionFromAir);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getCurrentHp()).isEqualTo(75.5);
    }


    @Test
    void shouldBuffLuckWhenFortuneCasted() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        Spell<? extends SpellableIf> fortune = new SpellFactory().create(FORTUNE, BASIC, 1);

        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(fortune)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));


        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        //when
        gameEngine.castSpell(new Point(14, 1), fortune);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getRunningSpells()).contains(fortune);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getLuck()).isEqualTo(11);
    }

    @Test
    void shouldDebuffMoraleWhenSorrowCasted() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        Spell<? extends SpellableIf> sorrow = new SpellFactory().create(SORROW, BASIC, 1);

        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(sorrow)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));


        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();


        //when
        gameEngine.castSpell(new Point(14, 1), sorrow);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getRunningSpells()).contains(sorrow);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getMorale()).isEqualTo(0);
    }

    @Test
    void shouldUnCastTheOldestSpellAndCastNew() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(HASTE_BASIC, MISFORTUNE_RANG_1, PRAYER_RANG_1, STONESKIN_RANG_1)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();


        gameEngine.castSpell(new Point(14, 1), HASTE_BASIC);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getRunningSpells()).isEqualTo(List.of(HASTE_BASIC));

        gameEngine.castSpell(new Point(14, 1), MISFORTUNE_RANG_1);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getRunningSpells()).isEqualTo(List.of(HASTE_BASIC, MISFORTUNE_RANG_1));

        gameEngine.castSpell(new Point(14, 1), PRAYER_RANG_1);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getRunningSpells()).isEqualTo(List.of(HASTE_BASIC, MISFORTUNE_RANG_1, PRAYER_RANG_1));


        //when
        gameEngine.castSpell(new Point(14, 1), STONESKIN_RANG_1);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getRunningSpells()).isEqualTo(List.of(MISFORTUNE_RANG_1, PRAYER_RANG_1, STONESKIN_RANG_1));
    }


    @Test
    void shouldSpawnAppropriateElementalOnAppropriatePositionWithAppropriateAmount() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        Hero hero1 = new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(SUMMON_AIR_ELEMENTAL_RANG_1)).mana(NOT_IMPORTANT_MANA).build());
        Hero hero2 = new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(SUMMON_WATER_ELEMENTAL_RANG_1)).mana(NOT_IMPORTANT_MANA).build());

        final GameEngine gameEngine =
                new GameEngine(hero1, hero2);


        //when
        gameEngine.castSpell(null, SUMMON_AIR_ELEMENTAL_RANG_1);
        gameEngine.pass();
        gameEngine.castSpell(null, SUMMON_WATER_ELEMENTAL_RANG_1);


        //then
        Optional<Creature> expectedAirElemental = gameEngine.getCreature(new Point(0, 0));
        Assertions.assertThat(expectedAirElemental)
                .isPresent();
        Assertions.assertThat(hero1.getCreatures()).contains(expectedAirElemental.get());
        Assertions.assertThat(expectedAirElemental.get().getName())
                .isEqualTo(AIR_ELEMENTAL.getName());
        Assertions.assertThat(expectedAirElemental.get().getAmount())
                .isEqualTo(EXAMPLE_SPELL_POWER_2);


        Optional<Creature> expectedWaterElemental = gameEngine.getCreature(new Point(14, 0));
        Assertions.assertThat(expectedWaterElemental)
                .isPresent();
        Assertions.assertThat(hero2.getCreatures()).contains(expectedWaterElemental.get());
        Assertions.assertThat(expectedWaterElemental.get().getName())
                .isEqualTo(WATER_ELEMENTAL.getName());
        Assertions.assertThat(expectedWaterElemental.get().getAmount())
                .isEqualTo(EXAMPLE_SPELL_POWER_2);
    }

    @Test
    void waterElementalShouldBeImmuneForIceBolt() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        Spell<? extends SpellableIf> iceBolt = new SpellFactory().create(ICE_BOLT, BASIC, EXAMPLE_SPELL_POWER_1);

        Hero hero1 = new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(SUMMON_WATER_ELEMENTAL_RANG_1)).mana(NOT_IMPORTANT_MANA).build());
        Hero hero2 = new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(SUMMON_AIR_ELEMENTAL_RANG_1, iceBolt)).mana(NOT_IMPORTANT_MANA).build());

        final GameEngine gameEngine =
                new GameEngine(hero1, hero2);

        gameEngine.castSpell(null, SUMMON_WATER_ELEMENTAL_RANG_1);
        gameEngine.pass();

        Optional<Creature> expectedAirElemental = gameEngine.getCreature(new Point(0, 0));
        Assertions.assertThat(expectedAirElemental)
                .isPresent();

        //when
        gameEngine.castSpell(new Point(0, 0), iceBolt);


        //then
        Assertions.assertThat(expectedAirElemental.get().getCurrentHp()).isEqualTo(30);
    }

    @Test
    void airElementalShouldBeVulnerableForLightningBolt() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        Hero hero1 = new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(SUMMON_AIR_ELEMENTAL_RANG_1)).mana(NOT_IMPORTANT_MANA).build());
        Hero hero2 = new Hero(secondHeroCreatures, SpellsBook.builder().spells(List.of(SUMMON_WATER_ELEMENTAL_RANG_1, LIGHTNING_BOLT_RANG_1)).mana(NOT_IMPORTANT_MANA).build());

        final GameEngine gameEngine =
                new GameEngine(hero1, hero2);

        gameEngine.castSpell(null, SUMMON_AIR_ELEMENTAL_RANG_1);
        gameEngine.pass();

        Optional<Creature> expectedAirElemental = gameEngine.getCreature(new Point(0, 0));
        Assertions.assertThat(expectedAirElemental)
                .isPresent();

        //when
        gameEngine.castSpell(new Point(0, 0), LIGHTNING_BOLT_RANG_1);


        //then
        Assertions.assertThat(expectedAirElemental.get().getCurrentHp()).isEqualTo(0);
        Assertions.assertThat(expectedAirElemental.get().getAmount()).isEqualTo(0);
    }


    @Test
    void shouldDamageThreeNearestCreaturesWithCorrectValueWhenChainLightningCasted() {
        //when
        Spell<? extends SpellableIf> chainLightning = new SpellFactory().create(CHAIN_LIGHTNING, BASIC, 1);

        Creature creature5 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .name("C2")
                                .damage(Range.closed(10, 10))
                                .attack(50)
                                .moveRange(25)
                                .maxHp(100)
                                .build())
                .build();
        Creature creature6 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .name("C3")
                                .damage(Range.closed(10, 10))
                                .attack(50)
                                .moveRange(25)
                                .maxHp(100)
                                .build())
                .build();

        Creature creature7 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .name("C3")
                                .damage(Range.closed(10, 10))
                                .attack(50)
                                .moveRange(25)
                                .maxHp(100)
                                .build())
                .build();

        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of(EXAMPLE_CREATURE_3), SpellsBook.builder().spells(List.of(chainLightning)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(List.of(EXAMPLE_CREATURE_4, creature5, creature6, creature7), SpellsBook.builder().mana(NOT_IMPORTANT_MANA).build()));


        Assertions.assertThat(gameEngine.getCreature(new Point(0, 1))
                .isPresent()).isTrue();
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3))
                .isPresent()).isTrue();
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 5))
                .isPresent()).isTrue();
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 7))
                .isPresent()).isTrue();


        //when
        gameEngine.castSpell(new Point(14, 3), chainLightning);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(0, 1)).get().getCurrentHp()).isEqualTo(100);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1)).get().getCurrentHp()).isEqualTo(67.5);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3)).get().getCurrentHp()).isEqualTo(35);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 5)).get().getCurrentHp()).isEqualTo(83.75);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 7)).get().getCurrentHp()).isEqualTo(100);
    }

    @Test
    void creatureShouldAttackWithMaximumDamageWhenBlessBasicCasted() {
        //given
        Spell<? extends SpellableIf> bless = new SpellFactory().create(BLESS, BASIC, EXAMPLE_SPELL_POWER_1);

        Creature creature1 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .name("C0")
                                .damage(Range.closed(2, 10))
                                .moveRange(25)
                                .maxHp(100)
                                .build())
                .build();
        Creature creature2 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .name("C1")
                                .damage(Range.closed(2, 10))
                                .moveRange(25)
                                .maxHp(100)
                                .build())
                .build();

        Hero hero1 = new Hero(List.of(creature1), SpellsBook.builder().spells(List.of(bless)).mana(NOT_IMPORTANT_MANA).build());
        Hero hero2 = new Hero(List.of(creature2), SpellsBook.builder().mana(NOT_IMPORTANT_MANA).build());

        final GameEngine gameEngine =
                new GameEngine(hero1, hero2);


        gameEngine.move(new Point(8, 1));
        gameEngine.pass();
        gameEngine.move(new Point(9, 1));
        gameEngine.pass();

        Assertions.assertThat(gameEngine.getCreature(new Point(8, 1))).isPresent();
        Assertions.assertThat(gameEngine.getCreature(new Point(9, 1))).isPresent();

        //when
        gameEngine.castSpell(new Point(8, 1), bless);
        gameEngine.attack(new Point(9, 1));

        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(9, 1)).get().getCurrentHp()).isEqualTo(90);
    }

    @Test
    void creatureShouldAttackWithMaximumDamagePlusOneWhenBlessAdvancedCasted() {
        //given
        Spell<? extends SpellableIf> bless = new SpellFactory().create(BLESS, ADVANCED, EXAMPLE_SPELL_POWER_1);

        Creature creature1 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .name("C0")
                                .damage(Range.closed(2, 10))
                                .moveRange(25)
                                .maxHp(100)
                                .build())
                .build();
        Creature creature2 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .name("C1")
                                .damage(Range.closed(2, 10))
                                .moveRange(25)
                                .maxHp(100)
                                .build())
                .build();

        Hero hero1 = new Hero(List.of(creature1), SpellsBook.builder().spells(List.of(bless)).mana(NOT_IMPORTANT_MANA).build());
        Hero hero2 = new Hero(List.of(creature2), SpellsBook.builder().mana(NOT_IMPORTANT_MANA).build());

        final GameEngine gameEngine =
                new GameEngine(hero1, hero2);


        gameEngine.move(new Point(8, 1));
        gameEngine.pass();
        gameEngine.move(new Point(9, 1));
        gameEngine.pass();

        Assertions.assertThat(gameEngine.getCreature(new Point(8, 1))).isPresent();
        Assertions.assertThat(gameEngine.getCreature(new Point(9, 1))).isPresent();

        //when
        gameEngine.castSpell(new Point(8, 1), bless);
        gameEngine.attack(new Point(9, 1));

        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(9, 1)).get().getCurrentHp()).isEqualTo(89);
    }

    @Test
    void creatureShouldAttackWithEightyPercentMinusOneWhenCurseExpertCasted() {
        //given
        Spell<? extends SpellableIf> curse = new SpellFactory().create(CURSE, ADVANCED, EXAMPLE_SPELL_POWER_1);

        Creature creature1 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .name("C0")
                                .damage(Range.closed(10, 20))
                                .moveRange(25)
                                .maxHp(100)
                                .build())
                .build();
        Creature creature2 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .name("C1")
                                .damage(Range.closed(10, 20))
                                .moveRange(25)
                                .maxHp(100)
                                .build())
                .build();

        Hero hero1 = new Hero(List.of(creature1), SpellsBook.builder().spells(List.of(curse)).mana(NOT_IMPORTANT_MANA).build());
        Hero hero2 = new Hero(List.of(creature2), SpellsBook.builder().mana(NOT_IMPORTANT_MANA).build());

        final GameEngine gameEngine =
                new GameEngine(hero1, hero2);


        gameEngine.move(new Point(8, 1));
        gameEngine.pass();
        gameEngine.move(new Point(9, 1));
        gameEngine.pass();

        Assertions.assertThat(gameEngine.getCreature(new Point(8, 1))).isPresent();
        Assertions.assertThat(gameEngine.getCreature(new Point(9, 1))).isPresent();

        //when
        gameEngine.castSpell(new Point(8, 1), curse);
        gameEngine.attack(new Point(9, 1));

        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(9, 1)).get().getCurrentHp()).isEqualTo(91);
    }

    @Test
    void shouldRemoveAllNegativeEffectsAndHealWhenCureCasted() {
        //given
        Spell<? extends SpellableIf> cure = new SpellFactory().create(CURE, BASIC, EXAMPLE_SPELL_POWER_1);

        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, SpellsBook.builder().spells(List.of(cure, SLOW_BASIC, HASTE_BASIC, MAGIC_ARROW_RANG_1)).mana(NOT_IMPORTANT_MANA).build()),
                        new Hero(secondHeroCreatures, SpellsBook.builder().mana(NOT_IMPORTANT_MANA).build()));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();



        gameEngine.castSpell(new Point(14, 1), MAGIC_ARROW_RANG_1);
        gameEngine.castSpell(new Point(14, 1), MISFORTUNE_RANG_1);
        gameEngine.castSpell(new Point(14, 1), HASTE_BASIC);

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getRunningSpells()).isEqualTo(List.of(MISFORTUNE_RANG_1, HASTE_BASIC));

        //when
        gameEngine.pass();
        gameEngine.castSpell(new Point(14, 1), cure);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getRunningSpells()).isEqualTo(List.of(HASTE_BASIC));
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getCurrentHp()).isEqualTo(95);
    }
}
