package pl.psi.spells;

import pl.psi.creatures.*;

import java.util.List;

import static pl.psi.spells.SpellAlignment.NEGATIVE;
import static pl.psi.spells.SpellAlignment.POSITIVE;
import static pl.psi.spells.SpellMagicClass.*;
import static pl.psi.spells.SpellNames.*;
import static pl.psi.spells.SpellRang.*;
import static pl.psi.spells.SpellTypes.*;

public class SpellFactory {

    private static final String RANG_EXCEPTION_MESSAGE = "We only support BASIC, ADVANCE, EXPERT rang!";
    private static final String NAME_EXCEPTION_MESSAGE = "Name not found!";
    private final SpellFactorsModifiers spellFactorsModifiers;


    public SpellFactory(SpellFactorsModifiers spellFactorsModifiers) {
        this.spellFactorsModifiers = spellFactorsModifiers;
    }

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
                        return new DamageOrHealSpell(FIELD, name, SHARED, BASIC, NEGATIVE, 5, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 10) + 10));
                    case ADVANCED:
                        return new DamageOrHealSpell(FIELD, name, SHARED, ADVANCED, NEGATIVE, 5, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 10) + 20));
                    case EXPERT:
                        return new DamageOrHealSpell(FIELD, name, SHARED, EXPERT, NEGATIVE, 5, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 10) + 30));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case DISRUPTING_RAY:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, AIR, BASIC, NEGATIVE, 10, CreatureStats.builder().armor(-3).build(), spellFactorsModifiers.getAddedDuration() + spellPower, STONESKIN);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, AIR, ADVANCED, NEGATIVE, 10, CreatureStats.builder().armor(-4).build(), spellFactorsModifiers.getAddedDuration() + spellPower, STONESKIN);
                    case EXPERT:
                        return new BuffDebuffSpell(FIELD, name, AIR, EXPERT, NEGATIVE, 10, CreatureStats.builder().armor(-4).build(), spellFactorsModifiers.getAddedDuration() + spellPower, STONESKIN);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case FORTUNE:
                switch (rang) {
                    case BASIC:
                        return new LuckBuffDebuffSpell(FIELD, name, AIR, BASIC, POSITIVE, 7, 1, spellFactorsModifiers.getAddedDuration() + spellPower, MISFORTUNE);
                    case ADVANCED:
                        return new LuckBuffDebuffSpell(FIELD, name, AIR, ADVANCED, POSITIVE, 7, 2, spellFactorsModifiers.getAddedDuration() + spellPower, MISFORTUNE);
                    case EXPERT:
                        return new LuckBuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, AIR, EXPERT, POSITIVE, 7, 2, spellFactorsModifiers.getAddedDuration() + spellPower, MISFORTUNE);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case HASTE:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, AIR, BASIC, POSITIVE, 6, CreatureStats.builder().moveRange(10).build(), spellFactorsModifiers.getAddedDuration() + spellPower, SLOW);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, AIR, ADVANCED, POSITIVE, 6, CreatureStats.builder().moveRange(20).build(), spellFactorsModifiers.getAddedDuration() + spellPower, SLOW);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, AIR, EXPERT, POSITIVE, 6, CreatureStats.builder().moveRange(30).build(), spellFactorsModifiers.getAddedDuration() + spellPower, SLOW);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case LIGHTNING_BOLT:
                switch (rang) {
                    case BASIC:
                        return new DamageOrHealSpell(FIELD, name, AIR, BASIC, NEGATIVE, 10, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 25) + 10));
                    case ADVANCED:
                        return new DamageOrHealSpell(FIELD, name, AIR, ADVANCED, NEGATIVE, 10, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 25) + 20));
                    case EXPERT:
                        return new DamageOrHealSpell(FOR_ALL_ALLIED_CREATURES, name, AIR, EXPERT, NEGATIVE, 10, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 25) + 50));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case PROTECTION_FROM_AIR:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, AIR, BASIC, POSITIVE, 6, CreatureStats.builder().build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, AIR, ADVANCED, POSITIVE, 6, CreatureStats.builder().build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, AIR, EXPERT, POSITIVE, 6, CreatureStats.builder().build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case CHAIN_LIGHTNING:
                switch (rang) {
                    case BASIC:
                        return new ChainLightning(AREA, name, AIR, BASIC, NEGATIVE, 24, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 40) + 25));
                    case ADVANCED:
                        return new ChainLightning(AREA, name, AIR, ADVANCED, NEGATIVE, 24, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 40) + 50));
                    case EXPERT:
                        return new ChainLightning(AREA, name, AIR, EXPERT, NEGATIVE, 24, spellFactorsModifiers.getAirDamageModifier() * ((spellPower * 40) + 100));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case COUNTERSTRIKE:
                switch (rang) {
                    case BASIC:
                        return new Counterstrike(FIELD, name, AIR, BASIC, POSITIVE, 24, 1, spellFactorsModifiers.getAddedDuration() + spellPower);
                    case ADVANCED:
                        return new Counterstrike(FIELD, name, AIR, ADVANCED, POSITIVE, 24, 2, spellFactorsModifiers.getAddedDuration() + spellPower);
                    case EXPERT:
                        return new Counterstrike(FOR_ALL_ALLIED_CREATURES, name, AIR, EXPERT, POSITIVE, 24, 2, spellFactorsModifiers.getAddedDuration() + spellPower);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case MAGIC_MIRROR:
                switch (rang) {
                    case BASIC:
                        return new MagicMirror(FIELD, name, AIR, BASIC, POSITIVE, 25, 2, spellFactorsModifiers.getAddedDuration() + spellPower);
                    case ADVANCED:
                        return new MagicMirror(FIELD, name, AIR, ADVANCED, POSITIVE, 25, 3, spellFactorsModifiers.getAddedDuration() + spellPower);
                    case EXPERT:
                        return new MagicMirror(FOR_ALL_ALLIED_CREATURES, name, AIR, EXPERT, POSITIVE, 25, 4, spellFactorsModifiers.getAddedDuration() + spellPower);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case SUMMON_AIR_ELEMENTAL:
                switch (rang) {
                    case BASIC:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, AIR, BASIC, POSITIVE, 24, CreatureStatistic.AIR_ELEMENTAL);
                    case ADVANCED:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, AIR, ADVANCED, POSITIVE, 24, CreatureStatistic.AIR_ELEMENTAL);
                    case EXPERT:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, AIR, EXPERT, POSITIVE, 24, CreatureStatistic.AIR_ELEMENTAL);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case BLOODLUST:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, FIRE, BASIC, NEGATIVE, 5, CreatureStats.builder().attack(3).build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, FIRE, ADVANCED, NEGATIVE, 5, CreatureStats.builder().attack(6).build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, FIRE, EXPERT, NEGATIVE, 5, CreatureStats.builder().attack(6).build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case CURSE:
                switch (rang) {
                    case BASIC:
                        return new ChangeCalculatorBuff(FIELD, name, FIRE, BASIC, NEGATIVE, 6, spellPower, new MinimalDamageCalculator());
                    case ADVANCED:
                        return new ChangeCalculatorBuff(FIELD, name, FIRE, ADVANCED, NEGATIVE, 6, spellPower, new CurseDamageCalculator());
                    case EXPERT:
                        return new ChangeCalculatorBuff(FOR_ALL_ENEMY_CREATURES, name, FIRE, EXPERT, NEGATIVE, 6, spellPower, new CurseDamageCalculator());
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case PROTECTION_FROM_FIRE:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, FIRE, BASIC, POSITIVE, 6, CreatureStats.builder().build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, FIRE, ADVANCED, POSITIVE, 6, CreatureStats.builder().build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, FIRE, EXPERT, POSITIVE, 6, CreatureStats.builder().build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case FIRE_BALL:
                switch (rang) {
                    case BASIC:
                        return new AreaDamageSpell(AREA, name, FIRE, BASIC, NEGATIVE, 25,
                                new boolean[][]{{false, false, true, false, false},
                                        {false, true, true, true, false},
                                        {true, true, true, true, true},
                                        {false, true, true, true, false},
                                        {false, false, true, false, false}}, spellFactorsModifiers.getFireDamageModifier() * (spellPower + 10));
                    case ADVANCED:
                        return new AreaDamageSpell(AREA, name, FIRE, ADVANCED, NEGATIVE, 30,
                                new boolean[][]{{false, false, true, false, false},
                                        {false, true, true, true, false},
                                        {true, true, true, true, true},
                                        {false, true, true, true, false},
                                        {false, false, true, false, false}}, spellFactorsModifiers.getFireDamageModifier() * (spellPower + 10));
                    case EXPERT:
                        return new AreaDamageSpell(AREA, name, FIRE, EXPERT, NEGATIVE, 45,
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
                        return new LuckBuffDebuffSpell(FIELD, name, FIRE, BASIC, NEGATIVE, 12, -1, spellFactorsModifiers.getAddedDuration() + spellPower, FORTUNE);
                    case ADVANCED:
                        return new LuckBuffDebuffSpell(FIELD, name, FIRE, ADVANCED, NEGATIVE, 12, -2, spellFactorsModifiers.getAddedDuration() + spellPower, FORTUNE);
                    case EXPERT:
                        return new LuckBuffDebuffSpell(FOR_ALL_ENEMY_CREATURES, name, FIRE, EXPERT, NEGATIVE, 12, -2, spellFactorsModifiers.getAddedDuration() + spellPower, FORTUNE);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case ARMAGEDDON:
                switch (rang) {
                    case BASIC:
                        return new DamageOrHealSpell(FOR_ALL_CREATURES, name, FIRE, BASIC, NEGATIVE, 24, (spellPower * 50) + 30);
                    case ADVANCED:
                        return new DamageOrHealSpell(FOR_ALL_CREATURES, name, FIRE, ADVANCED, NEGATIVE, 24, (spellPower * 50) + 60);
                    case EXPERT:
                        return new DamageOrHealSpell(FOR_ALL_CREATURES, name, FIRE, EXPERT, NEGATIVE, 24, (spellPower * 50) + 120);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case INFERNO:
                switch (rang) {
                    case BASIC:
                        return new AreaDamageSpell(AREA, name, FIRE, BASIC, NEGATIVE, 16,
                                new boolean[][]{{false, false, true, false, false},
                                        {false, true, true, true, false},
                                        {true, true, true, true, true},
                                        {false, true, true, true, false},
                                        {false, false, true, false, false}}, spellFactorsModifiers.getFireDamageModifier() * ((spellPower * 10) + 20));
                    case ADVANCED:
                        return new AreaDamageSpell(AREA, name, FIRE, ADVANCED, NEGATIVE, 16,
                                new boolean[][]{{false, false, true, false, false},
                                        {false, true, true, true, false},
                                        {true, true, true, true, true},
                                        {false, true, true, true, false},
                                        {false, false, true, false, false}}, spellFactorsModifiers.getFireDamageModifier() * ((spellPower * 10) + 40));
                    case EXPERT:
                        return new AreaDamageSpell(AREA, name, FIRE, EXPERT, NEGATIVE, 16,
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
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, FIRE, BASIC, POSITIVE, 25, CreatureStatistic.FIRE_ELEMENTAL);
                    case ADVANCED:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, FIRE, ADVANCED, POSITIVE, 25, CreatureStatistic.FIRE_ELEMENTAL);
                    case EXPERT:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, FIRE, EXPERT, POSITIVE, 25, CreatureStatistic.FIRE_ELEMENTAL);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case SLOW:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, EARTH, BASIC, NEGATIVE, 6, CreatureStats.builder().moveRange(-10).build(), spellFactorsModifiers.getAddedDuration() + spellPower, HASTE);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, EARTH, ADVANCED, NEGATIVE, 6, CreatureStats.builder().moveRange(-20).build(), spellFactorsModifiers.getAddedDuration() + spellPower, HASTE);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ENEMY_CREATURES, name, EARTH, EXPERT, NEGATIVE, 6, CreatureStats.builder().moveRange(-30).build(), spellFactorsModifiers.getAddedDuration() + spellPower, HASTE);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case STONESKIN:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, EARTH, BASIC, POSITIVE, 5, CreatureStats.builder().armor(3).build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, EARTH, ADVANCED, POSITIVE, 5, CreatureStats.builder().armor(6).build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, EARTH, EXPERT, POSITIVE, 5, CreatureStats.builder().armor(6).build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case DEATH_RIPPLE:
                switch (rang) {
                    case BASIC:
                        return new DamageOrHealSpell(FOR_ALL_CREATURES, name, EARTH, BASIC, NEGATIVE, 10, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 5) + 10));
                    case ADVANCED:
                        return new DamageOrHealSpell(FOR_ALL_CREATURES, name, EARTH, ADVANCED, NEGATIVE, 10, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 5) + 20));
                    case EXPERT:
                        return new DamageOrHealSpell(FOR_ALL_CREATURES, name, EARTH, EXPERT, NEGATIVE, 10, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 5) + 30));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case PROTECTION_FROM_EARTH:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, EARTH, BASIC, POSITIVE, 6, CreatureStats.builder().build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, EARTH, ADVANCED, POSITIVE, 6, CreatureStats.builder().build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, EARTH, EXPERT, POSITIVE, 6, CreatureStats.builder().build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case ANIMATE_DEAD:
                switch (rang) {
                    case BASIC:
                        return new DamageOrHealSpell(FIELD, name, EARTH, BASIC, POSITIVE, 15, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 50) + 30));
                    case ADVANCED:
                        return new DamageOrHealSpell(FIELD, name, EARTH, ADVANCED, POSITIVE, 15, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 50) + 60));
                    case EXPERT:
                        return new DamageOrHealSpell(FIELD, name, EARTH, EXPERT, POSITIVE, 15, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 50) + 160));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case METEOR_SHOWER:
                switch (rang) {
                    case BASIC:
                        return new AreaDamageSpell(AREA, name, EARTH, BASIC, NEGATIVE, 16,
                                new boolean[][]{{false, true, false},
                                        {true, true, true},
                                        {false, true, false}}, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 25) + 25));
                    case ADVANCED:
                        return new AreaDamageSpell(AREA, name, EARTH, ADVANCED, NEGATIVE, 16,
                                new boolean[][]{{false, true, false},
                                        {true, true, true},
                                        {false, true, false}}, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 25) + 50));
                    case EXPERT:
                        return new AreaDamageSpell(AREA, name, EARTH, EXPERT, NEGATIVE, 16,
                                new boolean[][]{{true, true, true},
                                        {true, true, true},
                                        {true, true, true}}, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 25) + 100));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case IMPLOSION:
                switch (rang) {
                    case BASIC:
                        return new DamageOrHealSpell(FIELD, name, EARTH, BASIC, NEGATIVE, 30, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 75) + 100));
                    case ADVANCED:
                        return new DamageOrHealSpell(FIELD, name, EARTH, ADVANCED, NEGATIVE, 30, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 75) + 200));
                    case EXPERT:
                        return new DamageOrHealSpell(FIELD, name, EARTH, EXPERT, NEGATIVE, 30, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 75) + 300));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case SORROW:
                switch (rang) {
                    case BASIC:
                        return new MoralBuffDebuffSpell(FIELD, name, EARTH, BASIC, NEGATIVE, 16, -1, spellFactorsModifiers.getAddedDuration() + spellPower, MIRTH);
                    case ADVANCED:
                        return new MoralBuffDebuffSpell(FIELD, name, EARTH, ADVANCED, NEGATIVE, 16, -2, spellFactorsModifiers.getAddedDuration() + spellPower, MIRTH);
                    case EXPERT:
                        return new MoralBuffDebuffSpell(FOR_ALL_ENEMY_CREATURES, name, EARTH, EXPERT, NEGATIVE, 16, -2, spellFactorsModifiers.getAddedDuration() + spellPower, MIRTH);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case RESURRECTION:
                switch (rang) {
                    case BASIC:
                        return new DamageOrHealSpell(FIELD, name, EARTH, BASIC, POSITIVE, 20, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 50) + 40));
                    case ADVANCED:
                        return new DamageOrHealSpell(FIELD, name, EARTH, ADVANCED, POSITIVE, 20, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 50) + 80));
                    case EXPERT:
                        return new DamageOrHealSpell(FIELD, name, EARTH, EXPERT, POSITIVE, 20, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 50) + 160));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case SUMMON_EARTH_ELEMENTAL:
                switch (rang) {
                    case BASIC:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, EARTH, BASIC, POSITIVE, 25, CreatureStatistic.EARTH_ELEMENTAL);
                    case ADVANCED:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, EARTH, ADVANCED, POSITIVE, 25, CreatureStatistic.EARTH_ELEMENTAL);
                    case EXPERT:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, EARTH, EXPERT, POSITIVE, 25, CreatureStatistic.EARTH_ELEMENTAL);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case BLESS:
                switch (rang) {
                    case BASIC:
                        return new ChangeCalculatorBuff(FIELD, name, WATER, BASIC, POSITIVE, 5, spellPower, new MaximalDamageCalculator());
                    case ADVANCED:
                        return new ChangeCalculatorBuff(FIELD, name, WATER, ADVANCED, POSITIVE, 5, spellPower, new MaximalPlusOneDamageCalculator());
                    case EXPERT:
                        return new ChangeCalculatorBuff(FOR_ALL_ALLIED_CREATURES, name, WATER, EXPERT, POSITIVE, 5, spellPower, new MaximalPlusOneDamageCalculator());
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case CURE:
                switch (rang) {
                    case BASIC:
                        return new DamageOrHealSpell(FIELD, name, WATER, BASIC, POSITIVE, 6, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 5) + 10));
                    case ADVANCED:
                        return new DamageOrHealSpell(FIELD, name, WATER, ADVANCED, POSITIVE, 6, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 5) + 20));
                    case EXPERT:
                        return new DamageOrHealSpell(FIELD, name, WATER, EXPERT, POSITIVE, 6, spellFactorsModifiers.getEarthDamageModifier() * ((spellPower * 5) + 30));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case DISPEL:
                switch (rang) {
                    case BASIC:
                        return new Dispel(FIELD, name, WATER, BASIC, POSITIVE, 10, List.of(POSITIVE, NEGATIVE));
                    case ADVANCED:
                        return new Dispel(FIELD, name, WATER, ADVANCED, POSITIVE, 15, List.of(POSITIVE, NEGATIVE));
                    case EXPERT:
                        return new Dispel(FOR_ALL_CREATURES, name, WATER, EXPERT, POSITIVE, 20, List.of(POSITIVE, NEGATIVE));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case PROTECTION_FROM_WATER:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, WATER, BASIC, POSITIVE, 6, CreatureStats.builder().build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, WATER, ADVANCED, POSITIVE, 6, CreatureStats.builder().build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, WATER, EXPERT, POSITIVE, 6, CreatureStats.builder().build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case ICE_BOLT:
                switch (rang) {
                    case BASIC:
                        return new DamageOrHealSpell(FIELD, name, WATER, BASIC, NEGATIVE, 8, spellFactorsModifiers.getWaterDamageModifier() * ((spellPower * 20) + 10));
                    case ADVANCED:
                        return new DamageOrHealSpell(FIELD, name, WATER, ADVANCED, NEGATIVE, 8, spellFactorsModifiers.getWaterDamageModifier() * ((spellPower * 20) + 20));
                    case EXPERT:
                        return new DamageOrHealSpell(FIELD, name, WATER, EXPERT, NEGATIVE, 8, spellFactorsModifiers.getWaterDamageModifier() * ((spellPower * 20) + 50));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case WEAKNESS:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, WATER, BASIC, NEGATIVE, 8, CreatureStats.builder().attack(-3).build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, WATER, ADVANCED, NEGATIVE, 8, CreatureStats.builder().attack(-6).build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ENEMY_CREATURES, name, WATER, EXPERT, NEGATIVE, 8, CreatureStats.builder().attack(-6).build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case FORGETFULNESS:
                switch (rang) {
                    case BASIC:
                        return new ChangeCalculatorBuff(FIELD, name, WATER, BASIC, NEGATIVE, 12, spellFactorsModifiers.getAddedDuration() + spellPower, new ReducedDamageCalculator(0.5));
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, WATER, ADVANCED, POSITIVE, 12, CreatureStats.builder().build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ENEMY_CREATURES, name, WATER, EXPERT, POSITIVE, 12, CreatureStats.builder().build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case FROST_RING:
                switch (rang) {
                    case BASIC:
                        return new AreaDamageSpell(AREA, name, WATER, BASIC, NEGATIVE, 12,
                                new boolean[][]{{true, true, true},
                                        {true, false, true},
                                        {true, true, true}}, spellFactorsModifiers.getWaterDamageModifier() * ((spellPower * 10) + 15));
                    case ADVANCED:
                        return new AreaDamageSpell(AREA, name, WATER, ADVANCED, NEGATIVE, 12,
                                new boolean[][]{{true, true, true},
                                        {true, false, true},
                                        {true, true, true}}, spellFactorsModifiers.getWaterDamageModifier() * ((spellPower * 10) + 30));
                    case EXPERT:
                        return new AreaDamageSpell(AREA, name, WATER, EXPERT, NEGATIVE, 12,
                                new boolean[][]{{true, true, true},
                                        {true, false, true},
                                        {true, true, true}}, spellFactorsModifiers.getWaterDamageModifier() * ((spellPower * 10) + 60));
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case MIRTH:
                switch (rang) {
                    case BASIC:
                        return new MoralBuffDebuffSpell(FIELD, name, WATER, BASIC, POSITIVE, 12, 1, spellFactorsModifiers.getAddedDuration() + spellPower, SORROW);
                    case ADVANCED:
                        return new MoralBuffDebuffSpell(FIELD, name, WATER, ADVANCED, POSITIVE, 12, 2, spellFactorsModifiers.getAddedDuration() + spellPower, SORROW);
                    case EXPERT:
                        return new MoralBuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, WATER, EXPERT, POSITIVE, 12, 2, spellFactorsModifiers.getAddedDuration() + spellPower, SORROW); // Add removing all non static special fields
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case PRAYER:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, WATER, BASIC, POSITIVE, 16, CreatureStats.builder().attack(4).armor(4).moveRange(4).build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, WATER, ADVANCED, POSITIVE, 16, CreatureStats.builder().attack(4).armor(4).moveRange(4).build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FOR_ALL_ALLIED_CREATURES, name, WATER, EXPERT, POSITIVE, 16, CreatureStats.builder().attack(4).armor(4).moveRange(4).build(), spellFactorsModifiers.getAddedDuration() + spellPower, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case SUMMON_WATER_ELEMENTAL:
                switch (rang) {
                    case BASIC:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, WATER, BASIC, POSITIVE, 25, CreatureStatistic.WATER_ELEMENTAL);
                    case ADVANCED:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, WATER, ADVANCED, POSITIVE, 25, CreatureStatistic.WATER_ELEMENTAL);
                    case EXPERT:
                        return new SpawningCreatureSpell(SPAWN_CREATURE, name, WATER, EXPERT, POSITIVE, 25, CreatureStatistic.WATER_ELEMENTAL);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            case DISEASE:
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, SHARED, BASIC, NEGATIVE, 0, CreatureStats.builder().attack(-2).armor(-2).build(), 3, null);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, SHARED, ADVANCED, NEGATIVE, 0, CreatureStats.builder().attack(-2).armor(-2).build(), 3, null);
                    case EXPERT:
                        return new BuffDebuffSpell(FIELD, name, SHARED, EXPERT, NEGATIVE, 0, CreatureStats.builder().attack(-2).armor(4).build(), 3, null);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION_MESSAGE);
                }
            default:
                throw new IllegalArgumentException(NAME_EXCEPTION_MESSAGE);
        }
    }
}
