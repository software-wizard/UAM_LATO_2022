package pl.psi.spells;

import pl.psi.creatures.AbstractCreature;
import pl.psi.creatures.Creature;

import java.util.List;

public class ElementalDecorator extends AbstractCreature {

    private final Creature decorated;
    private final List<SpellNames> immune;
    private final List<SpellNames> vulnerable;

    public ElementalDecorator(Creature aDecorated, List<SpellNames> immune, List<SpellNames> vulnerable) {
        super(aDecorated);
        this.decorated = aDecorated;
        this.immune = immune;
        this.vulnerable = vulnerable;
    }

    @Override
    public List<SpellNames> getImmuneSpellList() {
        return immune;
    }

    @Override
    public List<SpellNames> getVulnerableSpellList() {
        return vulnerable;
    }
}
