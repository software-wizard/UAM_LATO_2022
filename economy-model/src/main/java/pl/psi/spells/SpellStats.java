package pl.psi.spells;

import lombok.Getter;

import static pl.psi.spells.SpellMagicGuild.*;

@Getter
public enum SpellStats implements SpellStatisticIf {
    MAGIC_ARROW("Magic Arrow", AIR, "Causes a bolt of magical energy to strike the selected unit.", 5),
    FORTUNE("Fortune", AIR, "Increases the selected unit's luck.", 7),
    HASTE("Haste", AIR, "Increases the speed of the selected unit", 6),
    LIGHTNING_BOLT("Lightning Bolt", AIR, "Causes a bolt of lightning to strike the selected unit.", 10),
    CHAIN_LIGHTNING("Chain Lightning", AIR, "Fires a bolt of lightning at the selected unit which then travels to the nearest adjacent unit, inflicting half damage.", 24),
    COUNTERSTRIKE("Counterstrike", AIR, "The selected unit will retaliate against one additional attack each round.", 24),
    SLOW("Slow", EARTH, "Reduces the speed of the selected enemy unit.", 6),
    DEATH_RIPPLE("Death Ripple", EARTH, "Sends a wave of death across the battlefield which damages all non-undead units.", 10),
    SORROW("Sorrow", EARTH, "Reduces the morale of the selected enemy unit.", 16),
    STONESKIN("Stoneskin", EARTH, "Increases the selected unit's defense strength.", 5),
    METEOR_SHOWER("Meteor Shower", EARTH, "Causes a meteor shower to rain down on the selected target and any adjacent units.", 16),
    IMPLOSION("Implosion", EARTH, "Inflicts massive damage to a single creature stack", 30),
    FIRE_BALL("Fire Ball", FIRE, "Causes the selected target to burst into flames, inflicting fire damage to the target and any adjacent units.", 15),
    MISFORTUNE("Misfortune", FIRE, "Reduces the luck of the selected enemy unit", 12),
    ARMAGEDDON("Armageddon", FIRE, "Rains fire down upon the battlefield, damaging all units.", 24),
    INFERNO("Inferno", FIRE, "Causes a huge blast of fire to strike the selected target. Don't be near this when it goes off!", 16),
    ICE_BOLT("Ice Bolt", WATER, "Drains the body heat from the selected enemy unit", 8),
    WEAKNESS("Weakness", WATER, "Reduces the selected enemy unit's attack strength.", 8),
    FROST_RING("Frost Ring", WATER, "Drains the body heat of any units adjacent to the target location, without inflicting damage on the target.", 12),
    PRAYER("Prayer", WATER, "Bestows a bonus to the attack strength, defense strength and speed of the selected unit.", 16),
    DISPEL("Dispel", WATER, "Protects the selected unit from all low level spells.", 5),
    MIRTH("Mirth", WATER, "Increases the selected unit's morale.", 12);

    private final String name;
    private final SpellMagicGuild magicGuild;
    private final String description;
    private final int manaCost;

    SpellStats(String name, SpellMagicGuild magicGuild, String description, int manaCost) {
        this.name = name;
        this.magicGuild = magicGuild;
        this.description = description;
        this.manaCost = manaCost;
    }
}
