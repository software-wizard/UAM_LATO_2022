package pl.psi;

import lombok.Getter;
import pl.psi.creatures.Creature;
import pl.psi.hero.HeroStatistics;
import pl.psi.hero.HeroStatisticsIf;
import pl.psi.spells.Spell;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
public class Hero {
    private final List<Creature> creatures;
    private final HeroStatisticsIf stats;
    private final List<? extends Spell> spells;

    public Hero(List<Creature> aCreatures, HeroStatistics aStats) {
        this(aCreatures, aStats, new ArrayList<>());
    }

    public Hero(List<Creature> aCreatures, List<? extends Spell> aSpells) {
        this(aCreatures, HeroStatistics.NECROMANCER, aSpells);
    }

    public Hero(final List<Creature> aCreatures, final HeroStatisticsIf aStats, List<? extends Spell> aSpells) {
        stats = aStats;
        creatures = aCreatures;
        spells = aSpells;
    }
}
