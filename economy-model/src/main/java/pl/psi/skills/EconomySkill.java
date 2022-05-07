package pl.psi.skills;

import lombok.Getter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;

import java.util.List;

/**
 * Class that represents hero skills.
 */

@Getter
public class EconomySkill {

    private final SkillName skillName;
    private final int skillCost;
    private final double skillEffect;

    private final CalculateBuffStrategy calculateBuffStrategy;

    public EconomySkill( SkillName aName, int aCost, double aEffect )
    {
        this.skillName = aName;
        this.skillCost = aCost;
        this.skillEffect = aEffect;
        this.calculateBuffStrategy = new CalculateBuffStrategy(this.skillName, this.skillEffect);
    }

    // TODO find out proper way to edit creature skills
    public void apply( List<Creature> aCreatures )
    {
        for ( Creature aCreature: aCreatures )
        {
            CreatureStatistic statsToApply = this.calculateBuffStrategy.calculateBuff(aCreature);
            if (statsToApply != null)
            {
                aCreature.setStats(statsToApply);
            }
        }
    }

    // method that will take spell as an argument
    public void apply(  )
    {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
