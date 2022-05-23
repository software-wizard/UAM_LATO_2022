package pl.psi;

import lombok.Getter;
import pl.psi.creatures.Creature;
import pl.psi.spells.Spell;

import java.util.List;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
public class Hero {
    private final List<Creature> creatures;
    private final List<Spell> spells;

    public Hero(final List<Creature> aCreatures, List<Spell> spells) {
        creatures = aCreatures;
        this.spells = spells;
    }
}
