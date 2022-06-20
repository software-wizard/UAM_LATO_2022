package pl.psi.spells;

import lombok.Getter;

import static pl.psi.spells.SpellMagicGuild.*;

@Getter
public enum SpellStats implements SpellStatisticIf {
    HASTE(SpellNames.HASTE, AIR, "Increases the speed of the selected unit", 6),
    MAGIC_ARROW(SpellNames.MAGIC_ARROW, AIR, "Causes a bolt of magical energy to strike the selected unit.", 5),
    DISRUPTING_RAY(SpellNames.DISRUPTING_RAY, AIR, "Reduces the selected enemy unit's defense strength. A single enemy may be targeted multiple times by this spell.", 10),
    FORTUNE(SpellNames.FORTUNE, AIR, "Increases the selected unit's luck.", 7),
    LIGHTNING_BOLT(SpellNames.LIGHTNING_BOLT, AIR, "Causes a bolt of lightning to strike the selected unit.", 10),
    PROTECTION_FROM_AIR(SpellNames.PROTECTION_FROM_AIR, AIR, "Protects the selected unit, reducing damage received from Air spells.", 5),
    CHAIN_LIGHTNING(SpellNames.LIGHTNING_BOLT, AIR, "Fires a bolt of lightning at the selected unit which then travels to the nearest adjacent unit, inflicting half damage.", 24),
    COUNTERSTRIKE(SpellNames.COUNTERSTRIKE, AIR, "The selected unit will retaliate against one additional attack each round.", 24),
    MAGIC_MIRROR(SpellNames.MAGIC_MIRROR, AIR, "Reflects hostile spells towards a random enemy creature 20% of the time.", 25),
    SUMMON_AIR_ELEMENTAL(SpellNames.SUMMON_AIR_ELEMENTAL, AIR, "Allows you to summon elementals. Once cast, no other elemental types may be summoned.",25),
    CURSE(SpellNames.CURSE, FIRE, "Causes the selected enemy unit to inflict minimum damage in combat.", 6),
    PROTECTION_FROM_FIRE(SpellNames.PROTECTION_FROM_FIRE,FIRE, "Protects the selected unit, reducing damage received from Fire spells.", 5),
    FIRE_BALL(SpellNames.FIRE_BALL, FIRE, "Causes the selected target to burst into flames, inflicting fire damage to the target and any adjacent units.", 15),
    MISFORTUNE(SpellNames.MISFORTUNE, FIRE, "Reduces the luck of the selected enemy unit", 12),
    ARMAGEDDON(SpellNames.ARMAGEDDON, FIRE, "Rains fire down upon the battlefield, damaging all units.", 24),
    INFERNO(SpellNames.INFERNO, FIRE, "Causes a huge blast of fire to strike the selected target. Don't be near this when it goes off!", 16),
    SUMMON_FIRE_ELEMENTAL(SpellNames.SUMMON_FIRE_ELEMENTAL, FIRE, "Allows you to summon elementals. Once cast, no other elemental types may be summoned.", 25),
    SLOW(SpellNames.SLOW, EARTH, "Reduces the speed of the selected enemy unit.", 6),
    STONESKIN(SpellNames.STONESKIN, EARTH, "Increases the selected unit's defense strength.", 5),
    DEATH_RIPPLE(SpellNames.DEATH_RIPPLE, EARTH, "Sends a wave of death across the battlefield which damages all non-undead units.", 10),
    PROTECTION_FROM_EARTH(SpellNames.PROTECTION_FROM_EARTH, EARTH, "Protects the selected unit, reducing damage received from Earth spells.", 5),
    ANIMATE_DEAD(SpellNames.ANIMATE_DEAD, EARTH, "Re-animates any destroyed undead in the selected group by restoring a fixed number of hit points to the target.", 15),
    METEOR_SHOWER(SpellNames.METEOR_SHOWER, EARTH, "Causes a meteor shower to rain down on the selected target and any adjacent units.", 16),
    RESURRECTION(SpellNames.RESURRECTION, EARTH, "Resurrects units in the selected group by restoring a fixed number of hit points to the target", 20),
    SORROW(SpellNames.SORROW, EARTH, "Reduces the morale of the selected enemy unit.", 16),
    IMPLOSION(SpellNames.IMPLOSION, EARTH, "Inflicts massive damage to a single creature stack", 30),
    SUMMON_EARTH_ELEMENTAL(SpellNames.SUMMON_EARTH_ELEMENTAL, EARTH, "Allows you to summon elementals. Once cast, no other elemental types may be summoned.", 25),
    BLESS(SpellNames.BLESS, WATER, "Causes the selected unit to inflict maximum damage in combat.", 5),
    CURE(SpellNames.CURE, WATER, "Removes all negative spell effects and heals a small number of hit points on the selected unit.", 6),
    DISPEL(SpellNames.DISPEL, WATER, "Protects the selected unit from all low level spells.", 5),
    PROTECTION_FROM_WATER(SpellNames.PROTECTION_FROM_WATER, WATER, "Protects the selected unit, reducing damage received from Water spells.", 5),
    ICE_BOLT(SpellNames.ICE_BOLT, WATER, "Drains the body heat from the selected enemy unit", 8),
    WEAKNESS(SpellNames.WEAKNESS, WATER, "Reduces the selected enemy unit's attack strength.", 8),
    FORGETFULNESS(SpellNames.FORGETFULNESS, WATER, "Causes the selected enemy unit to forget to use its ranged attack in combat.", 12),
    FROST_RING(SpellNames.FROST_RING, WATER, "Drains the body heat of any units adjacent to the target location, without inflicting damage on the target.", 12),
    MIRTH(SpellNames.MIRTH, WATER, "Increases the selected unit's morale.", 12),
    PRAYER(SpellNames.PRAYER, WATER, "Bestows a bonus to the attack strength, defense strength and speed of the selected unit.", 16),
    SUMMON_WATER_ELEMENTAL(SpellNames.SUMMON_WATER_ELEMENTAL, WATER, "Allows you to summon elementals. Once cast, no other elemental types may be summoned.\n", 25);

    private final SpellNames name;
    private final SpellMagicGuild magicGuild;
    private final String description;
    private final int manaCost;

    SpellStats(SpellNames name, SpellMagicGuild magicGuild, String description, int manaCost) {
        this.name = name;
        this.magicGuild = magicGuild;
        this.description = description;
        this.manaCost = manaCost;
    }
}
