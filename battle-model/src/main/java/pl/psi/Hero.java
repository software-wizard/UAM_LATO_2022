package pl.psi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import pl.psi.creatures.Creature;

import lombok.Getter;
import pl.psi.hero.HeroStatisticsIf;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
public class Hero
{
    private final List< Creature > creatures;
    private final HeroStatisticsIf stats;

    public Hero(final List< Creature > aCreatures, final HeroStatisticsIf aStats)
    {
        stats = aStats;
        creatures = aCreatures;
    }
}
