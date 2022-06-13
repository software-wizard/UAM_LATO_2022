package pl.psi.spells;

public enum SpellNames {
    MAGIC_ARROW("Magic Arrow"),
    HASTE("Haste"),
    LIGHTNING_BOLT("Lightning Bolt"),
    CHAIN_LIGHTNING("Chain Lightning"),
    COUNTERSTRIKE("Counterstrike"),
    SLOW("Slow"),
    DEATH_RIPPLE("Death Ripple"),
    FIRE_BALL("Fire Ball"),
    DISPEL("Dispel");


    private final String name;

    SpellNames(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
