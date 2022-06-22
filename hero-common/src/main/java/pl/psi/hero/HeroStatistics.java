package pl.psi.hero;

import lombok.Builder;
import lombok.Getter;

import java.util.Optional;


@Getter
public enum HeroStatistics implements HeroStatisticsIf {

    // NECRO
    DEATH_KNIGHT("Death_Knight", 1, 2, 2, 1, 0, 0),
    NECROMANCER("Necromancer", 1, 0, 2, 2, 0, 0),

    // CASTLE
    KNIGHT("Knight", 2, 2, 1, 1, 0, 0),
    CLERIC("Cleric", 1, 0, 2, 2, 0, 0),

    // STRONGHOLD
    BARBARIAN("Barbarian", 4,0,1,1,0,0);

    private final static int KNOWLEDGE_MANA_FACTOR = 10;
    private final String name;
    private final int attack;
    private final int defence;
    private final int spellPower;
    private final int knowledge;
    private int morale;
    private int luck;
    private final int spellPoints;

    HeroStatistics(final String aName, final int aAttack, final int aDefence, final int aSpellPower, final int aKnowledge, final int aMorale, final int aLuck) {

        name = aName;
        attack = aAttack;
        defence = aDefence;
        spellPower = aSpellPower;
        knowledge = aKnowledge;
        morale = aMorale;
        luck = aLuck;
        spellPoints = knowledge * KNOWLEDGE_MANA_FACTOR;

    }

    @Override
    public HeroStatisticsIf updateStats(HeroStatisticsIf newStats) {
        this.morale += newStats.getMorale();
        this.luck += newStats.getLuck();
        return this;
    }
}
