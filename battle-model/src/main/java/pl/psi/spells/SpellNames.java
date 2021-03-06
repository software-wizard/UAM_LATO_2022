package pl.psi.spells;

public enum SpellNames {
    HASTE("Haste"),
    MAGIC_ARROW("Magic Arrow"),
    DISRUPTING_RAY("Disrupting Ray"),
    FORTUNE("Fortune"),
    LIGHTNING_BOLT("Lightning Bolt"),
    PROTECTION_FROM_AIR("Protection From Air"),
    CHAIN_LIGHTNING("Chain Lightning"),
    COUNTERSTRIKE("Counterstrike"),
    MAGIC_MIRROR("Magic Mirror"),
    SUMMON_AIR_ELEMENTAL("Summon Air Elemental"),
    CURSE("Curse"),
    PROTECTION_FROM_FIRE("Protection From Fire"),
    BLOODLUST("Bloodlust"),
    FIRE_BALL("Fire Ball"),
    MISFORTUNE("Misfortune"),
    ARMAGEDDON("Armageddon"),
    INFERNO("Inferno"),
    SUMMON_FIRE_ELEMENTAL("Summon Fire Elemental"),
    SLOW("Slow"),
    STONESKIN("Stoneskin"),
    DEATH_RIPPLE("Death Ripple"),
    PROTECTION_FROM_EARTH("Protection from Earth"),
    BLESS("Bless"),
    ANIMATE_DEAD("Animate Dead"),
    METEOR_SHOWER("Meteor Shower"),
    RESURRECTION("Resurrection"),
    SORROW("Sorrow"),
    IMPLOSION("Implosion"),
    SUMMON_EARTH_ELEMENTAL("Summon Earth Elemental"),
    ICE_BOLT("Ice Bolt"),
    WEAKNESS("Weakness"),
    FORGETFULNESS("Forgetfulness"),
    FROST_RING("Frost Ring"),
    PRAYER("Prayer"),
    CURE("Cure"),
    DISPEL("Dispel"),
    PROTECTION_FROM_WATER("Protection From Water"),
    MIRTH("Mirth"),
    SUMMON_WATER_ELEMENTAL("Summon Water Elemental"),
    DISEASE("Disease");


    private final String name;

    SpellNames(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
