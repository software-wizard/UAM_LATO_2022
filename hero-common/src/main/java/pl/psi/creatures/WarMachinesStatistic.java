package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WarMachinesStatistic implements CreatureStatisticIf {

    BALLISTA("Ballista", 10, 24, 250, 0, Range.closed(8, 12), 1, "Lorem ipsum", false, CreatureStatistic.CreatureType.NON_LIVING, true, 1, CreatureStatistic.CreatureGroup.OTHER),
    FIRST_AID_TENT("First Aid Tent", 0, 0, 75, 0, Range.closed(0, 0), 2, "Lorem ipsum", false, CreatureStatistic.CreatureType.NON_LIVING, true, 1, CreatureStatistic.CreatureGroup.OTHER),
    AMMO_CART("Ammo Cart", 0, 0, 75, 0, Range.closed(0, 0), 2, "Lorem ipsum", false, CreatureStatistic.CreatureType.NON_LIVING, true, 1,CreatureStatistic.CreatureGroup.OTHER),
    CATAPULT("Catapult", 10, 24, 1000, 0, Range.closed(1, 2), 3, "Lorem ipsum", false, CreatureStatistic.CreatureType.NON_LIVING, true, 1,CreatureStatistic.CreatureGroup.OTHER);

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
    private final String canAttackImagePath;
    private final String canBuffImagePath;
    private final String currentImagePath;
    private final CreatureStatistic.CreatureGroup group;
    private final String IMAGE_FOLDER = "/images/machines/";


    @Override
    public CreatureStatistic.CreatureGroup getGroup() {
        return null;
    }

    WarMachinesStatistic(final String aName, final int aAttack, final int aArmor, final int aMaxHp,
                      final int aMoveRange, final Range<Integer> aDamage, final int aTier, final String aDescription,
                      final boolean aIsUpgraded, final CreatureStatistic.CreatureType aType, final boolean aIsGround, final int aSize,
                      final CreatureStatistic.CreatureGroup aGroup) {
        name = aName;
        attack = aAttack;
        armor = aArmor;
        maxHp = aMaxHp;
        moveRange = aMoveRange;
        damage = aDamage;
        tier = aTier;
        description = aDescription;
        isUpgraded = aIsUpgraded;
        type = aType;
        size = aSize;
        isGround = aIsGround;
        imagePath = IMAGE_FOLDER + aName + ".jpg";
        canAttackImagePath = IMAGE_FOLDER + aName + " canAttack.jpg";
        canBuffImagePath = IMAGE_FOLDER + aName + " canBuff.jgp";
        currentImagePath = IMAGE_FOLDER + aName + " current.jpg";
        group = aGroup;
    }
}
