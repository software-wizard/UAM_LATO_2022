package pl.psi.spells;

public enum SpellNames {
    HASTE("Haste"),
    MAGIC_ARROW("Magic Arrow"),
    FORTUNE("Fortune"),
    LIGHTNING_BOLT("Lightning Bolt"),
    PROTECTION_FROM_AIR("Protection From Air"),
    CHAIN_LIGHTNING("Chain Lightning"),
    COUNTERSTRIKE("Counterstrike"),
    PROTECTION_FROM_FIRE("Protection From Fire"),
    BLOODLUST("Bloodlust"),
    FIRE_BALL("Fire Ball"),
    MISFORTUNE("Misfortune"),
    ARMAGEDDON("Armageddon"),
    FRENZY("Frenzy"),
    INFERNO("Inferno"),
    SLOW("Slow"),
    DEATH_RIPPLE("Death Ripple"),
    PROTECTION_FROM_EARTH("Protection from Earth"),
    SORROW("Sorrow"),
    STONESKIN("Stoneskin"),
    METEOR_SHOWER("Meteor Shower"),
    IMPLOSION("Implosion"),
    ICE_BOLT("Ice Bolt"),
    WEAKNESS("Weakness"),
    FROST_RING("Frost Ring"),
    PRAYER("Prayer"),
    DISPEL("Dispel"),
    PROTECTION_FROM_WATER("Protection From Water"),
    MIRTH("Mirth");


    private final String name;

    SpellNames(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
