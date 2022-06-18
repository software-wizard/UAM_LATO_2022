package pl.psi.spells;

import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static pl.psi.spells.SpellNames.*;
import static pl.psi.spells.SpellRang.BASIC;

public class DamageSpell extends Spell<Creature> {

    private double protectionFactor;
    private final double value;

    public DamageSpell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, int manaCost, double value) {
        super(category, name, spellMagicClass, rang, manaCost);
        this.value = value;
    }

    @Override
    public void castSpell(Creature aDefender, BiConsumer<String, PropertyChangeListener> consumer) {
        System.out.println(aDefender.getImmuneSpellList());
//        ElementalDecorator elementalDecorator = (ElementalDecorator) aDefender;
//        System.out.println(elementalDecorator.getImmune());
        double damage = (isCreatureHasProtection(aDefender) ? value * protectionFactor : value);
        aDefender.applySpellDamage(aDefender, damage);
    }

    private boolean isCreatureHasProtection(Creature creature) {
        switch (getSpellMagicClass()) {
            case AIR:
                return isCreatureContainsSpellWithName(creature, PROTECTION_FROM_AIR);
            case FIRE:
                return isCreatureContainsSpellWithName(creature, PROTECTION_FROM_FIRE);
            case EARTH:
                return isCreatureContainsSpellWithName(creature, PROTECTION_FROM_EARTH);
            case WATER:
                return isCreatureContainsSpellWithName(creature, PROTECTION_FROM_WATER);
            default:
                return false;
        }
    }

    private boolean isCreatureContainsSpellWithName(Creature creature, SpellNames spellName) {
        if (creature.getRunningSpells().stream().map(Spell::getName).collect(Collectors.toList()).contains(spellName)) {
            setProtectionFactor(creature, spellName);
            return true;
        }
        return false;
    }

    private void setProtectionFactor(Creature creature, SpellNames spellName) {
        creature.getRunningSpells().stream()
                .filter(spell -> spell.getName().equals(spellName))
                .findAny()
                .ifPresent(spell -> {
                    if (spell.getRang().equals(BASIC)) protectionFactor = 0.7;
                    else protectionFactor = 0.5;
                });
    }

    @Override
    public void unCastSpell(Creature aDefender) {

    }

}
