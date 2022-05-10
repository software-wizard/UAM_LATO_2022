package pl.psi.creatures;

import com.google.common.collect.Range;

import lombok.Builder;
import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
@Builder
public class CreatureStats implements CreatureStatisticIf{
    private final String name;
    private final int attack;
    private final int armor;
    private final int maxHp;
    private final int moveRange;
    private final Range< Integer > damage;
    private final int tier;
    private final String description;
    private final boolean isUpgraded;
    private final CreatureStatistic.CreatureType type;
    private final boolean isGround;
    private final int size;

    @Override
    public CreatureStatistic.CreatureType getType() {
        return type;
    }

    public boolean isGround() {
        return isGround;
    }

    @Override
    public int getSize() {
        return size;
    }

}
