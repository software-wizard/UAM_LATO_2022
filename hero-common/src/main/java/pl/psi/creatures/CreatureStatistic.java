package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.Getter;

@Getter
public enum CreatureStatistic implements CreatureStatisticIf {
    // NECROPILIS FRACTION
    SKELETON("Skeleton", 5, 4, 6, 4, Range.closed(1, 3), 1,
            "Average lvl1 foot soldier, but always in huge numbers thanks to necromancy skill and skeleton transformer.; ",
            false, CreatureType.UNDEAD, true, 1), //
    WALKING_DEAD("Walking Dead", 5, 5, 15, 3, Range.closed(2, 3), 2,
            "Basically its the same skeleton with more hit points. I prefer buying 2 skeletons instead.; ",
            false, CreatureType.UNDEAD, true, 1), //
    WIGHT("Wight", 7, 7, 18, 5, Range.closed(3, 5), 3,
            "Regenerating ability is really good when fighting weak enemies, especially shooters.;Special: top wight of the stack regenerates all lost damage in the beginning of each round",
            false, CreatureType.UNDEAD, false, 1), //
    VAMPIRE("Vampire", 10, 9, 30, 6, Range.closed(5, 8), 4,
            "NOTHING compared to their upgraded brothers. Keep the population growing and recruit after the upgrade.;Special: no enemy retaliation.",
            false, CreatureType.UNDEAD, false, 1), //
    LICH("Lich", 13, 10, 30, 6, Range.closed(11, 15), 5,
            "Now they last longer and are able to do more damage! A must for good necropolis army.;Special: death cloud range attack -\ndamages living creatures on\nadjacent squares to target.",
            false, CreatureType.UNDEAD, true, 1), //
    BLACK_KNIGHT("Black Knight", 16, 16, 120, 7, Range.closed(15, 30), 6,
            "Awesome ground unit. As any undead it cannot be blinded, so your enemies will have to look out.;Special: 20% chance to curse enemy.",
            false, CreatureType.UNDEAD, true, 2), //
    BONE_DRAGON("Bone Dragon", 17, 15, 150, 9, Range.closed(25, 50), 7,
            "They are truly fearsome for enemies with low morale. Simply keeping them on battlefield scares enemies.;Special: -1 to enemy morale.",
            false, CreatureType.UNDEAD, false, 2), //
    SKELETON_WARRIOR("Skeleton Warrior", 6, 6, 6, 5, Range.closed(1, 3), 1,
            "Numerous skeletons become even better, but running back to town and upgrading is a problem... If there is no room in your army for ordinary skeletons, necromancy skill will resurrect skeleton warriors, but there will be less of them than normal skeletons, so it might be a good idea not to upgrade cursed temple at all.; ",
            true, CreatureType.UNDEAD, true, 1),
    ZOMBIE("Zombie", 5, 5, 20, 4, Range.closed(2, 3), 2,
            "Attack ratings are way too low... In my opinion, necropolis has the worst lvl2 creature.;Special: 20% chance to disease enemies (-2Att -2Def for 3 rounds)",
            true, CreatureType.UNDEAD, true, 1), //
    WRAITH("Wraith", 7, 7, 18, 5, Range.closed(3, 5), 3,
            "Regenerating ability is really good when fighting weak enemies, especially shooters.;Special: top wight of the stack regenerates all lost damage in the beginning of each round",
            true, CreatureType.UNDEAD, false, 1), //
    VAMPIRE_LORD("VampireL", 10, 10, 40, 9, Range.closed(5, 8), 4,
            "My favorite necropolis unit. Use them as main striking unit and you might end up with no losses!;Special: no enemy retaliation\nResurrects members of their own \nstack by restoring health equal to \nthe amount of damage they do \nto living enemies.",
            true, CreatureType.UNDEAD, false, 1), //
    POWER_LICH("Power Lich", 13, 10, 40, 7, Range.closed(11, 15), 5,
            "Now they last longer and are able to do more damage! A must for good necropolis army.;Special: death cloud range attack - damages living creatures on adjacent squares to target.",
            true, CreatureType.UNDEAD, true, 1), //
    DREAD_KNIGHT("Dread Knight", 18, 18, 120, 9, Range.closed(15, 30), 6,
            "I think it's the best lvl6 unit in the game! Double damage ability puts Dread Knights above Naga Queens.;Special: 20% chance to curse enemy\n20% chance to do double damage.",
            true, CreatureType.UNDEAD, true, 2), //
    GHOST_DRAGON("Ghost Dragon", 19, 17, 200, 14, Range.closed(25, 50), 7,
            "When situation seems hopeless, take a chance on the best enemy stack! If you'll get lucky, half their hit points will be gone instantly!! Ageing ability makes ghost dragons as dangerous as other lvl7 creatures.;Special: -1 to enemy morale\n20% chance to age enemy (halve hit points of all stack members).\n ",
            true, CreatureType.UNDEAD, false, 2),

