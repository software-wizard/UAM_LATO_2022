package pl.psi.spells;

import com.google.common.collect.Range;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.psi.GameEngine;
import pl.psi.Hero;
import pl.psi.Point;
import pl.psi.SpellsBook;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.psi.spells.SpellNames.*;
import static pl.psi.spells.SpellRang.*;


public class SpellTest {

    private static final int NOT_IMPORTANT_MANA = Integer.MAX_VALUE;

    private static final Spell<? extends SpellableIf> MAGIC_ARROW_RANG_1 = new SpellFactory().create(MAGIC_ARROW, BASIC, 1);
    private static final Spell<? extends SpellableIf> MAGIC_ARROW_RANG_2 = new SpellFactory().create(MAGIC_ARROW, ADVANCED, 1);
    private static final Spell<? extends SpellableIf> HASTE_BASIC = new SpellFactory().create(HASTE, BASIC, 1);
    private static final Spell<? extends SpellableIf> HASTE_EXPERT = new SpellFactory().create(HASTE, EXPERT, 1);
    private static final Spell<? extends SpellableIf> SLOW_BASIC = new SpellFactory().create(SLOW, BASIC, 1);
    private static final Spell<? extends SpellableIf> SLOW_EXPERT = new SpellFactory().create(SLOW, EXPERT, 1);
    private static final Spell<? extends SpellableIf> FIREBALL = new SpellFactory().create(FIRE_BALL, BASIC, 1);
    private static final Spell<? extends SpellableIf> DISPEL_RANG_1 = new SpellFactory().create(DISPEL, BASIC, 1);
    private static final Spell<? extends SpellableIf> DISPEL_RANG_2 = new SpellFactory().create(DISPEL, ADVANCED, 1);
    private static final Spell<? extends SpellableIf> STONESKIN_RANG_1 = new SpellFactory().create(STONESKIN, BASIC, 1);
    private static final Spell<? extends SpellableIf> MISFORTUNE_RANG_1 = new SpellFactory().create(MISFORTUNE, BASIC, 1);
    private static final Spell<? extends SpellableIf> PRAYER_RANG_1 = new SpellFactory().create(PRAYER, BASIC, 1);


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
                                .damage(Range.closed(10, 10))
                                .attack(50)
                                .moveRange(25)
                                .maxHp(200)
                                .build())
                .build();
        Creature creature2 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .damage(Range.closed(10, 10))
                                .attack(50)
                                .moveRange(25)
                                .maxHp(100)
                                .build())
                .build();
        Creature creature3 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .damage(Range.closed(10, 10))
                                .attack(50)
                                .moveRange(25)
                                .maxHp(100)
                                .build())
                .build();
        Creature creature4 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
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
        creature2.attack(creature1);
        gameEngine.pass();
        creature3.attack(creature1);// ?????
        gameEngine.pass();
        creature4.attack(creature1);

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
    void shouldBlockCastingBasicDispelOnEnemyCreatureAndAllowForAllied() {
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
    void shouldBlockCastingSpellWhenNotEnoughMana(){
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
    void shouldTakeAppropriateAmountOfMana(){
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
    void shouldUnCastTheOldestSpellAndCastNew() {

    }

}
