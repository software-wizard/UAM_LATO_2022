package pl.psi.specialfields;

import org.junit.jupiter.api.Test;
import pl.psi.Point;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import static java.util.List.*;
import static org.junit.jupiter.api.Assertions.*;
import static pl.psi.creatures.Alignment.*;

public class EvilFogTest {

    @Test
    void shouldReturnCorrectBuiltEvilFogObject() {
        // when
        var evilFog = EvilFog
                .builder()
                .point(new Point(10, 10))
                .build();

        var evilFogWithoutPoint = EvilFog
                .builder()
                .build();

        // then
        assertEquals(new Point(10, 10), evilFog.getPoint());
        assertNull(evilFogWithoutPoint.getPoint());
    }

    @Test
    void shouldGiveOneMoraleToBadAlignedCreature() {
        // given
        var evilFog = EvilFog
                .builder()
                .point(new Point(10, 10))
                .build();

        var badAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).alignment(EVIL).build();

        // when
        evilFog.buffCreature(badAlignedCreature);

        // then
        assertEquals(2, badAlignedCreature.getMorale());
    }

    @Test
    void shouldReduceOneMoraleFromGoodAlignedCreature() {
        // given
        var evilFog = EvilFog
                .builder()
                .point(new Point(10, 10))
                .build();

        var goodAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).alignment(GOOD).build();

        // when
        evilFog.buffCreature(goodAlignedCreature);

        // then
        assertEquals(0, goodAlignedCreature.getMorale());
    }

    @Test
    void shouldThrowExceptionWhenGivenCreatureIsNull() {
        // given
        var evilFog = EvilFog
                .builder()
                .point(new Point(10, 10))
                .build();

        // when
        var exception = assertThrows(IllegalArgumentException.class, () -> evilFog.buffCreature(null));

        // then
        assertEquals("Creature must not be null", exception.getMessage());
    }

    @Test
    void shouldGiveOneMoraleToAllBadAlignedCreaturesAndTakeOneMoraleFromAllGoodAlignedCreatures() {

        // given
        var evilFog = EvilFog
                .builder()
                .point(new Point(10, 10))
                .build();

        var goodAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).alignment(GOOD).build();

        var secondGoodAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).alignment(GOOD).build();

        var badAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).alignment(EVIL).build();

        var secondBadAlignedCreature = new Creature.Builder()
                .statistic(
                        CreatureStats.builder().build()
                ).alignment(EVIL).build();

        var creatures = of(
                goodAlignedCreature,
                badAlignedCreature,
                secondGoodAlignedCreature,
                secondBadAlignedCreature
        );

        // when
        evilFog.handleEffect(creatures);

        // then
        assertEquals(0, goodAlignedCreature.getMorale());
        assertEquals(0, secondGoodAlignedCreature.getMorale());
        assertEquals(2, badAlignedCreature.getMorale());
        assertEquals(2, secondBadAlignedCreature.getMorale());
    }

    @Test
    void shouldThrowExceptionsWhenGivenDataIsIncorrect() {

        // given
        var evilFog = EvilFog
                .builder()
                .point(new Point(10, 10))
                .build();

        // when
        var exceptionWhenGivenDataIsNull = assertThrows(IllegalArgumentException.class, () -> evilFog.handleEffect(null));
        var exceptionWhenGivenDataIsEmpty = assertThrows(IllegalArgumentException.class, () -> evilFog.handleEffect(of()));

        // then
        assertEquals("Creatures list must not be null", exceptionWhenGivenDataIsNull.getMessage());
        assertEquals("Creatures list must not be empty", exceptionWhenGivenDataIsEmpty.getMessage());
    }

}
