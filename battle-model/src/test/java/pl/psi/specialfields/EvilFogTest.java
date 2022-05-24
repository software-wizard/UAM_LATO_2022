package pl.psi.specialfields;

import org.junit.jupiter.api.Test;
import pl.psi.Point;
import pl.psi.creatures.Alignment;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EvilFogTest {

    @Test
    void shouldGiveOneMoraleToBadAlignedCreature() {
        // given
        var evilFog = new EvilFog(new Point(10, 10));
        var badAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).alignment(Alignment.EVIL).build();

        // when
        evilFog.buffCreature(badAlignedCreature);

        // then
        assertEquals(2, badAlignedCreature.getMorale());
    }

    @Test
    void shouldReduceOneMoraleFromGoodAlignedCreature() {
        // given
        var evilFog = new EvilFog(new Point(10, 10));
        var goodAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).alignment(Alignment.GOOD).build();

        // when
        evilFog.buffCreature(goodAlignedCreature);

        // then
        assertEquals(0, goodAlignedCreature.getMorale());
    }

    @Test
    void shouldThrowExceptionWhenGivenCreatureIsNull() {
        // given
        var evilFog = new EvilFog(new Point(10, 10));

        // when
        var exception = assertThrows(IllegalArgumentException.class, () -> evilFog.buffCreature(null));

        // then
        assertEquals("Creature must not be null", exception.getMessage());
    }

    @Test
    void shouldGiveOneMoraleToAllBadAlignedCreaturesAndTakeOneMoraleFromAllGoodAlignedCreatures() {

        // given
        var evilFog = new EvilFog(new Point(10, 10));
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
        evilFog.buffCreatures(creatures);

        // then
        assertEquals(0, goodAlignedCreature.getMorale());
        assertEquals(0, secondGoodAlignedCreature.getMorale());
        assertEquals(2, badAlignedCreature.getMorale());
        assertEquals(2, secondBadAlignedCreature.getMorale());
    }

    @Test
    void shouldThrowExceptionsWhenGivenDataIsIncorrect() {

        // given
        var evilFog = new EvilFog(new Point(10, 10));

        // when
        var exceptionWhenGivenDataIsNull = assertThrows(IllegalArgumentException.class, () -> evilFog.buffCreatures(null));
        var exceptionWhenGivenDataIsEmpty = assertThrows(IllegalArgumentException.class, () -> evilFog.buffCreatures(List.of()));

        // then
        assertEquals("Creatures list must not be null", exceptionWhenGivenDataIsNull.getMessage());
        assertEquals("Creatures list must not be empty", exceptionWhenGivenDataIsEmpty.getMessage());
    }

}
