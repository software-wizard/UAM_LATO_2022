package pl.psi.spells;

import pl.psi.creatures.Creature;

public interface SpellDamageCalculatorIf {

    int calculate(Creature creature, int multiplier, int spellPower, int value);
}
