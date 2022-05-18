package pl.psi;

import java.util.List;
import java.util.Set;

import pl.psi.creatures.Creature;

import lombok.Getter;
import pl.psi.spells.Spell;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
public class Hero
{
    private final List< Creature > creatures;
    private final Set<Spell> spells;
    private int spellPower;


    public Hero(final List<Creature> aCreatures, Set<Spell> spells)
    {
        creatures = aCreatures;
        this.spells = spells;
    }
}
