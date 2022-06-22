package pl.psi.spells;

import pl.psi.creatures.CreatureStats;

import java.beans.PropertyChangeListener;
import java.util.function.BiConsumer;

public class AreaBuffDebuffSpell extends Spell<SpellCreatureList> {

    private final CreatureStats creatureStats;

    public AreaBuffDebuffSpell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, SpellAlignment spellAlignment, int manaCost, CreatureStats creatureStats) {
        super(category, name, spellMagicClass, rang, spellAlignment, manaCost);
        this.creatureStats = creatureStats;
    }

    @Override
    public void castSpell(SpellCreatureList spellCreatureList, BiConsumer<String, PropertyChangeListener> consumer) {
        spellCreatureList.getCreatureList().forEach(creature -> creature.buff(creatureStats));
    }

    @Override
    public void unCastSpell(SpellCreatureList spellCreatureList) {

    }
}
