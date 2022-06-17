package pl.psi.spells;

public class EconomySpellFactory {

    private static final String SPELL_NOT_FOUND = "We don't have this spell!";
    private static final String RANG_EXCEPTION = "We don't have this spell!";


    public EconomySpell create(SpellStats spellStats, SpellRang spellRang) {
        switch (spellStats) {
            case MAGIC_ARROW:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 100);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 200);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 300);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case FORTUNE:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 100);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 200);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 300);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case HASTE:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 101);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 201);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 301);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case LIGHTNING_BOLT:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 102);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 202);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 302);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case CHAIN_LIGHTNING:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 103);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 203);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 303);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case COUNTERSTRIKE:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 104);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 204);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 304);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case FIRE_BALL:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 105);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 205);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 305);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case MISFORTUNE:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 106);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 206);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 306);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case ARMAGEDDON:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 107);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 207);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 307);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case INFERNO:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 108);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 208);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 308);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case SLOW:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 109);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 209);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 309);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case STONESKIN:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 110);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 210);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 310);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case DEATH_RIPPLE:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 111);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 211);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 311);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case METEOR_SHOWER:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 112);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 212);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 312);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case SORROW:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 113);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 213);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 313);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case IMPLOSION:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 114);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 214);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 314);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case DISPEL:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 115);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 215);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 315);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case ICE_BOLT:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 116);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 216);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 316);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case WEAKNESS:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 117);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 217);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 317);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case FROST_RING:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 118);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 218);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 318);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case MIRTH:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 119);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 219);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 319);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            case PRAYER:
                switch (spellRang) {
                    case BASIC:
                        return new EconomySpell(spellStats, spellRang, 120);
                    case ADVANCED:
                        return new EconomySpell(spellStats, spellRang, 220);
                    case EXPERT:
                        return new EconomySpell(spellStats, spellRang, 320);
                    default:
                        throw new IllegalArgumentException(RANG_EXCEPTION);
                }
            default:
                throw new IllegalArgumentException(SPELL_NOT_FOUND);
        }
    }
}
