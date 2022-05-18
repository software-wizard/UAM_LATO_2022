package pl.psi.spells;

import pl.psi.creatures.Creature;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DamageSpell extends Spell<Integer> {

    public DamageSpell(SpellsCategories category, String name, int rang, int manaCost, int multiplier, Integer value) {
        super(category, name, rang, manaCost, multiplier, value);
    }

    @Override
    public void castSpell(Creature aDefender) {
        applyDamage(aDefender, getValue());
    }

    @Override
    public void castSpell(List<Optional<Creature>> aDefender) {
        aDefender.forEach(
                optionalCreature -> optionalCreature.ifPresent(
                        creature -> applyDamage(creature, getValue())));
    }

    private void applyDamage(Creature aDefender, Integer damage) {
        aDefender.setCurrentHp(aDefender.getCurrentHp() - (getMultiplier() + damage));
    }
}
