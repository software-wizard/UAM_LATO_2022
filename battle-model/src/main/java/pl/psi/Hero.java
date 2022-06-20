package pl.psi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.psi.creatures.Creature;
import pl.psi.hero.HeroStatistics;
import pl.psi.hero.HeroStatisticsIf;

import java.util.List;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
@Setter
@ToString
public class Hero {
    private List<Creature> creatures;
    private final HeroStatisticsIf stats;
    private final SpellsBook spellBook;
    private HeroeSide side;

    public Hero(List<Creature> aCreatures, HeroStatistics aStats) {
        this(aCreatures, aStats, SpellsBook.builder().build());
    }

    public Hero(List<Creature> aCreatures, SpellsBook aSpellBook) {
        this(aCreatures, HeroStatistics.NECROMANCER, aSpellBook);
    }

    public Hero(List<Creature> aCreatures, final HeroStatisticsIf aStats, SpellsBook aSpellBook) {
        stats = aStats;
        creatures = aCreatures;
        spellBook = aSpellBook;
    }

    public void subtractMana(int manaCost) {
        getSpellBook().setMana(getSpellBook().getMana() - manaCost);
    }
}
