package pl.psi.spells;

import pl.psi.creatures.Creature;

public class Dispel extends Spell<Creature> {

    public Dispel(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, int manaCost) {
        super(category, name, spellMagicClass, rang, manaCost);
    }

    @Override
    public void castSpell(Creature aDefender) {
        aDefender.getRunningSpells().forEach(spell -> {
            spell.unCastSpell(aDefender); // check if running spell list clear
        });
    }

    @Override
    public void unCastSpell(Creature aDefender) {

    }

}