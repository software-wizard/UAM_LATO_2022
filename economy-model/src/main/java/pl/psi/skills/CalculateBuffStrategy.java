package pl.psi.skills;

import pl.psi.Hero;
import pl.psi.creatures.*;

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

    public CreatureStats getBuffedStats(Creature aCreature )
    {
        double changedStat;
        CreatureStats statsToApply = null;
        switch ( this.skillName )
        {
            case ARCHERY:
                if (aCreature instanceof ShooterCreature)
                {
                    changedStat = (1 + this.skillEffect) * aCreature.getStats().getAttack();
                    statsToApply = CreatureStats.builder()
                            .armor(changedStat)
                            .build();
                }
                break;
            case OFFENCE:
                changedStat = (1 + this.skillEffect) * aCreature.getStats().getAttack();
                statsToApply = CreatureStats.builder()
                        .attack(changedStat)
                        .build();
                break;
            case ARMOURER:
                changedStat = (1 + this.skillEffect) * aCreature.getStats().getArmor();
                statsToApply = CreatureStats.builder()
                        .armor(changedStat)
                        .build();
                break;
            case RESISTANCE:
                break;
            default:
                statsToApply = CreatureStats.builder().build();
                break;
        }
        return statsToApply;
    }

    public void getBuffedStats( Hero aHero )
    {
        double changedStat;
        switch ( this.skillName )
        {
            case LEADERSHIP:
                // update hero's morale
                break;
            case  LUCK:
                // update hero's luck
                break;
        }
    }
}
