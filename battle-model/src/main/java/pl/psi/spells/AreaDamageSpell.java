package pl.psi.spells;

import lombok.Getter;

import java.beans.PropertyChangeListener;
import java.util.function.BiConsumer;

public class AreaDamageSpell extends Spell<SpellCreatureList> {

    @Getter
    private final boolean[][] area;
    private final double value;

    public AreaDamageSpell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, int manaCost, boolean[][] area, double value) {
        super(category, name, spellMagicClass, rang, manaCost);
        this.area = area;
        this.value = value;
    }

    @Override
    public void castSpell(SpellCreatureList creatureList, BiConsumer<String, PropertyChangeListener> consumer) {
        creatureList.getCreatureList().forEach(creature -> {
            SpellFactorCalculator spellFactorCalculator = new SpellFactorCalculator();
            creature.applySpellDamage(creature, value * spellFactorCalculator.isCreatureHasProtection(this, creature));
        });
    }

    @Override
    public void unCastSpell(SpellCreatureList creatureList) {

    }
}
