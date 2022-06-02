package pl.psi.skills;

import pl.psi.Hero;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import pl.psi.creatures.ShooterCreature;
import pl.psi.hero.EconomyHero;

/**
 * Class that represents changing creature stats based on current skill
 */

public class UpgradeCalculator {

    private final SkillType skillName;
    private final double skillEffect;

    public UpgradeCalculator(SkillType aName, double aEffect) {
        this.skillName = aName;
        this.skillEffect = aEffect;
    }

    public CreatureStats calculate(Creature aCreature) {
        double changedStat;
        CreatureStats statsToApply = null;
        switch (this.skillName) {
            case ARCHERY:
                if (aCreature instanceof ShooterCreature) {
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

    public void calculate(EconomyHero aHero) {
        double changedStat;
        switch (this.skillName) {
            case LEADERSHIP:
                // update hero's morale
                break;
            case LUCK:
                // update hero's luck
                break;
        }
    }
}
