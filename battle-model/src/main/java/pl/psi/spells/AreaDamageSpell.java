package pl.psi.spells;

import lombok.Getter;
import pl.psi.creatures.Creature;

import java.util.List;

public class AreaDamageSpell extends Spell<List<Creature>> {

    @Getter
    private final boolean[][] area;
    private final int value;

    public AreaDamageSpell(SpellTypes category, String name, SpellRang rang, int manaCost, boolean[][] area, int value) {
        super(category, name, rang, manaCost);
        this.area = area;
        this.value = value;
    }

    @Override
    public void castSpell(List<Creature> aDefender) {
        aDefender.forEach(creature -> creature.applySpellDamage(creature, value));
    }
}
