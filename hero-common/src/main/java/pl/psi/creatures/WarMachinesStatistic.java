package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WarMachinesStatistic implements CreatureStatisticIf {

    BALLISTA("Ballista", 10, 24, 250, 0, Range.closed(8, 12), 1, "Lorem ipsum", false, CreatureStatistic.CreatureType.NON_LIVING, true, 1,""),
    FIRST_AID_TENT("First Aid Tent", 0, 0, 75, 0, Range.closed(0, 0), 2, "Lorem ipsum", false, CreatureStatistic.CreatureType.NON_LIVING, true, 1,""),
    AMMO_CART("Ammo Cart", 0, 0, 75, 0, Range.closed(0, 0), 2, "Lorem ipsum", false, CreatureStatistic.CreatureType.NON_LIVING, true, 1,""),
    CATAPULT("Catapult", 10, 24, 1000, 0, Range.closed(1, 2), 3, "Lorem ipsum", false, CreatureStatistic.CreatureType.NON_LIVING, true, 1,"");

    private final String name;
    private final double attack;
    private final double armor;
    private final double maxHp;
    private final double moveRange;
    private final Range<Integer> damage;
    private final int tier;
    private final String description;
    private final boolean isUpgraded;
    private final CreatureStatistic.CreatureType type;
    private final boolean isGround;
    private final int size;
    private final String imagePath;

    @Override
    public CreatureStatistic.CreatureGroup getGroup() {
        return CreatureStatistic.CreatureGroup.OTHER;
    }

    @Override
    public String getCanAttackImagePath() {
        return "";
    }

    @Override
    public String getCanBuffImagePath() {
        return "";
    }

    @Override
    public String getCurrentImagePath() {
        return "";
    }
}
