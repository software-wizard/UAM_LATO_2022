package pl.psi.spells;

import pl.psi.creatures.Creature;

public class CalculateSpellDamage implements SpellDamageCalculatorIf{

    @Override
    public int calculate(Creature creature,int multiplier, int spellPower, int value) {
        return ((spellPower * multiplier) + value);// ToDo: take spell resistant from creature and make abstract calculator
    }
}
