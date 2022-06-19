package pl.psi.spells;

import pl.psi.creatures.CreatureStatistic;
import pl.psi.creatures.CreatureStats;

import static pl.psi.spells.SpellMagicClass.*;
import static pl.psi.spells.SpellNames.*;
import static pl.psi.spells.SpellRang.*;
import static pl.psi.spells.SpellTypes.*;

public class SpellFactory {

    private static final String RANG_EXCEPTION_MESSAGE = "We only support BASIC, ADVANCE, EXPERT rang!";
    private static final String NAME_EXCEPTION_MESSAGE = "Name not found!";
    private final SpellFactorsModifiers spellFactorsModifiers;

    public SpellFactory() {
        this.spellFactorsModifiers = SpellFactorsModifiers.builder()
                .airDamageModifier(1)
                .fireDamageModifier(1)
                .earthDamageModifier(1)
                .waterDamageModifier(1)
                .addedDuration(0)
                .build();
    }

    public Spell<?> create(SpellNames name, SpellRang rang, int spellPower) {

        switch (name) {
            case MAGIC_ARROW:
                switch (rang) {
                    case BASIC:
                        return new DamageSpell(FIELD, name, SHARED, BASIC, 5, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 10) + 10));
                    case ADVANCED:
                        return new DamageSpell(FIELD, name, SHARED, ADVANCED, 5, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 10) + 20));
                    case EXPERT:
                        return new DamageSpell(FIELD, name, SHARED, EXPERT, 5, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 10) + 30));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case DISRUPTING_RAY:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, AIR, BASIC, 10, CreatureStats.builder().armor(-3).build(), spellPower, STONESKIN);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, AIR, ADVANCED, 10, CreatureStats.builder().armor(-4).build(), spellPower, STONESKIN);
                    case EXPERT:
                        return new BuffDebuffSpell(FIELD, name, AIR, EXPERT, 10, CreatureStats.builder().armor(-4).build(), spellPower, STONESKIN);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case FORTUNE:
                switch (rang) {
                    case BASIC:
                        return new LuckBuffDebuffSpell(FIELD, name, AIR, BASIC, 7, 1, spellPower, MISFORTUNE);
                    case ADVANCED:
                        return new LuckBuffDebuffSpell(FIELD, name, AIR, ADVANCED, 7, 2, spellPower, MISFORTUNE);
                    case EXPERT:
                        return new LuckBuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, AIR, EXPERT, 7, 2, spellPower, MISFORTUNE);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case HASTE:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, AIR, BASIC, 6, CreatureStats.builder().moveRange(10).build(), spellPower, SLOW);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, AIR, ADVANCED, 6, CreatureStats.builder().moveRange(20).build(), spellPower, SLOW);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, AIR, EXPERT, 6, CreatureStats.builder().moveRange(30).build(), spellPower, SLOW);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case LIGHTNING_BOLT:
                switch (rang) {
                    case BASIC:
                        return new DamageSpell(FIELD, name, AIR, BASIC, 10, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 25) + 10));
                    case ADVANCED:
                        return new DamageSpell(FIELD, name, AIR, ADVANCED, 10, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 25) + 20));
                    case EXPERT:
                        return new DamageSpell(FOR_ALL_ALLIED_CREATURES, name, AIR, EXPERT, 10, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 25) + 50));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case PROTECTION_FROM_AIR:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, AIR, BASIC, 6, CreatureStats.builder().build(), spellPower, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, AIR, ADVANCED, 6, CreatureStats.builder().build(), spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, AIR, EXPERT, 6, CreatureStats.builder().build(), spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case CHAIN_LIGHTNING:
                switch (rang) {
                    case BASIC:
                        return new ChainLightning(AREA, name, AIR, BASIC, 24, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 40) + 25));
                    case ADVANCED:
                        return new ChainLightning(AREA, name, AIR, ADVANCED, 24, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 40) + 50));
                    case EXPERT:
                        return new ChainLightning(AREA, name, AIR, EXPERT, 24, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 40) + 100));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case COUNTERSTRIKE:
                switch (rang) {
                    case BASIC:
                        return new Counterstrike(FIELD, name, AIR, BASIC, 24, 1, spellPower);
                    case ADVANCED:
                        return new Counterstrike(FIELD, name, AIR, ADVANCED, 24, 2, spellPower);
                    case EXPERT:
                        return new Counterstrike(FOR_ALL_ALLIED_CREATURES, name, AIR, EXPERT, 24, 2, spellPower);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case SUMMON_AIR_ELEMENTAL:
                switch (rang) {
                    case BASIC:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, AIR, BASIC, 24, CreatureStatistic.AIR_ELEMENTAL);
                    case ADVANCED:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, AIR, ADVANCED, 24, CreatureStatistic.AIR_ELEMENTAL);
                    case EXPERT:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, AIR, EXPERT, 24, CreatureStatistic.AIR_ELEMENTAL);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case BLOODLUST:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, FIRE, BASIC, 5, CreatureStats.builder().attack(3).build(), spellPower, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, FIRE, ADVANCED, 5, CreatureStats.builder().attack(6).build(), spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, FIRE, EXPERT, 5, CreatureStats.builder().attack(6).build(), spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case PROTECTION_FROM_FIRE:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, FIRE, BASIC, 6, CreatureStats.builder().build(), spellPower, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, FIRE, ADVANCED, 6, CreatureStats.builder().build(), spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, FIRE, EXPERT, 6, CreatureStats.builder().build(), spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case FIRE_BALL:
                switch (rang) {
                    case BASIC:
                        return new AreaDamageSpell(AREA, name, FIRE, BASIC, 25,
                                new boolean[][]{{false, false, true, false, false},
                                        {false, true, true, true, false},
                                        {true, true, true, true, true},
                                        {false, true, true, true, false},
                                        {false, false, true, false, false}}, spellFactorsModifiers.getFireDamageModifier() * (spellPower + 10));
                    case ADVANCED:
                        return new AreaDamageSpell(AREA, name, FIRE, ADVANCED, 30,
                                new boolean[][]{{false, false, true, false, false},
                                        {false, true, true, true, false},
                                        {true, true, true, true, true},
                                        {false, true, true, true, false},
                                        {false, false, true, false, false}}, spellFactorsModifiers.getFireDamageModifier() * (spellPower + 10));
                    case EXPERT:
                        return new AreaDamageSpell(AREA, name, FIRE, EXPERT, 45,
                                new boolean[][]{{false, false, true, false, false},
                                        {false, true, true, true, false},
                                        {true, true, true, true, true},
                                        {false, true, true, true, false},
                                        {false, false, true, false, false}}, spellFactorsModifiers.getFireDamageModifier() * (spellPower + 10));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case MISFORTUNE:
                switch (rang) {
                    case BASIC:
                        return new LuckBuffDebuffSpell(FIELD, name, FIRE, BASIC, 12, -1, spellPower, FORTUNE);
                    case ADVANCED:
                        return new LuckBuffDebuffSpell(FIELD, name, FIRE, ADVANCED, 12, -2, spellPower, FORTUNE);
                    case EXPERT:
                        return new LuckBuffDebuffSpell(FOR_ALL_ENEMY_CREATURES, name, FIRE, EXPERT, 12, -2, spellPower, FORTUNE);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case ARMAGEDDON:
                switch (rang) {
                    case BASIC:
                        return new DamageSpell(FOR_ALL_CREATURES, name, FIRE, BASIC, 24, (spellPower * 50) + 30);
                    case ADVANCED:
                        return new DamageSpell(FOR_ALL_CREATURES, name, FIRE, ADVANCED, 24, (spellPower * 50) + 60);
                    case EXPERT:
                        return new DamageSpell(FOR_ALL_CREATURES, name, FIRE, EXPERT, 24, (spellPower * 50) + 120);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case INFERNO:
                switch (rang) {
                    case BASIC:
                        return new AreaDamageSpell(AREA, name, FIRE, BASIC, 16,
                                new boolean[][]{{false, false, true, false, false},
                                        {false, true, true, true, false},
                                        {true, true, true, true, true},
                                        {false, true, true, true, false},
                                        {false, false, true, false, false}}, spellFactorsModifiers.getFireDamageModifier() * ((spellPower * 10) + 20));
                    case ADVANCED:
                        return new AreaDamageSpell(AREA, name, FIRE, ADVANCED, 16,
                                new boolean[][]{{false, false, true, false, false},
                                        {false, true, true, true, false},
                                        {true, true, true, true, true},
                                        {false, true, true, true, false},
                                        {false, false, true, false, false}}, spellFactorsModifiers.getFireDamageModifier() * ((spellPower * 10) + 40));
                    case EXPERT:
                        return new AreaDamageSpell(AREA, name, FIRE, EXPERT, 16,
                                new boolean[][]{{false, false, true, false, false},
                                        {false, true, true, true, false},
                                        {true, true, true, true, true},
                                        {false, true, true, true, false},
                                        {false, false, true, false, false}}, spellFactorsModifiers.getFireDamageModifier() * ((spellPower * 10) + 80));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case SUMMON_FIRE_ELEMENTAL:
                switch (rang) {
                    case BASIC:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, FIRE, BASIC, 25, CreatureStatistic.FIRE_ELEMENTAL);
                    case ADVANCED:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, FIRE, ADVANCED, 25, CreatureStatistic.FIRE_ELEMENTAL);
                    case EXPERT:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, FIRE, EXPERT, 25, CreatureStatistic.FIRE_ELEMENTAL);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case SLOW:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, EARTH, BASIC, 6, CreatureStats.builder().moveRange(-10).build(), spellPower, HASTE);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, EARTH, ADVANCED, 6, CreatureStats.builder().moveRange(-20).build(), spellPower, HASTE);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ENEMY_CREATURES, name, EARTH, EXPERT, 6, CreatureStats.builder().moveRange(-30).build(), spellPower, HASTE);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case STONESKIN:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, EARTH, BASIC, 5, CreatureStats.builder().armor(3).build(), spellPower, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, EARTH, ADVANCED, 5, CreatureStats.builder().armor(6).build(), spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, EARTH, EXPERT, 5, CreatureStats.builder().armor(6).build(), spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case DEATH_RIPPLE:
                switch (rang) {
                    case BASIC:
                        return new DamageSpell(FOR_ALL_CREATURES, name, EARTH, BASIC, 10, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 5) + 10));
                    case ADVANCED:
                        return new DamageSpell(FOR_ALL_CREATURES, name, EARTH, ADVANCED, 15, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 5) + 20));
                    case EXPERT:
                        return new DamageSpell(FOR_ALL_CREATURES, name, EARTH, EXPERT, 20, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 5) + 30));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case PROTECTION_FROM_EARTH:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, EARTH, BASIC, 6, CreatureStats.builder().build(), spellPower, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, EARTH, ADVANCED, 6, CreatureStats.builder().build(), spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, EARTH, EXPERT, 6, CreatureStats.builder().build(), spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case METEOR_SHOWER:
                switch (rang) {
                    case BASIC:
                        return new AreaDamageSpell(AREA, name, EARTH, BASIC, 16,
                                new boolean[][]{{false, true, false},
                                        {true, true, true},
                                        {false, true, false}}, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 25) + 25));
                    case ADVANCED:
                        return new AreaDamageSpell(AREA, name, EARTH, ADVANCED, 16,
                                new boolean[][]{{false, true, false},
                                        {true, true, true},
                                        {false, true, false}}, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 25) + 50));
                    case EXPERT:
                        return new AreaDamageSpell(AREA, name, EARTH, EXPERT, 16,
                                new boolean[][]{{true, true, true},
                                        {true, true, true},
                                        {true, true, true}}, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 25) + 100));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case SORROW:
                switch (rang) {
                    case BASIC:
                        return new MoralBuffDebuffSpell(FIELD, name, EARTH, BASIC, 16, -1, spellPower, MIRTH);
                    case ADVANCED:
                        return new MoralBuffDebuffSpell(FIELD, name, EARTH, ADVANCED, 16, -2, spellPower, MIRTH);
                    case EXPERT:
                        return new MoralBuffDebuffSpell(FOR_ALL_ENEMY_CREATURES, name, EARTH, EXPERT, 16, -2, spellPower, MIRTH);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case IMPLOSION:
                switch (rang) {
                    case BASIC:
                        return new DamageSpell(FIELD, name, EARTH, BASIC, 30, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 75) + 100));
                    case ADVANCED:
                        return new DamageSpell(FIELD, name, EARTH, ADVANCED, 30, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 75) + 200));
                    case EXPERT:
                        return new DamageSpell(FIELD, name, EARTH, EXPERT, 30, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 75) + 300));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case SUMMON_EARTH_ELEMENTAL:
                switch (rang) {
                    case BASIC:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, EARTH, BASIC, 25, CreatureStatistic.EARTH_ELEMENTAL);
                    case ADVANCED:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, EARTH, ADVANCED, 25, CreatureStatistic.EARTH_ELEMENTAL);
                    case EXPERT:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, EARTH, EXPERT, 25, CreatureStatistic.EARTH_ELEMENTAL);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case DISPEL:
                switch (rang) {
                    case BASIC:
                        return new Dispel(FIELD, name, WATER, BASIC, 10);
                    case ADVANCED:
                        return new Dispel(FIELD, name, WATER, ADVANCED, 15);
                    case EXPERT:
                        return new Dispel(FOR_ALL_CREATURES, name, WATER, EXPERT, 20);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case PROTECTION_FROM_WATER:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, WATER, BASIC, 6, CreatureStats.builder().build(), spellPower, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, WATER, ADVANCED, 6, CreatureStats.builder().build(), spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, WATER, EXPERT, 6, CreatureStats.builder().build(), spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case ICE_BOLT:
                switch (rang) {
                    case BASIC:
                        return new DamageSpell(FIELD, name, WATER, BASIC, 8, spellFactorsModifiers.getWaterDamageModifier() * ((spellPower * 20) + 10));
                    case ADVANCED:
                        return new DamageSpell(FIELD, name, WATER, ADVANCED, 8, spellFactorsModifiers.getWaterDamageModifier() * ((spellPower * 20) + 20));
                    case EXPERT:
                        return new DamageSpell(FIELD, name, WATER, EXPERT, 8, spellFactorsModifiers.getWaterDamageModifier() * ((spellPower * 20) + 50));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case WEAKNESS:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, WATER, BASIC, 8, CreatureStats.builder().attack(-3).build(), spellPower, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, WATER, ADVANCED, 8, CreatureStats.builder().attack(-6).build(), spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ENEMY_CREATURES, name, WATER, EXPERT, 8, CreatureStats.builder().attack(-6).build(), spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case FROST_RING:
                switch (rang) {
                    case BASIC:
                        return new AreaDamageSpell(AREA, name, WATER, BASIC, 12,
                                new boolean[][]{{true, true, true},
                                        {true, false, true},
                                        {true, true, true}}, spellFactorsModifiers.getWaterDamageModifier() * ((spellPower * 10) + 15));
                    case ADVANCED:
                        return new AreaDamageSpell(AREA, name, WATER, ADVANCED, 12,
                                new boolean[][]{{true, true, true},
                                        {true, false, true},
                                        {true, true, true}}, spellFactorsModifiers.getWaterDamageModifier() * ((spellPower * 10) + 30));
                    case EXPERT:
                        return new AreaDamageSpell(AREA, name, WATER, EXPERT, 12,
                                new boolean[][]{{true, true, true},
                                        {true, false, true},
                                        {true, true, true}}, spellFactorsModifiers.getWaterDamageModifier() * ((spellPower * 10) + 60));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case MIRTH:
                switch (rang) {
                    case BASIC:
                        return new MoralBuffDebuffSpell(FIELD, name, WATER, BASIC, 12, 1, spellPower, SORROW);
                    case ADVANCED:
                        return new MoralBuffDebuffSpell(FIELD, name, WATER, ADVANCED, 12, 2, spellPower, SORROW);
                    case EXPERT:
                        return new MoralBuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, WATER, EXPERT, 12, 2, spellPower, SORROW); // Add removing all non static special fields
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case PRAYER:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, WATER, BASIC, 16, CreatureStats.builder().attack(4).armor(4).moveRange(4).build(), 2, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, WATER, ADVANCED, 16, CreatureStats.builder().attack(4).armor(4).moveRange(4).build(), 4, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, WATER, EXPERT, 16, CreatureStats.builder().attack(4).armor(4).moveRange(4).build(), 2, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case SUMMON_WATER_ELEMENTAL:
                switch (rang) {
                    case BASIC:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, WATER, BASIC, 25, CreatureStatistic.WATER_ELEMENTAL);
                    case ADVANCED:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, WATER, ADVANCED, 25, CreatureStatistic.WATER_ELEMENTAL);
                    case EXPERT:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, WATER, EXPERT, 25, CreatureStatistic.WATER_ELEMENTAL);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            default:
                throw new IllegalArgumentException(NAME_EXCEPTION_MESSAGE);
        }
    }
}
