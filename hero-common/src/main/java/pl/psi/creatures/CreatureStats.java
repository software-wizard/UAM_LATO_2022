package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
@Builder
@ToString
public class CreatureStats implements CreatureStatisticIf {
    private final String name;
    private double attack;
    private double armor;
    private double maxHp;
    private double moveRange;
    private final int magicResist;
    private Range<Integer> damage;
    private final int tier;
    private final String description;
    private final boolean isUpgraded;
    private final CreatureStatistic.CreatureType type;
    private final boolean isGround;
    private final int size;
    private final String imagePath;
    private final String canAttackImagePath;
    private final String currentImagePath;

    @Override
    public CreatureStatistic.CreatureType getType() {
        return type;
    }

    @Override
    public boolean isGround() {
        return isGround;
    }

    @Override
    public int getSize() {
        return size;
    }

    public CreatureStatisticIf addStats(CreatureStatisticIf statsToAdd) {
        attack += statsToAdd.getAttack();
        armor += statsToAdd.getArmor();
        maxHp += statsToAdd.getMaxHp();
        moveRange += statsToAdd.getMoveRange();
        if (!Objects.isNull(statsToAdd.getDamage())) {
            damage = statsToAdd.getDamage();
        }
        return this;
    }


}
