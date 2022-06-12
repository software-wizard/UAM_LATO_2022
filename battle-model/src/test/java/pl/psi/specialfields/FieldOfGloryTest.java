package pl.psi.specialfields;

import org.junit.jupiter.api.Test;
import pl.psi.Point;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;


import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FieldOfGloryTest {

    @Test
    void shouldThrowExceptionsWhenDataIsIncorrect() {

        // given
        var fieldOfGlory = new FieldOfGlory(new Point(10, 10));

        // when
        var exceptionWhenGivenDataIsNull = assertThrows(IllegalArgumentException.class, () -> fieldOfGlory.buffCreatures(null));
        var exceptionWhenGivenDataIsEmpty = assertThrows(IllegalArgumentException.class, () -> fieldOfGlory.buffCreatures(List.of()));

        // then
        assertEquals("Creatures list must not be null", exceptionWhenGivenDataIsNull.getMessage());
        assertEquals("Creatures list must not be empty", exceptionWhenGivenDataIsEmpty.getMessage());
    }


    @Test
    void shouldTakeAwayTwoLuckFromAllCreatures() {

        // given
        var fieldOfGlory = new FieldOfGlory(new Point(10, 10));
        var Creature1 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).luck(1).build();

        var Creature2 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).luck(4).build();

        var Creature3 = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).luck(-2).build();


        var creatures = List.of(
                Creature1,
                Creature2,
                Creature3
        );

        // when
        fieldOfGlory.buffCreatures(creatures);

        // then
        assertEquals(-1, Creature1.getLuck());
        assertEquals(2, Creature2.getLuck());
        assertEquals(-4, Creature3.getLuck());
    }
}
