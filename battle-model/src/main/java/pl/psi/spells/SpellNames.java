package pl.psi.spells;

public enum SpellNames {
    HASTE("Haste"),
    MAGIC_ARROW("Magic Arrow"),
    DISRUPTING_RAY("Disrupting_ray"),
    FORTUNE("Fortune"),
    LIGHTNING_BOLT("Lightning Bolt"),
    PROTECTION_FROM_AIR("Protection From Air"),
    CHAIN_LIGHTNING("Chain Lightning"),
    COUNTERSTRIKE("Counterstrike"),
    SUMMON_AIR_ELEMENTAL("Summon Air Elemental"),
    PROTECTION_FROM_FIRE("Protection From Fire"),
    BLOODLUST("Bloodlust"),
    FIRE_BALL("Fire Ball"),
    MISFORTUNE("Misfortune"),
    ARMAGEDDON("Armageddon"),
    FRENZY("Frenzy"),
    INFERNO("Inferno"),
    SUMMON_FIRE_ELEMENTAL("Summon Fire Elemental"),
    SLOW("Slow"),
    DEATH_RIPPLE("Death Ripple"),
    PROTECTION_FROM_EARTH("Protection from Earth"),
    SORROW("Sorrow"),
    STONESKIN("Stoneskin"),
    METEOR_SHOWER("Meteor Shower"),
    IMPLOSION("Implosion"),
    SUMMON_EARTH_ELEMENTAL("Summon Earth Elemental"),
    ICE_BOLT("Ice Bolt"),
    WEAKNESS("Weakness"),
    FROST_RING("Frost Ring"),
    PRAYER("Prayer"),
    DISPEL("Dispel"),
    PROTECTION_FROM_WATER("Protection From Water"),
    MIRTH("Mirth"),
    SUMMON_WATER_ELEMENTAL("Summon Water Elemental");


    private final String name;

    SpellNames(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
