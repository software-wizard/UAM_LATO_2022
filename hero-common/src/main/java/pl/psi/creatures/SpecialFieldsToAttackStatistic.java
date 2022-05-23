package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpecialFieldsToAttackStatistic implements CreatureStatisticIf {

    WALL("Wall", 0, 0, 20, 0, Range.closed(0,0), 1, "Lorem ipsum", false);

    private final String name;
    private final int attack;
    private final int armor;
    private final int maxHp;
    private final int moveRange;
    private final Range< Integer > damage;
    private final int tier;
    private final String description;
    private final boolean isUpgraded;
}
