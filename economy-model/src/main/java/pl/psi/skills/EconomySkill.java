package pl.psi.skills;

import lombok.Getter;
import pl.psi.Hero;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

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

    public void apply( List<Creature> aCreatures )
    {
        aCreatures.forEach( aCreature -> {
            CreatureStats statsToApply = this.calculateBuffStrategy.getBuffedStats(aCreature);
            aCreature.buff(statsToApply);
        });
    }

    public void apply( Hero aHero )
    {
        this.calculateBuffStrategy.getBuffedStats( aHero );
    }

    // method that will take spell as an argument
    public void apply(  )
    {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
