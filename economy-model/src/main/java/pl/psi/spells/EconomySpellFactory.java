package pl.psi.spells;

public class EconomySpellFactory {

    public EconomySpell create(SpellStats spellStats, SpellRang spellRang, int requiredMagicGuildLevel, int goldCost) {

        switch (requiredMagicGuildLevel) {
            case 1:
                switch (spellStats) {
                    case HASTE:
                        switch (spellRang) {
                            case BASIC:
                                return new EconomySpell(spellStats, spellRang, requiredMagicGuildLevel, 100);
                            case ADVANCED:
                                return new EconomySpell(spellStats, spellRang, requiredMagicGuildLevel, 200);
                            case EXPERT:
                                return new EconomySpell(spellStats, spellRang, requiredMagicGuildLevel, 300);
                            default:
                                return null; //ToDo: Exception!
                        }
                    case MAGIC_ARROW:
                        switch (spellRang) {
                            case BASIC:
                                return new EconomySpell(spellStats, spellRang, requiredMagicGuildLevel, 200);
                            case ADVANCED:
                                return new EconomySpell(spellStats, spellRang, requiredMagicGuildLevel, 300);
                            case EXPERT:
                                return new EconomySpell(spellStats, spellRang, requiredMagicGuildLevel, 400);
                            default:
                                return null; //ToDo: Exception!
                        }
                    default:
                        return null; //ToDo: Exception!
                }
            case 2:
                break;
            case 3:
                switch (spellStats) {
                    case FIREBALL:
                        switch (spellRang) {
                            case BASIC:
                                return new EconomySpell(spellStats, spellRang, requiredMagicGuildLevel, 400);
                            case ADVANCED:
                                return new EconomySpell(spellStats, spellRang, requiredMagicGuildLevel, 500);
                            case EXPERT:
                                return new EconomySpell(spellStats, spellRang, requiredMagicGuildLevel, 600);
                            default:
                                return null; //ToDo: Exception!
                        }
                    default:
                        return null; //ToDo: Exception!
                }
            default:
                return null; //ToDo: Exception!
        }
        return null;
    }
}
