package pl.psi.spells;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;

import java.util.Optional;
import java.util.stream.Collectors;

import static pl.psi.spells.SpellNames.*;
import static pl.psi.spells.SpellRang.BASIC;

public class SpellFactorCalculator {

    public double getCreatureProtectionFactor(Spell spell, Creature creature) {
        double protectionFactor = 1;

        if (creature.getImmuneSpellList() != null) {
            for (SpellNames spellName : creature.getImmuneSpellList()) {
                if (spell.getName().equals(spellName)) {
                    return 0;
                }
            }
        }

        if (creature.getVulnerableSpellList() != null) {
            for (SpellNames spellName : creature.getVulnerableSpellList()) {
                if (spell.getName().equals(spellName)) {
                    protectionFactor *= 2;
                }
            }
        }

        switch (spell.getSpellMagicClass()) {
            case AIR:
                if (isCreatureContainsSpellWithName(creature, PROTECTION_FROM_AIR)) {
                    protectionFactor = protectionFactor * getProtectionFactorBySpellName(creature, PROTECTION_FROM_AIR);
                    return protectionFactor;
                }
            case FIRE:
                if (isCreatureContainsSpellWithName(creature, PROTECTION_FROM_FIRE)) {
                    protectionFactor *= getProtectionFactorBySpellName(creature, PROTECTION_FROM_FIRE);
                    return protectionFactor;
                }
            case EARTH:
                if (isCreatureContainsSpellWithName(creature, PROTECTION_FROM_EARTH)) {
                    protectionFactor *= getProtectionFactorBySpellName(creature, PROTECTION_FROM_EARTH);
                    return protectionFactor;
                }
            case WATER:
                if (isCreatureContainsSpellWithName(creature, PROTECTION_FROM_WATER)) {
                    protectionFactor *= getProtectionFactorBySpellName(creature, PROTECTION_FROM_WATER);
                    return protectionFactor;
                }
            default:
                return protectionFactor;
        }
    }

    private boolean isCreatureContainsSpellWithName(Creature creature, SpellNames spellName) {
        return creature.getRunningSpells().stream().map(Spell::getName).collect(Collectors.toList()).contains(spellName);
    }

    private double getProtectionFactorBySpellName(Creature creature, SpellNames spellName) {
        Optional<Spell> spell = creature.getRunningSpells().stream().filter(foundSpell -> foundSpell.getName().equals(spellName)).findAny();
        if (spell.isPresent()) {
            if (spell.get().getRang().equals(BASIC)) return 0.7;
            else return 0.5;
        }
        return 1;
    }
}
