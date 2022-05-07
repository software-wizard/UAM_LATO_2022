package pl.psi.skills;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;

/**
 * Class that represents changing creature stats based on current skill
 */

public class CalculateBuffStrategy {

    private final SkillName skillName;
    private final double skillEffect;

    public CalculateBuffStrategy( SkillName aName, double aEffect )
    {
        this.skillName = aName;
        this.skillEffect = aEffect;
    }

    public CreatureStatistic calculateBuff(Creature aCreature )
    {
        CreatureStatistic statisticToReturn;
        switch (this.skillName)
        {
            case ARCHERY:
                break;
            case OFFENCE:
                // TODO find out what type should skillEffect be
                double changedStat = (1 + this.skillEffect) * aCreature.getStats().getAttack();
                break;
            case ARMOURER:
                break;
            case RESISTANCE:
                break;
        }
        return statisticToReturn;
    }
}