    //STRONGHOLD FRACTION
    GOBLIN("Goblin", 4, 2, 5, 5, Range.closed(1, 2), 1,
            "Slightly weaker than average for level 1, but better than imp for a smaller price. Highly populated, goblin is a good offensive unit to start with. Nice speed for level 1, especially after the upgrade. Defence is lower than attack, so be sure to attack before attacked.; ",
            false, CreatureType.ALIVE, true, 1),
    WOLF_RIDER("Wolf Rider", 7, 5, 10, 6, Range.closed(2, 4), 2,
            "Basically you're getting the same goblin, slightly better stats and larger size. Weak for level 2, wolf rider is only slightly better than level 1 centaur captain. Wolves should attack before attacked, applies to wolf raiders especially.; ",
            false, CreatureType.ALIVE, true, 2),
    ORC("Orc", 8, 4, 15, 4, Range.closed(2, 5), 3,
            "Weaker than beholders and elves, orcs are the weakest level 3 shooters, but that is also reflected in their low price. Reasonable stats, wide damage easily affected by bless and curse, slow speed which will be reflected on your hero movement, but if you plan using ogres in your army, they are just as slow, so feel free to take some orcs. Cyclops will replace orcs later on.; ",
            false, CreatureType.ALIVE, true, 1),
    OGRE("Ogre", 13, 7, 40, 4, Range.closed(6, 12), 4,
            "A great level 4 unit if it wasn't for the superlow speed. Nice damage, great attack, low price, while the low defence is backed up by plenty hit points. Orcs and ogres are a speed pair, so either have both or none for your main hero. Ogres make great defenders, bashing the enemy from close range as they approach your juicy shooters. For all ogres - watch out for curse and apply bless as required.; ",
            false, CreatureType.ALIVE, true, 1),
    ROC("Roc", 13, 11, 60, 7, Range.closed(11, 15), 5,
            "Let's roc! A nice level 5 unit with higher hit points than most. Roc is also very balanced, having no weak sides such as damage range or low defence. Use them for whatever purpose, but being the only stronghold flyers they often die alone in the enemy crowd.; ",
            false, CreatureType.ALIVE, false, 2),
    CYCLOPS("Cyclops", 15, 12, 70, 6, Range.closed(16, 20), 6,
            "Now we're shooting! Only titan's lightning can hit stronger than cyclops' rocks, and that abbility to attack seige walls is completely exlusive to cyclops. Want 3 extra catapults? Split cyclops into multiple stacks, seige walls take same damage from 1 cyclops as from 100. Cyclops is also a well-balanced unit with no weakneses except for the hand-to-hand penalty. Keep that ammo cart handy for long ones.;Special: can attack seige walls at basic ballistics level.",
            false, CreatureType.ALIVE, true, 1),
    BEHEMOTH("Behemoth", 17, 17, 160, 6, Range.closed(30, 50), 7,
            "That smile scares the hell out of whoever behemoth is attacking, reducing their abbility to defend themselves. Slow and quite vunerable, behemoth is also a cheap level 7 unit, definitely attack-oriented and able to deal huge damage by reducing enemy defence during the attack. Try to use charge tactics whenever possible, do not leave behemoth exposed to enemy attacks, too easy to lose them.;Special: target is able to use only 60% of its defence when attacked by behemoth.",
            false, CreatureType.ALIVE, true, 2),
    HOBGOBLIN("Hobgoblin", 5, 3, 5, 7, Range.closed(1, 2), 1,
            "The speed is worth 10 gold, combined attack and defence increase. Hobgoblins are more able to attack before failing to defend themselves. However, the damage is lacking: 1-2 is lower than 1-3 or 2-3 that many other units of this level have. Since this upgrade is required for wolf raiders, you will probably end up doing it, but 5 wood and 5 ore are better saved for a castle.; ",
            true, CreatureType.ALIVE, true, 1),
    WOLF_RAIDER("Wolf Raider", 8, 5, 10, 8, Range.closed(3, 4), 2,
            "Awesome upgrade that makes wolves do about 2.5 times more damage. Defence and hit points remain the same, so use the sudden charge tactics. Try to get your target to waste their retaliation before raiders make their double move. Ahhh, HOMM1 memories... same two attacks, but those wolves were white and self-sufficient :) As for getting the upgrade - it is very expensive in terms of wood and ore because you are required to upgrade goblins first. If you're poor, you might even forget about using goblins and wolves in your main army, just let them stock up for a bad day.;Special: Attacks twice.",
            true, CreatureType.ALIVE, true, 2),
    ORC_CHIEFTAIN("Orc Chieftain", 8, 4, 20, 5, Range.closed(2, 5), 3,
            "A small and cheap upgrade that makes orcs slightly faster (same as ogre magi) and tougher. Do it if you want orcs to last longer or if all your ogres are upgraded already, so that the lowest army speed will be 5 instead of 4. Comparing the pictures, orc chieftain has one extra axe - this is to be interpreted as double amount of shots :); ",
            true, CreatureType.ALIVE, true, 1),
    OGRE_MAGI("Ogre Magi", 13, 7, 60, 5, Range.closed(6, 12), 4,
            "Great upgrade, 50% tougher for 33% extra price plus the spell bonus. Ogre magi are the toughest among their level, their supperiority undermined perhaps only by vampires' life drain. Stock up on these guys because they can dellay your enemy's victory by few thousand hit points. Ogre magi are still slow, but they don't just stand there anymore - bloodlust spell will assist the advancing armies while magi defend the shooters. Bloodlust increases attack skill for the time of 6 rounds.;Special: Can cast bloodlust spell on one ally per round at spell power 6, advanced level.",
            true, CreatureType.ALIVE, true, 1),
    THUNDERBIRD("Roc", 13, 11, 60, 11, Range.closed(11, 15), 5,
            "Thunderbolt strike is a useful damage since it is not affected by defence. The only flying unit for stronghold, they are still not good enough to fly over the castle walls alone. This upgrade is good to get the earlier spellcasting turn: speed 11 is the highest in stronghold, followed by 9 of ancient behemoth. And hey, the looks are good, they actually look more powerful to your human opponent.;Special: 20% chance for thunderbolt spell which does 10 damage per each thunderbird to target.",
            true, CreatureType.ALIVE, false, 2),
    CYCLOPS_KING("Cyclops King", 17, 13, 70, 8, Range.closed(16, 20), 6,
            "They aren't kings for nothing, packing two seige shots in one turn and leveling walls to the ground in no time. You'll notice that the lack of flyers is compensated by the cyclops' special abbilities. Kings however are quite pricey - look at that golden skirt armor that you are buying them for 350 gold! Attack, defence and speed improvements are too small to be worth 350 gold, so upgrade only if you are filthy rich and are heading for a long seige. Think about it, 2 kings cost almost as much as 3 normal cyclops!;Special: can attack seige walls at advanced ballistics level.",
            true, CreatureType.ALIVE, true, 1),
    ANCIENT_BEHEMOTH("Ancient Behemoth", 19, 19, 300, 9, Range.closed(30, 50), 7,
            "Quite low on attack, defence and speed for the best creature, but hit points are at the top and the special abbility is amazing: the target is virtually defenceless however high it's skill is, even if it is commanded by a good hero, nothing helps, only 1/5th of defence will be useful to the scared enemy. Ancient behemoth is able to deal the highest damage amongst all level 7 units, but it's use is more strategic than that: always attack the unit with the highest defence skill since only behemoths can bypass it. If you are ever to face the ancient behemoth, command all your units to attack at once - its attack is great, but its defence is nothing special, making it a strategic target.;Special: target is able to use only 20% of its defence when attacked by ancient behemoth.",
            true, CreatureType.ALIVE, true, 2),
    //CASTLE FRACTION
    PIKEMAN("Pikeman", 4, 5, 10, 4, Range.closed(1, 3), 1,
            "Toughest lvl1 unit, but a bit slow.\n",
            false, CreatureType.ALIVE, true, 1),
    HALBERDIER("Halberdier", 6, 5, 10, 5, Range.closed(2, 3), 1,
            "Now they are faster and do more damage. Will make a good defence for shooters.\n",
            true, CreatureType.ALIVE, true, 1),
    ARCHER("Archer", 6, 3, 10, 4, Range.closed(2, 3), 2,
            "Archer's upgrade is literally 2 times better. Upgrade them quickly.\n",
            false, CreatureType.ALIVE, true, 1),
    MARKSMAN("Marksman", 6, 3, 10, 6, Range.closed(2, 3), 2,
            "Awesome upgrade, but they still lack defence...\n",
            true, CreatureType.ALIVE, true, 1),
    GRIFFIN("Griffin", 8, 8, 25, 5, Range.closed(3, 6), 3,
            "High in population, griffins become castle's main unit for the midgame.\n",
            false, CreatureType.ALIVE, false, 2),
    ROYAL_GRIFFIN("Halberdier", 9, 9, 25, 9, Range.closed(3, 6), 3,
            "Send them right in the middle of the battlefield. Everyone who comes will get some ;)\n",
            true, CreatureType.ALIVE, false, 1),
    SWORDSMAN("Swordsman", 10, 12, 35, 5, Range.closed(6, 9), 4,
            "Not too good unit the upgrade and also too slow.\n",
            false, CreatureType.ALIVE, true, 1),
    CRUSADER("Crusader", 12, 12, 35, 6, Range.closed(7, 10), 4,
            "Good upgrade, but still lacks speed. Seem undefeatable in large numbers.\n",
            true, CreatureType.ALIVE, true, 1),
    MONK("Monk", 12, 7, 30, 5, Range.closed(10, 12), 5,
            "Good shooter, nice damage.\n",
            false, CreatureType.ALIVE, true, 1),
    ZEALOT("Zealot", 12, 10, 35, 7, Range.closed(7, 10), 5,
            "Zealots are skilled enough to use the same magic powers at very close range. Better defence too.\n",
            true, CreatureType.ALIVE, true, 1);

    private final String name;
    private final double attack;
    private final double armor;
    private final double maxHp;
    private final double moveRange;
    private final Range<Integer> damage;
    private final int tier;
    private final String description;
    private final boolean isUpgraded;
    private final CreatureType type;
    private final boolean isGround;
    private final int size;

    CreatureStatistic(final String aName, final int aAttack, final int aArmor, final int aMaxHp,
                      final int aMoveRange, final Range<Integer> aDamage, final int aTier, final String aDescription,
                      final boolean aIsUpgraded, final CreatureType aType, final boolean aIsGround, final int aSize) {
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

    public enum CreatureType {
        ALIVE, UNDEAD, NON_LIVING
    }
}
