package pl.psi.hero;

import lombok.Getter;

@Getter
public enum HeroStatistics implements HeroStatisticsIf{

    // NECRO
    DEATH_KNIGHT("Death_Knight", 1, 2, 2, 1, 0, 0),
    NECROMANCER("Necromancer", 1, 0, 2, 2, 0, 0),

    // CASTLE
    KNIGHT("Knight", 2, 2, 1, 1, 0, 0),
    CLERIC("Cleric", 1, 0, 2, 2, 0, 0);

    private final String name;
    private final int attack;
    private final int defence;
    private final int spellPower;
    private final int knowledge;
    private final int morale;
    private final int luck;

    HeroStatistics(final String aName, final int aAttack, final int aDefence, final int aSpellPower, final int aKnowledge, final int aMorale, final int aLuck){

        name = aName;
        attack = aAttack;
        defence = aDefence;
        spellPower = aSpellPower;
        knowledge = aKnowledge;
        morale = aMorale;
        luck = aLuck;

    }
}
