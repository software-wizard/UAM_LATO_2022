package pl.psi.spells;

import pl.psi.creatures.CreatureStats;

import static pl.psi.spells.SpellMagicClass.*;
import static pl.psi.spells.SpellNames.*;
import static pl.psi.spells.SpellRang.*;
import static pl.psi.spells.SpellTypes.*;

public class SpellFactory {

    private static final String EXCEPTION_MESSAGE = "We support rang from 1 to 7";

    public Spell<?> create(SpellNames name, SpellRang rang, int spellPower) {

        switch (name) {
            case MAGIC_ARROW:
                switch (rang) {
                    case BASIC:
                        return new DamageSpell(FIELD, name, SHARED, BASIC, 5, (spellPower * 10) + 10);
                    case ADVANCED:
                        return new DamageSpell(FIELD, name, SHARED, ADVANCED, 5, (spellPower * 10) + 20);
                    case EXPERT:
                        return new DamageSpell(FIELD, name, SHARED, EXPERT, 5, (spellPower * 19) + 30);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case HASTE:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, AIR, BASIC, 6, CreatureStats.builder().moveRange(10).build(), 2, SLOW);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, AIR, ADVANCED, 6, CreatureStats.builder().moveRange(20).build(), 4, SLOW);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, AIR, EXPERT, 6, CreatureStats.builder().moveRange(30).build(), 2, SLOW);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case LIGHTNING_BOLT:
                switch (rang) {
                    case BASIC:
                        return new DamageSpell(FIELD, name, AIR, BASIC, 10, (spellPower * 25) + 10);
                    case ADVANCED:
                        return new DamageSpell(FIELD, name, AIR, ADVANCED, 10, (spellPower * 25) + 20);
                    case EXPERT:
                        return new DamageSpell(FOR_ALL_ALLIED_CREATURES, name, AIR, EXPERT, 10, (spellPower * 25) + 50);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case CHAIN_LIGHTNING:
                switch (rang) {
                    case BASIC:
                        return new ChainLightning(AREA, name, AIR, BASIC, 24, (spellPower * 40) + 25);
                    case ADVANCED:
                        return new ChainLightning(AREA, name, AIR, ADVANCED, 24, (spellPower * 40) + 50);
                    case EXPERT:
                        return new ChainLightning(AREA, name, AIR, EXPERT, 24, (spellPower * 40) + 100);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case COUNTERSTRIKE:
                switch (rang) {
                    case BASIC:
                        return new Counterstrike(FIELD, name, AIR, BASIC, 24, 1, 2);
                    case ADVANCED:
                        return new Counterstrike(FIELD, name, AIR, ADVANCED, 24, 2, 2);
                    case EXPERT:
                        return new Counterstrike(FOR_ALL_ALLIED_CREATURES, name, AIR, EXPERT, 24, 2, 4);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case FIRE_BALL:
                switch (rang) {
                    case BASIC:
                        return new AreaDamageSpell(AREA, name, FIRE, BASIC, 25,
                                new boolean[][]{{false, false, true, false, false},
                                                {false, true, true, true, false},
                                                {true, true, true, true, true},
                                                {false, true, true, true, false},
                                                {false, false, true, false, false}}, spellPower + 10);
                    case ADVANCED:
                        return new AreaDamageSpell(AREA, name, FIRE, ADVANCED, 30,
                                new boolean[][]{{false, false, true, false, false},
                                                {false, true, true, true, false},
                                                {true, true, true, true, true},
                                                {false, true, true, true, false},
                                                {false, false, true, false, false}}, spellPower + 10);
                    case EXPERT:
                        return new AreaDamageSpell(AREA, name, FIRE, EXPERT, 45,
                                new boolean[][]{{false, false, true, false, false},
                                        {false, true, true, true, false},
                                        {true, true, true, true, true},
                                        {false, true, true, true, false},
                                        {false, false, true, false, false}}, spellPower + 10);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case SLOW:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, EARTH, BASIC, 10, CreatureStats.builder().moveRange(-10).build(), 2, HASTE); // ToDo: change to procentage damage
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, EARTH, ADVANCED,  15, CreatureStats.builder().moveRange(-20).build(), 4, HASTE);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ENEMY_CREATURES, name, EARTH, EXPERT, 20, CreatureStats.builder().moveRange(-30).build(), 2, HASTE);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case DEATH_RIPPLE:
                switch (rang) {
                    case BASIC:
                        return new DamageSpell(FOR_ALL_CREATURES, name, EARTH, BASIC, 10, (spellPower * 5) + 10);
                    case ADVANCED:
                        return new DamageSpell(FOR_ALL_CREATURES, name, EARTH, ADVANCED, 15, (spellPower * 5) + 20);
                    case EXPERT:
                        return new DamageSpell(FOR_ALL_CREATURES, name, EARTH, EXPERT, 20, (spellPower * 5) + 30);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case DISPEL:
                switch (rang) {
                    case BASIC:
                        return new Dispel(FIELD, name, WATER, BASIC, 10);
                    case ADVANCED:
                        return new Dispel(FIELD, name, WATER, ADVANCED, 15);
                    case EXPERT:
                        return new Dispel(FOR_ALL_CREATURES, name, WATER, EXPERT, 20); // Add removing all non static special fields
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            default:
                throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }
}
