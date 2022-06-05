package pl.psi.spells;

import pl.psi.creatures.Creature;

public class DamageSpell extends Spell<Creature> {

    private final Integer value;

    public DamageSpell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, int manaCost, Integer value) {
        super(category, name, spellMagicClass, rang, manaCost);
        this.value = value;
    }

    @Override
    public void castSpell(Creature aDefender) {
        aDefender.applySpellDamage(aDefender, value);
    }

}
