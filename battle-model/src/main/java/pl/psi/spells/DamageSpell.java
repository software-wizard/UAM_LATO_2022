package pl.psi.spells;

import pl.psi.creatures.Creature;

public class DamageSpell extends Spell<Creature> {

    private final Integer value;

    public DamageSpell(SpellTypes category, String name, SpellRang rang, int manaCost, Integer value) {
        super(category, name, rang, manaCost);
        this.value = value;
    }

    @Override
    public void castSpell(Creature aDefender) {
        aDefender.applySpellDamage(aDefender, value);
    }

    @Override
    public void unCastSpell(Creature aDefender) {

    }

}
