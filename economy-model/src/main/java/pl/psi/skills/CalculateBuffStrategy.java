package pl.psi.skills;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.creatures.ShooterCreature;

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

    public void applyBuff( Creature aCreature )
    {
        double changedStat;
        switch (this.skillName)
        {
            case ARCHERY:
                if (aCreature instanceof ShooterCreature)
                {
                    changedStat = (1 + this.skillEffect) * aCreature.getStats().getAttack();
                    aCreature.setAttack((int) Math.round(changedStat));
                }
                break;
            case OFFENCE:
                changedStat = (1 + this.skillEffect) * aCreature.getStats().getAttack();
                // TODO discuss is it possible right now to change stats type
                aCreature.setAttack((int) Math.round(changedStat));
                break;
            case ARMOURER:
                changedStat = (1 + this.skillEffect) * aCreature.getStats().getArmor();
                aCreature.setArmor((int) Math.round(changedStat));
                break;
            case RESISTANCE:
                break;
        }
    }
}
