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

    private final SkillType skillType;
    private final SkillCostValueObject skillCost;
    private final double factor;

    private final UpgradeCalculator upgradeCalculator;

    public EconomySkill(SkillType aType, int aCost, double aFactor) {
        this.skillType = aType;
        this.skillCost = new SkillCostValueObject(aCost);
        this.factor = aFactor;
        this.upgradeCalculator = new UpgradeCalculator(this.skillType, this.factor);
    }

    public void apply(List<Creature> aCreatures) {
        aCreatures.forEach(aCreature -> {
            CreatureStats statsToApply = this.upgradeCalculator.calculate(aCreature);
            aCreature.buff(statsToApply);
        });
    }

    public void apply(Hero aHero) {
        this.upgradeCalculator.calculate(aHero);
    }

    // method that will take spell as an argument
    public void apply() {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
