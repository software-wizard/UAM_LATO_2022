import lombok.Value;

import com.google.common.collect.Range;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Value
public class CreatureStatistic
{
    private final String name;
    private final int maxHp;
    private final Range< Integer > damage;
    private final int attack;
    private final int defence;
    private final int moveRange;
    private final int tier;
    private final boolean isUpgraded;
    private final String description;
}
