package pl.psi.creatures;

import com.google.common.collect.Range;

public interface CreatureStatisticIf {
    String getName();

    double getAttack();

    double getArmor();

    double getMaxHp();

    double getMoveRange();

    Range<Integer> getDamage();

    int getTier();

    String getDescription();

    boolean isUpgraded();

    CreatureStatistic.CreatureType getType();

    CreatureStatistic.CreatureGroup getGroup();

    int getSize();

    boolean isGround();

    default boolean isGoodAligned() {
        return false;
    }

    String getImagePath();

    String getCanAttackImagePath();

    String getCanBuffImagePath();

    String getCurrentImagePath();
}
