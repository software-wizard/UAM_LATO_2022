package pl.psi.spells;

import pl.psi.creatures.CreatureStatisticIf;
import pl.psi.creatures.CreatureStats;

import java.util.List;

import static pl.psi.spells.SpellsCategories.*;

public class SpellFactory {

    private static final String EXCEPTION_MESSAGE = "We support rang from 1 to 7";

    public Spell<?> create(String name, int rang, int manaCost) {

        switch (name) {
            case "LightingBolt":
                switch (rang) {
                    case 1:
                        return new DamageSpell(DAMAGE, name, rang, manaCost, 25, 10);
                    case 2:
                        return new DamageSpell(DAMAGE, name, rang, manaCost, 25, 20);
                    case 3:
                        return new DamageSpell(DAMAGE, name, rang, manaCost, 25, 50);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case "Haste":
                switch (rang) {
                    case 1:
                        return new BuffDebuffSpell(BUFF_DEBUFF, name, rang, manaCost, 1, CreatureStats.builder().moveRange(10).build());
                    case 2:
                        return new BuffDebuffSpell(BUFF_DEBUFF, name, rang, manaCost, 1, CreatureStats.builder().moveRange(20).build());
                    case 3:
                        return new BuffDebuffSpell(BUFF_DEBUFF, name, rang, manaCost, 1, CreatureStats.builder().moveRange(30).build());
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case "FireBall":
                switch (rang) {
                    case 1:
                        return new DamageSpell(AREA, name, rang, manaCost, 1, 10);
                    case 2:
                        return new DamageSpell(AREA, name, rang, manaCost, 2, 20);
                    case 3:
                        return new DamageSpell(AREA, name, rang, manaCost, 3, 30);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            default:
                throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }
}
