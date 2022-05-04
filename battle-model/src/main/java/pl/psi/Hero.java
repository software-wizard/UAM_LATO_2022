package pl.psi;

import java.util.List;
import java.util.Set;

import lombok.Setter;
import pl.psi.creatures.Creature;

import lombok.Getter;
import pl.psi.spells.Spell;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Hero
{
    @Getter
    private final List< Creature > creatures;

    private Set<Spell> spells; // final

    public Hero( final List< Creature > aCreatures )
    {
        creatures = aCreatures;
    }
}
