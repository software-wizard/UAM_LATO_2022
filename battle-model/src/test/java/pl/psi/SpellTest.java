package pl.psi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import pl.psi.spells.Spell;
import pl.psi.spells.SpellFactory;

import java.util.Collections;
import java.util.List;


public class SpellTest {

    private static final Spell LIGHTING_BOLT_RANG_1 = new SpellFactory().create("LightingBolt", 1, 10);
    private static final Spell LIGHTING_BOLT_RANG_2 = new SpellFactory().create("LightingBolt", 2, 10);

    private static final Spell HASTE = new SpellFactory().create("Haste", 1, 10);
    private static final Spell FIREBALL = new SpellFactory().create("FireBall", 1, 10);

    private final Creature EXAMPLE_CREATURE = new Creature.Builder()
            .statistic(
                    CreatureStats.builder()
                            .moveRange(10)
                            .maxHp(100)
                            .build())
            .build();

    @Test
    void shouldCastLightningBoltAndTakeDamage() {
        //given
        List<Creature> creatures = List.of(EXAMPLE_CREATURE);

        final GameEngine gameEngine =
                new GameEngine(new Hero(Collections.emptyList(), List.of(LIGHTING_BOLT_RANG_1)),
                        new Hero(creatures, Collections.emptyList()));

        //when
        gameEngine.castSpell(new Point(14, 1), LIGHTING_BOLT_RANG_1, gameEngine.getHero1().getSpellPower());


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getCurrentHp()).isEqualTo(90);
    }

    @Test
    void shouldCastHasteAndIncreaseMoveRange() {
        //given
        List<Creature> creatures = List.of(EXAMPLE_CREATURE);

        final GameEngine gameEngine =
                new GameEngine(new Hero(Collections.emptyList(), List.of(HASTE)),
                        new Hero(creatures, Collections.emptyList()));


        //when
        gameEngine.castSpell(new Point(14, 1), HASTE, gameEngine.getHero1().getSpellPower());


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .isPresent()).isTrue();

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 1))
                .get().getStats().getMoveRange()).isEqualTo(20);
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
        List<Creature> creatures = List.of(EXAMPLE_CREATURE, secondCreature);

        final GameEngine gameEngine =
                new GameEngine(new Hero(Collections.emptyList(), List.of(FIREBALL)),
                        new Hero(creatures, Collections.emptyList()));
        gameEngine.move(new Point(14, 2));


        //when
        gameEngine.castSpell(new Point(14, 2), FIREBALL, gameEngine.getHero1().getSpellPower());


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 2))
                .isPresent()).isTrue();

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3))
                .isPresent()).isTrue();

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3)).get().getCurrentHp()).isEqualTo(90);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 2)).get().getCurrentHp()).isEqualTo(90);
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
        List<Creature> creatures = List.of(EXAMPLE_CREATURE, secondCreature);

        final GameEngine gameEngine =
                new GameEngine(new Hero(Collections.emptyList(), List.of(LIGHTING_BOLT_RANG_1, LIGHTING_BOLT_RANG_2), 1),
                        new Hero(creatures, Collections.emptyList()));
        gameEngine.move(new Point(14, 2));


        //when
        gameEngine.castSpell(new Point(14, 2), LIGHTING_BOLT_RANG_1, gameEngine.getHero1().getSpellPower());
        gameEngine.castSpell(new Point(14, 3), LIGHTING_BOLT_RANG_2, gameEngine.getHero1().getSpellPower());


        //then
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 2))
                .isPresent()).isTrue();

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3))
                .isPresent()).isTrue();

        Assertions.assertThat(gameEngine.getCreature(new Point(14, 2)).get().getCurrentHp()).isEqualTo(65);
        Assertions.assertThat(gameEngine.getCreature(new Point(14, 3)).get().getCurrentHp()).isEqualTo(55);
    }
}
