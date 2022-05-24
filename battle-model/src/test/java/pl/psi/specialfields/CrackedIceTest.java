package pl.psi.specialfields;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CrackedIceTest {
    @Test
    void shouldReduceDefenseOfAllTroopsBy5()
    {
        // given
        CrackedIce crackedIce = new CrackedIce();
        Creature creature1 = new Creature.Builder().statistic(
            CreatureStats.builder().build()
        ).defense(15).build();
        Creature creature2 = new Creature.Builder().statistic(
            CreatureStats.builder().build()
        ).defense(2).build();
        List<Creature> creatures = List.of(creature1, creature2);

        // when
        crackedIce.buffCreatures(creatures);

        // then
        assertThat(creature1.getDefense()).isEqualTo(10);
        assertThat(creature2.getDefense()).isEqualTo(-3);
    }

    @Test
    void shouldReduceDefenseOfSingleTroopBy5()
    {
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
}
