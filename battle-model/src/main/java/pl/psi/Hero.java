package pl.psi;

import java.util.List;
import pl.psi.creatures.Creature;

import lombok.Getter;
import pl.psi.hero.HeroStatisticsIf;
import pl.psi.creatures.Creature;
import pl.psi.spells.Spell;

import java.util.List;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
public class Hero{
    private final List< Creature > creatures;
    private final HeroStatisticsIf stats;

    public Hero(final List< Creature > aCreatures, final HeroStatisticsIf aStats, List<Spell> aSpells)
    {
        stats = aStats;
        creatures = aCreatures;
        this.spells = aSpells;
    }
}
