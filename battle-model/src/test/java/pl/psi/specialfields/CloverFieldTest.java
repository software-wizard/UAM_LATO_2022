package pl.psi.specialfields;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.Alignment;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CloverFieldTest {
    @Test
    void shouldGive2LuckToAllNeutrallyAlignedCreatures() {
        // given
        CloverField cloverField = new CloverField();
        Creature creature1 = new Creature.Builder().statistic(
                CreatureStats.builder().build()
        ).luck(2).build();
        Creature creature2 = new Creature.Builder().statistic(
                CreatureStats.builder().build()
        ).luck(5).build();
        Creature creature3 = new Creature.Builder().statistic(
                CreatureStats.builder().build()
        ).luck(-2).build();
        Creature creature4 = new Creature.Builder().statistic(
                CreatureStats.builder().build()
        ).luck(-4).build();
        List<Creature> creatures = List.of(creature1, creature2, creature3, creature4);

        // when
        cloverField.buffCreatures(creatures);

        // then
        assertThat(creature1.getLuck()).isEqualTo(4);
        assertThat(creature2.getLuck()).isEqualTo(7);
        assertThat(creature3.getLuck()).isEqualTo(0);
        assertThat(creature4.getLuck()).isEqualTo(-2);
    }

    @Test
    void shouldNotGive2LuckToCreaturesOtherThanNeutrallyAligned() {
        // given
        CloverField cloverField = new CloverField();
        Creature creature1 = new Creature.Builder().statistic(
                CreatureStats.builder().build()
        ).luck(2).alignment(Alignment.EVIL).build();
        Creature creature2 = new Creature.Builder().statistic(
                CreatureStats.builder().build()
        ).luck(5).alignment(Alignment.GOOD).build();
        Creature creature3 = new Creature.Builder().statistic(
                CreatureStats.builder().build()
        ).luck(-2).alignment(Alignment.GOOD).build();
        Creature creature4 = new Creature.Builder().statistic(
                CreatureStats.builder().build()
        ).luck(-4).alignment(Alignment.EVIL).build();
        List<Creature> creatures = List.of(creature1, creature2, creature3, creature4);

        // when
        cloverField.buffCreatures(creatures);

        // then
        assertThat(creature1.getLuck()).isEqualTo(2);
        assertThat(creature2.getLuck()).isEqualTo(5);
        assertThat(creature3.getLuck()).isEqualTo(-2);
        assertThat(creature4.getLuck()).isEqualTo(-4);
    }

    @Test
    void shouldGive2LuckToNeutrallyAlignedCreature() {
        // given
        CloverField cloverField = new CloverField();
        Creature creature1 = new Creature.Builder().statistic(
                CreatureStats.builder().build()
        ).luck(2).build();

        // when
        cloverField.buffCreature(creature1);

        // then
        assertThat(creature1.getLuck()).isEqualTo(4);
    }

    @Test
    void shouldNotGive2LuckToEvilAlignedCreature() {
        // given
        CloverField cloverField = new CloverField();
        Creature creature1 = new Creature.Builder().statistic(
                CreatureStats.builder().build()
        ).luck(2).alignment(Alignment.EVIL).build();

        // when
        cloverField.buffCreature(creature1);

        // then
        assertThat(creature1.getLuck()).isEqualTo(2);
    }

    @Test
    void shouldNotGive2LuckToGoodAlignedCreature() {
        // given
        CloverField cloverField = new CloverField();
        Creature creature1 = new Creature.Builder().statistic(
                CreatureStats.builder().build()
        ).luck(2).alignment(Alignment.GOOD).build();

        // when
        cloverField.buffCreature(creature1);

        // then
        assertThat(creature1.getLuck()).isEqualTo(2);
    }
}
