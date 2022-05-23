package pl.psi.spells;

import pl.psi.creatures.CreatureStats;

import static pl.psi.spells.SpellRang.*;
import static pl.psi.spells.SpellTypes.*;

public class SpellFactory {

    private static final String EXCEPTION_MESSAGE = "We support rang from 1 to 7";

    public Spell<?> create(String name, SpellRang rang, int manaCost, int spellPower) {

        switch (name) {
            case "LightingBolt":
                switch (rang) {
                    case BASIC:
                        return new DamageSpell(FIELD, name, BASIC, manaCost, (spellPower * 25) + 10);
                    case ADVANCED:
                        return new DamageSpell(FIELD, name, ADVANCED, manaCost, (spellPower * 25) + 20);
                    case EXPERT:
                        return new DamageSpell(FIELD, name, EXPERT, manaCost, (spellPower * 25) + 50);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case "Haste":
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, BASIC, manaCost, CreatureStats.builder().moveRange(10).build(), 2);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, ADVANCED, manaCost, CreatureStats.builder().moveRange(20).build(), 4);
                    case EXPERT:
                        return new BuffDebuffSpell(FIELD, name, EXPERT, manaCost, CreatureStats.builder().moveRange(30).build(), 2);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case "FireBall":
                switch (rang) {
                    case BASIC:
                        return new AreaDamageSpell(AREA, name, BASIC, manaCost, spellPower + 10);
                    case ADVANCED:
                        return new AreaDamageSpell(AREA, name, ADVANCED, manaCost, (spellPower * 2) + 20);
                    case EXPERT:
                        return new AreaDamageSpell(AREA, name, EXPERT, manaCost, (spellPower * 3) + 30);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            default:
                throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }
}
