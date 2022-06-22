package pl.psi.spells;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;

import java.beans.PropertyChangeListener;
import java.util.function.BiConsumer;

public class SpawningCreatureSpell extends Spell<Creature> {

    private final CreatureStatistic creatureStatistic;

    public SpawningCreatureSpell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, SpellAlignment spellAlignment, int manaCost, CreatureStatistic creatureStatistic) {
        super(category, name, spellMagicClass, rang, spellAlignment, manaCost);
        this.creatureStatistic = creatureStatistic;
    }

    @Override
    public void castSpell(Creature aDefender, BiConsumer<String, PropertyChangeListener> consumer) {
        consumer.accept(creatureStatistic.getName(), null);
    }

    @Override
    public void unCastSpell(Creature aDefender) {

    }
}
