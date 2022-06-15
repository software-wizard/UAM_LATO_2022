package pl.psi.spells;

public enum SpellNames {
    HASTE("Haste"),
    MAGIC_ARROW("Magic Arrow"),
    FORTUNE("Fortune"),
    LIGHTNING_BOLT("Lightning Bolt"),
    CHAIN_LIGHTNING("Chain Lightning"),
    COUNTERSTRIKE("Counterstrike"),
    BLOODLUST("Bloodlust"),
    FIRE_BALL("Fire Ball"),
    MISFORTUNE("Misfortune"),
    ARMAGEDDON("Armageddon"),
    FRENZY("Frenzy"),
    INFERNO("Inferno"),
    SLOW("Slow"),
    DEATH_RIPPLE("Death Ripple"),
    SORROW("Sorrow"),
    STONESKIN("Stoneskin"),
    METEOR_SHOWER("Meteor Shower"),
    IMPLOSION("Implosion"),
    ICE_BOLT("Ice Bolt"),
    WEAKNESS("Weakness"),
    FROST_RING("Frost Ring"),
    PRAYER("Prayer"),
    DISPEL("Dispel"),
    MIRTH("Mirth");


    private final String name;

    SpellNames(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
