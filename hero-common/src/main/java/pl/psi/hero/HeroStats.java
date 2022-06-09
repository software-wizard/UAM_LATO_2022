package pl.psi.hero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HeroStats implements HeroStatisticsIf {
    private final String name;
    private final int attack;
    private final int defence;
    private final int spellPower;
    private final int knowledge;
    private int morale;
    private int luck;
    private final int spellPoints;

    public HeroStatisticsIf updateStats(HeroStatisticsIf updatedStats) {
        this.morale += updatedStats.getMorale();
        this.luck += updatedStats.getLuck();
        return this;
    }
}
