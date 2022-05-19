package pl.psi.specialfields;

import org.junit.jupiter.api.Test;
import pl.psi.Point;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EvilFogTest {

    @Test
    public void shouldGiveOneMoraleToAllBadAlignedCreaturesAndTakeOneMoraleFromAllGoodAlignedCreatures() {

        // given
        var evilFog = new EvilFog(new Point(10, 10));
        var goodAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .isGoodAligned(true)
                                .build()
                ).build();

        var secondGoodAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .isGoodAligned(true)
                                .build()
                ).build();

        var badAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .isGoodAligned(false)
                                .build()
                ).build();

        var secondBadAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder()
                                .isGoodAligned(false)
                                .build()
                ).build();

        var creatures = List.of(
                goodAlignedCreature,
                badAlignedCreature,
                secondGoodAlignedCreature,
                secondBadAlignedCreature

        );

        // when
        evilFog.putBuffOnAllCreatures(creatures);

        // then
        assertEquals(0, goodAlignedCreature.getMorale());
        assertEquals(0, secondGoodAlignedCreature.getMorale());
        assertEquals(2, badAlignedCreature.getMorale());
        assertEquals(2, secondBadAlignedCreature.getMorale());
    }

    @Test
    public void shouldThrowExceptionsWhenGivenDataIsIncorrect() {

        // given
        var evilFog = new EvilFog(new Point(10, 10));

        // when
        var exceptionWhenGivenDataIsNull = assertThrows(IllegalArgumentException.class, () -> evilFog.putBuffOnAllCreatures(null));
        var exceptionWhenGivenDataIsEmpty = assertThrows(IllegalArgumentException.class, () -> evilFog.putBuffOnAllCreatures(List.of()));

        // then
        assertEquals("Creatures list must not be null", exceptionWhenGivenDataIsNull.getMessage());
        assertEquals("Creatures list must not be empty", exceptionWhenGivenDataIsEmpty.getMessage());
    }

}
