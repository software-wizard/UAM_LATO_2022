package pl.psi.spells;

public enum SpellNames {
    MAGIC_ARROW("Magic Arrow"),
    FORTUNE("Fortune"),
    HASTE("Haste"),
    LIGHTNING_BOLT("Lightning Bolt"),
    CHAIN_LIGHTNING("Chain Lightning"),
    COUNTERSTRIKE("Counterstrike"),
    SLOW("Slow"),
    DEATH_RIPPLE("Death Ripple"),
    SORROW("Sorrow"),
    FIRE_BALL("Fire Ball"),
    MISFORTUNE("Misfortune"),
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
