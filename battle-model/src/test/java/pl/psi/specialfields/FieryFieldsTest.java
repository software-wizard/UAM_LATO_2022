package pl.psi.specialfields;

import org.junit.jupiter.api.Test;
import pl.psi.Point;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FieryFieldsTest {


    @Test
    void shouldGive3SpellPowerToFireSpells() {

        // given
        var fireSpell1 = new SpellFactory().create("Fireball", 1, 10);

        var fireSpell2 = new SpellFactory().create("Fireball", 2, 10);

        var differentSpell = new SpellFactory().create("Haste", 1, 10);


        var spells = List.of(
                fireSpell1,
                fireSpell2,
                differentSpell
        );

        // when
        FieryFields.buffSpells(spells);

        // then
        assertEquals(3, fireSpell1.getRang());
        assertEquals(3, fireSpell2.getRang());
        assertEquals(1, differentSpell.getRang());
    }

    oid shouldNotGive3SpellPowerToSpellsDifferentThanFire() {

        // given
        var differentSpell1 = new SpellFactory().create("LightingBolt", 1, 10);

        var differentSpell2 = new SpellFactory().create("LightingBolt", 2, 10);

        var differentSpell3 = new SpellFactory().create("Haste", 1, 10);


        var spells = List.of(
                differentSpell1,
                differentSpell2,
                differentSpell3
        );

        // when
        FieryFields.buffSpells(spells);

        // then
        assertEquals(1, differentSpell1.getRang());
        assertEquals(2, differentSpell2.getRang());
        assertEquals(1, differentSpell3.getRang());
    }
}
