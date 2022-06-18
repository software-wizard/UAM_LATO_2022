package pl.psi.specialfields;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.psi.creatures.Alignment;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class CrackedIceTest {
    @Test
    void shouldReduceDefenseOfAllTroopsBy5() {
        // given
        CrackedIce crackedIce = new CrackedIce();
        Creature creature1 = new Creature.Builder().statistic(
            CreatureStats.builder().build()
        ).defense(15).build();
        Creature creature2 = new Creature.Builder().statistic(
            CreatureStats.builder().build()
        ).defense(2).build();
        Creature creature3 = new Creature.Builder().statistic(
            CreatureStats.builder().build()
        ).defense(-4).alignment(Alignment.EVIL).build();
        Creature creature4 = new Creature.Builder().statistic(
            CreatureStats.builder().build()
        ).defense(15).alignment(Alignment.GOOD).build();

        List<Creature> creatures = List.of(creature1, creature2, creature3, creature4);

        // when
        crackedIce.buffCreatures(creatures);

        // then
        assertThat(creature1.getDefense()).isEqualTo(10);
        assertThat(creature2.getDefense()).isEqualTo(-3);
        assertThat(creature3.getDefense()).isEqualTo(-9);
        assertThat(creature4.getDefense()).isEqualTo(10);
    }

    @Test
    void shouldReduceDefenseOfAllEvilAlignedCreaturesBy5() {
        // given
        CrackedIce crackedIce = new CrackedIce();
        Creature creature1 = new Creature.Builder().statistic(
            CreatureStats.builder().build()
        ).defense(2).alignment(Alignment.EVIL).build();
        Creature creature2 = new Creature.Builder().statistic(
            CreatureStats.builder().build()
        ).defense(-4).alignment(Alignment.EVIL).build();

        List<Creature> creatures = List.of(creature1, creature2);

        // when
        crackedIce.buffCreatures(creatures);

        // then
        assertThat(creature1.getDefense()).isEqualTo(-3);
        assertThat(creature2.getDefense()).isEqualTo(-9);
    }

    @Test
    void shouldReduceDefenseOfAllGoodAlignedCreaturesBy5() {
        // given
        CrackedIce crackedIce = new CrackedIce();
        Creature creature1 = new Creature.Builder().statistic(
            CreatureStats.builder().build()
        ).defense(6).alignment(Alignment.GOOD).build();
        Creature creature2 = new Creature.Builder().statistic(
            CreatureStats.builder().build()
        ).defense(12).alignment(Alignment.GOOD).build();

        List<Creature> creatures = List.of(creature1, creature2);

        // when
        crackedIce.buffCreatures(creatures);

        // then
        assertThat(creature1.getDefense()).isEqualTo(1);
        assertThat(creature2.getDefense()).isEqualTo(7);
    }

    @Test
    void shouldReduceDefenseOfSingleTroopBy5() {
        // given
        CrackedIce crackedIce = new CrackedIce();
        Creature creature = new Creature.Builder().statistic(
                CreatureStats.builder().build()
        ).defense(4).build();

        // when
        crackedIce.buffCreature(creature);

        // then
        assertThat(creature.getDefense()).isEqualTo(-1);
    }

    @Test
    void shouldReduceDefenseOfSingleEvilCreatureBy5() {
        // given
        CrackedIce crackedIce = new CrackedIce();
        Creature creature = new Creature.Builder().statistic(
            CreatureStats.builder().build()
        ).defense(20).alignment(Alignment.EVIL).build();

        // when
        crackedIce.buffCreature(creature);

        // then
        assertThat(creature.getDefense()).isEqualTo(15);
    }

    @Test
    void shouldReduceDefenseOfSingleGoodCreatureBy5() {
        // given
        CrackedIce crackedIce = new CrackedIce();
        Creature creature = new Creature.Builder().statistic(
            CreatureStats.builder().build()
        ).defense(50).alignment(Alignment.GOOD).build();

        // when
        crackedIce.buffCreature(creature);

        // then
        assertThat(creature.getDefense()).isEqualTo(45);
    }
}
