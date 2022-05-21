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
}
