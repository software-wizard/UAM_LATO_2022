package pl.psi.spells;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.psi.GameEngine;
import pl.psi.Hero;
import pl.psi.Point;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

import static pl.psi.spells.SpellRang.ADVANCED;
import static pl.psi.spells.SpellRang.BASIC;


public class SpellTest {

    private static final Spell LIGHTING_BOLT_RANG_1 = new SpellFactory().create("LightingBolt", BASIC, 1);
    private static final Spell LIGHTING_BOLT_RANG_2 = new SpellFactory().create("LightingBolt", ADVANCED, 1);
    private static final Spell HASTE = new SpellFactory().create("Haste", BASIC, 1);
    private static final Spell FIREBALL = new SpellFactory().create("FireBall", BASIC, 1);

    private final Creature EXAMPLE_CREATURE_1 = new Creature.Builder()
            .statistic(
                    CreatureStats.builder()
                            .moveRange(10)
                            .maxHp(100)
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
    void shouldCastLightningBoltAndTakeDamage() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, List.of(LIGHTING_BOLT_RANG_1)),
                        new Hero(secondHeroCreatures, List.of(LIGHTING_BOLT_RANG_1)));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();


        //when
        gameEngine.castSpell(new Point(14, 1), LIGHTING_BOLT_RANG_1);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getCurrentHp()).isEqualTo(65);
    }

    @Test
    void shouldCastHasteAndIncreaseMoveRange() {
        //given
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);


        final GameEngine gameEngine =
                new GameEngine(new Hero(firstHeroCreatures, List.of(HASTE)),
                        new Hero(secondHeroCreatures, List.of(LIGHTING_BOLT_RANG_1)));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        //when
        gameEngine.castSpell(new Point(14, 1), HASTE);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getStatsWithSpells().getMoveRange()).isEqualTo(10); // ToDo: change to checking get summed up stats
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
        List<Creature> firstHeroCreatures = List.of(EXAMPLE_CREATURE_1, secondCreature);
        List<Creature> secondHeroCreatures = List.of(EXAMPLE_CREATURE_2);

        final GameEngine gameEngine =
                new GameEngine(new Hero(secondHeroCreatures, List.of(FIREBALL)),
                        new Hero(firstHeroCreatures, List.of(LIGHTING_BOLT_RANG_1)));

        gameEngine.pass();
        gameEngine.move(new Point(14, 2));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 2))
                .isPresent()).isTrue();

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3))
                .isPresent()).isTrue();


        //when
        gameEngine.castSpell(new Point(14, 2), FIREBALL);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3)).get().getCurrentHp()).isEqualTo(89);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 2)).get().getCurrentHp()).isEqualTo(89);
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
                new GameEngine(new Hero(secondHeroCreatures, List.of(LIGHTING_BOLT_RANG_1, LIGHTING_BOLT_RANG_2)),
                        new Hero(firstHeroCreatures, List.of(LIGHTING_BOLT_RANG_1)));

        gameEngine.pass();
        gameEngine.move(new Point(14, 2));

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 2))
                .isPresent()).isTrue();

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3))
                .isPresent()).isTrue();


        //when
        gameEngine.castSpell(new Point(14, 2), LIGHTING_BOLT_RANG_1);
        gameEngine.castSpell(new Point(14, 3), LIGHTING_BOLT_RANG_2);


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 2)).get().getCurrentHp()).isEqualTo(65);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3)).get().getCurrentHp()).isEqualTo(55);
    }
}
