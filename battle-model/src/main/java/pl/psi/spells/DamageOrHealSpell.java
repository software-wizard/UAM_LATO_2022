package pl.psi.spells;

import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;
import java.util.function.BiConsumer;

public class DamageOrHealSpell extends Spell<Creature> {

    private final double value;

    public DamageOrHealSpell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, SpellAlignment spellAlignment, int manaCost, double value) {
        super(category, name, spellMagicClass, rang, spellAlignment, manaCost);
        this.value = value;
    }

    @Override
    public void castSpell(Creature aDefender, BiConsumer<String, PropertyChangeListener> consumer) {
        if (getSpellAlignment().equals(SpellAlignment.POSITIVE)){
            aDefender.heal(value);
        }else {
            SpellFactorCalculator spellFactorCalculator = new SpellFactorCalculator();
            double damage = value * spellFactorCalculator.getCreatureProtectionFactor(this, aDefender);
            aDefender.applySpellDamage(aDefender, damage);
        }
    }


    @Override
    public void unCastSpell(Creature aDefender) {

    }

}
