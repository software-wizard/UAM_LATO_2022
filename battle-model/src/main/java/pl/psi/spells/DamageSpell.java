package pl.psi.spells;

import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;
import java.util.function.BiConsumer;

public class DamageSpell extends Spell<Creature> {

    private final double value;

    public DamageSpell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, int manaCost, double value) {
        super(category, name, spellMagicClass, rang, manaCost);
        this.value = value;
    }

    @Override
    public void castSpell(Creature aDefender, BiConsumer<String, PropertyChangeListener> consumer) {
        SpellFactorCalculator spellFactorCalculator = new SpellFactorCalculator();
        double damage = value * spellFactorCalculator.isCreatureHasProtection(this, aDefender);
        aDefender.applySpellDamage(aDefender, damage);
    }


    @Override
    public void unCastSpell(Creature aDefender) {

    }

}
