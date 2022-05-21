package pl.psi;

import java.util.HashSet;
import java.util.List;

import pl.psi.creatures.Creature;

import lombok.Getter;
import pl.psi.hero.HeroStatisticsIf;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Hero
{
    @Getter
    private final List< Creature > creatures;
    private HeroStatisticsIf stats;

    public Hero( final List< Creature > aCreatures, final HeroStatisticsIf aStats, final HashSet<Artifact> aItems, final List<Artifact> aBackpack )
    {
        stats = aStats;
        creatures = aCreatures;
        items = aItems;
        backpack = aBackpack;
    }
}
