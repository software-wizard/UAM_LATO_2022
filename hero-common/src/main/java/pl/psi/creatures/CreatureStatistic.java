package pl.psi.creatures;

import com.google.common.collect.Range;

import lombok.Getter;

@Getter
public enum CreatureStatistic implements CreatureStatisticIf
{
    // NECROPILIS FRACTION
    SKELETON( "Skeleton", 5, 4, 6, 4, Range.closed( 1, 3 ), 1,
            "Average lvl1 foot soldier, but always in huge numbers \nthanks to necromancy skill and skeleton transformer.",
            false, CreatureType.UNDEAD, true, 1), //
    WALKING_DEAD( "Walking Dead", 5, 5, 15, 3, Range.closed( 2, 3 ), 2,
            "Basically its the same skeleton with more hit points.\n I prefer buying 2 skeletons instead.",
            false, CreatureType.UNDEAD, true, 1), //
    WIGHT( "Wight", 7, 7, 18, 5, Range.closed( 3, 5 ), 3,
            "Regenerating ability is really good when fighting \nweak enemies, especially shooters.\nSpecial: top wight of the stack regenerates all\n lost damage in the beginning of each round",
            false, CreatureType.UNDEAD, false, 1), //
    VAMPIRE( "Vampire", 10, 9, 30, 6, Range.closed( 5, 8 ), 4,
            "NOTHING compared to their upgraded brothers. Keep the population growing and recruit after the upgrade.\nSpecial: no enemy retaliation.",
            false,CreatureType.UNDEAD ,false,1), //
    LICH( "Lich", 13, 10, 30, 6, Range.closed( 11, 15 ), 5,
            "Now they last longer and are able to do more damage!\n A must for good necropolis army.\nSpecial: death cloud range attack - \ndamages living creatures on adjacent hexes to target.\n",
            false,CreatureType.UNDEAD,true, 1), //
    BLACK_KNIGHT( "Black Knight", 16, 16, 120, 7, Range.closed( 15, 30 ), 6,
            "Awesome ground unit. As any undead it cannot be blinded, so your enemies will have to look out.\nSpecial: 20% chance to curse enemy.\n",
            false,CreatureType.UNDEAD ,true, 2), //
    BONE_DRAGON( "Bone Dragon", 17, 15, 150, 9, Range.closed( 25, 50 ), 7,
            "They are truly fearsome for enemies with low morale.\n Simply keeping them on battlefield scares enemies.\nSpecial: -1 to enemy morale.\n",
            false,CreatureType.UNDEAD ,false, 2), //
    SKELETON_WARRIOR( "Skeleton Warrior", 6, 6, 6, 5, Range.closed( 1, 3 ), 1,
            "Numerous skeletons become even better, but running back to town and upgrading is a problem... \nIf there is no room in your army for ordinary skeletons, \nnecromancy skill will resurrect skeleton warriors,\n but there will be less of them than normal skeletons, \nso it might be a good idea not to upgrade cursed temple at all.",
            true,CreatureType.UNDEAD ,true, 1), //
    ZOMBIE( "Zombie", 5, 5, 20, 4, Range.closed( 2, 3 ), 2,
            "Attack ratings are way too low... In my opinion, necropolis has the worst lvl2 creature.\nSpecial: 20% chance to disease enemies (-2Att -2Def for 3 rounds)\n",
            true,CreatureType.UNDEAD ,true,1), //
    WRAITH( "Wraith", 7, 7, 18, 5, Range.closed( 3, 5 ), 3,
            "Regenerating ability is really good when fighting weak enemies, especially shooters.\nSpecial: top wight of the stack regenerates all lost damage in the beginning of each round\n",
            true,CreatureType.UNDEAD ,false,1), //
    VAMPIRE_LORD( "Vampire Lord", 10, 10, 40, 9, Range.closed( 5, 8 ), 4,
            "My favorite necropolis unit. Use them as main striking unit and you might end up with no losses!\nSpecial: no enemy retaliation ; resurrects members of their own stack by restoring \nhealth equal to the amount of damage they do to living enemies.\n",
            true,CreatureType.UNDEAD ,false, 1), //
    POWER_LICH( "Power Lich", 13, 10, 40, 7, Range.closed( 11, 15 ), 5,
            "Now they last longer and are able to do more damage! A must for good necropolis army.\nSpecial: death cloud range attack - damages living creatures on adjacent hexes to target.\n",
            true,CreatureType.UNDEAD ,true, 1), //
    DREAD_KNIGHT( "Dread Knight", 18, 18, 120, 9, Range.closed( 15, 30 ), 6,
            "I think it's the best lvl6 unit in the game! Double damage ability puts Dread Knights above Naga Queens.\nSpecial: 20% chance to curse enemy ; 20% chance to do double damage.\n",
            true,CreatureType.UNDEAD ,true, 2), //
    GHOST_DRAGON( "Ghost Dragon", 19, 17, 200, 14, Range.closed( 25, 50 ), 7,
            "When situation seems hopeless, take a chance on the best enemy stack! If you'll get lucky, half their hit points will be gone instantly!! \nAgeing ability makes ghost dragons as dangerous as other lvl7 creatures.\nSpecial: -1 to enemy morale ; 20% chance to age\n enemy (halve hit points of all stack members).\n",
            true,CreatureType.UNDEAD,false, 2),

    // CASTLE FRACTION
    PIKEMAN( "Pikeman", 4, 5, 10, 4, Range.closed( 1, 3 ), 1,
            "Toughest lvl1 unit, but a bit slow.",
            false,CreatureType.ALIVE,true, 1),
    HALBERDIER( "Halberdier", 6, 5, 10, 5, Range.closed( 2, 3 ), 1,
            "Now they are faster and do more damage. Will make a good defence for shooters.",
            true,CreatureType.ALIVE,true, 1),
    GRIFFIN( "Griffin", 8, 8, 25, 5, Range.closed( 3, 6 ), 2,
            "High in population, griffins become castle's main unit for the midgame.",
            false,CreatureType.ALIVE,false, 2),
    ROYAL_GRIFFIN( "ROYAL_GRIFFIN", 9, 9, 25, 9, Range.closed( 3, 6 ), 2,
            "Send them right in the middle of the battlefield. Everyone who comes will get some ;)",
            true,CreatureType.ALIVE,false, 1),
    SWORDSMAN( "Swordsman", 10, 12, 35, 5, Range.closed( 6, 9 ), 3,
            "Not too good unit the upgrade and also too slow.",
            false,CreatureType.ALIVE,true, 1),
    CRUSADER( "Crusader", 12, 12, 35, 6, Range.closed( 7, 10 ), 3,
            "Good upgrade, but still lacks speed. Seem undefeatable in large numbers.\n",
            true,CreatureType.ALIVE,true, 1);

    public enum CreatureType {
        ALIVE, UNDEAD, NON_LIVING
    }

    private final String name;
    private final int attack;
    private final int armor;
    private final int maxHp;
    private final int moveRange;
    private final Range< Integer > damage;
    private final int tier;
    private final String description;
    private final boolean isUpgraded;
    private final CreatureType type;
    private final boolean isGround;
    private final int size;

    CreatureStatistic( final String aName, final int aAttack, final int aArmor, final int aMaxHp,
                       final int aMoveRange, final Range< Integer > aDamage, final int aTier, final String aDescription,
                       final boolean aIsUpgraded, final CreatureType aType, final boolean aIsGround, final int aSize)
    {
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
    }

    String getTranslatedName()
    {
        return name;
    }

}