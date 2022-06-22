package pl.psi.spells;

public class EconomySpellFactory {

    private static final String SPELL_NOT_FOUND = "We don't have this spell!";
    private static final String RANG_EXCEPTION = "We don't have this spell with this rang!";


    public EconomySpell create(SpellStats spellStats, SpellRang spellRang) {
        int basicCost = spellStats.getManaCost() * 10;
        switch (spellRang) {
            case BASIC:
                return new EconomySpell(spellStats, spellRang, basicCost);
            case ADVANCED:
                return new EconomySpell(spellStats, spellRang, basicCost + 50);
            case EXPERT:
                return new EconomySpell(spellStats, spellRang, basicCost + 100);
            default:
                throw new IllegalArgumentException(RANG_EXCEPTION);
        }
    }
}
