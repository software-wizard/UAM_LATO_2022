package pl.psi.hero;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HeroStats implements HeroStatisticsIf {
    private final String name;
    private final int attack;
    private final int defence;
    private final int spellPower;
    private final int knowledge;
    private final int morale;
    private final int luck;
    private final int spellPoints;


    public HeroStats(HeroStatisticsIf prevStats, HeroStatsFieldType aFieldType, int upgradedSkill) {
        if (aFieldType == HeroStatsFieldType.LUCK) {
            this.luck = upgradedSkill;
            this.morale = prevStats.getMorale();
        }
        else if (aFieldType == HeroStatsFieldType.MORALE) {
            this.morale = upgradedSkill;
            this.luck = prevStats.getLuck();
        } else {
            this.morale = prevStats.getMorale();
            this.luck = prevStats.getLuck();
        }
        this.name = prevStats.getName();
        this.attack = prevStats.getAttack();
        this.defence = prevStats.getDefence();
        this.spellPower = prevStats.getSpellPower();
        this.spellPoints = prevStats.getSpellPoints();
        this.knowledge = prevStats.getKnowledge();
    }
}
