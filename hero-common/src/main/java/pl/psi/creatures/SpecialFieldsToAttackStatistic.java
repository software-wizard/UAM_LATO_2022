package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpecialFieldsToAttackStatistic implements CreatureStatisticIf {

    WALL("Wall", 0, 0, 20, 0, Range.closed(0, 0), 1, "Lorem ipsum", false, CreatureStatistic.CreatureType.NON_LIVING, true, 1,"");

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
        return null;
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
