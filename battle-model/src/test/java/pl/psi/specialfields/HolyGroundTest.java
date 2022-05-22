package pl.psi.specialfields;

import org.junit.jupiter.api.Test;
import pl.psi.Point;
import pl.psi.creatures.Alignment;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HolyGroundTest {

    @Test
    void shouldGiveOneMoraleToGoodAlignedCreature() {
        // given
        var holyGround = new HolyGround(new Point(10, 10));
        var goodAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).alignment(Alignment.GOOD).build();

        // when
        holyGround.buffCreature(goodAlignedCreature);

        // then
        assertEquals(2, goodAlignedCreature.getMorale());
    }

    @Test
    void shouldTakeOneMoraleFromBadAlignedCreature() {
        // given
        var holyGround = new HolyGround(new Point(10, 10));
        var badAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).alignment(Alignment.EVIL).build();

        // when
        holyGround.buffCreature(badAlignedCreature);

        // then
        assertEquals(0, badAlignedCreature.getMorale());
    }

    @Test
    void shouldThrowExceptionWhenGivenCreatureIsNull() {
        // given
        var holyGround = new HolyGround(new Point(10, 10));

        // when
        var exception = assertThrows(IllegalArgumentException.class, () -> holyGround.buffCreature(null));

        // then
        assertEquals("Creature must not be null", exception.getMessage());
    }

    @Test
    void shouldGiveOneMoraleToAllGoodAlignedCreaturesAndTakeOneMoraleFromAllBadAlignedCreatures() {

        // given
        var holyGround = new HolyGround(new Point(10, 10));
        var goodAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).alignment(Alignment.GOOD).build();

        var secondGoodAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).alignment(Alignment.GOOD).build();

        var badAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).alignment(Alignment.EVIL).build();

        var secondBadAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).alignment(Alignment.EVIL).build();

        var creatures = List.of(
                goodAlignedCreature,
                badAlignedCreature,
                secondGoodAlignedCreature,
                secondBadAlignedCreature

        );

        // when
        holyGround.buffCreatures(creatures);

        // then
        assertEquals(2, goodAlignedCreature.getMorale());
        assertEquals(2, secondGoodAlignedCreature.getMorale());
        assertEquals(0, badAlignedCreature.getMorale());
        assertEquals(0, secondBadAlignedCreature.getMorale());
    }

    @Test
    void shouldThrowExceptionsWhenGivenDataIsIncorrect() {

        // given
        var holyGround = new HolyGround(new Point(10, 10));

        // when
        var exceptionWhenGivenDataIsNull = assertThrows(IllegalArgumentException.class, () -> holyGround.buffCreatures(null));
        var exceptionWhenGivenDataIsEmpty = assertThrows(IllegalArgumentException.class, () -> holyGround.buffCreatures(List.of()));

        // then
        assertEquals("Creatures list must not be null", exceptionWhenGivenDataIsNull.getMessage());
        assertEquals("Creatures list must not be empty", exceptionWhenGivenDataIsEmpty.getMessage());
    }
}
