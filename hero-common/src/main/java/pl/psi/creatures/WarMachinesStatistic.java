package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WarMachinesStatistic implements CreatureStatisticIf {

    BALLISTA("Ballista", 10, 24, 250, 0, Range.closed(8,12), 1, "Lorem ipsum", false),
    FIRST_AID_TENT("First Aid Tent", 0, 0, 75, 0, Range.closed(-25, -1), 1, "Lorem ipsum", false),
    CATAPULT("Catapult", 10, 24, 1000, 0, Range.closed(0,0), 1, "Lorem ipsum", false);

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
