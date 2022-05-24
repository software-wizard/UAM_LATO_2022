package pl.psi.specialfields;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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
                CreatureStats.builder().armor(15).build()
        ).build();
        Creature creature2 = new Creature.Builder().statistic(
                CreatureStats.builder().armor(2).build()
        ).build();
        List<Creature> creatures = List.of(creature1, creature2);

        // when
        crackedIce.buffCreatures(creatures);

        // then
        assertThat(creature1.getArmor()).isEqualTo(10);
        assertThat(creature2.getArmor()).isEqualTo(-3);
    }

    @Test
    void shouldReduceDefenseOfSingleTroopBy5() {
        // given
        CrackedIce crackedIce = new CrackedIce();
        Creature creature = new Creature.Builder().statistic(
                CreatureStats.builder().armor(4).build()
        ).build();

        // when
        crackedIce.buffCreature(creature);

        // then
        assertThat(creature.getArmor()).isEqualTo(-1);
    }
}
