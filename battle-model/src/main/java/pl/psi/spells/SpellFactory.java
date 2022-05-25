package pl.psi.spells;

import pl.psi.creatures.CreatureStats;

import static pl.psi.spells.SpellRang.*;
import static pl.psi.spells.SpellTypes.*;

public class SpellFactory {

    private static final String EXCEPTION_MESSAGE = "We support rang from 1 to 7";

    public Spell<?> create(String name, SpellRang rang, int spellPower) {

        switch (name) {
            case "LightingBolt":
                switch (rang) {
                    case BASIC:
                        return new DamageSpell(FIELD, name, BASIC, 10, (spellPower * 25) + 10);
                    case ADVANCED:
                        return new DamageSpell(FIELD, name, ADVANCED, 15, (spellPower * 25) + 20);
                    case EXPERT:
                        return new DamageSpell(FIELD, name, EXPERT, 20, (spellPower * 25) + 50);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case "Haste":
                switch (rang) {
                    case BASIC:
                        return new BuffDebuffSpell(FIELD, name, BASIC, 10, CreatureStats.builder().moveRange(10).build(), 2);
                    case ADVANCED:
                        return new BuffDebuffSpell(FIELD, name, ADVANCED, 15, CreatureStats.builder().moveRange(20).build(), 4);
                    case EXPERT:
                        return new BuffDebuffSpell(FIELD, name, EXPERT, 20, CreatureStats.builder().moveRange(30).build(), 2);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case "FireBall":
                switch (rang) {
                    case BASIC:
                        return new AreaDamageSpell(AREA, name, BASIC, 25,
                                new boolean[][]{{false, false, true, false, false},
                                                {false, true, true, true, false},
                                                {true, true, true, true, true},
                                                {false, true, true, true, false},
                                                {false, false, true, false, false}}, spellPower + 10);
                    case ADVANCED:
                        return new AreaDamageSpell(AREA, name, ADVANCED, 30,
                                new boolean[][]{{false, false, true, false, false},
                                                {false, true, true, true, false},
                                                {true, true, true, true, true},
                                                {false, true, true, true, false},
                                                {false, false, true, false, false}}, spellPower + 10);
                    case EXPERT:
                        return new AreaDamageSpell(AREA, name, EXPERT, 45,
                                new boolean[][]{{false, false, true, false, false},
                                                {false, true, true, true, false},
                                                {true, true, true, true, true},
                                                {false, true, true, true, false},
                                                {false, false, true, false, false}}, spellPower + 10);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            default:
                throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }
}
