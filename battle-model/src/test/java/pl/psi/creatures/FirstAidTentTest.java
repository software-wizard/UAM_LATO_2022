package pl.psi.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FirstAidTentTest {

    private static final int NOT_IMPORTANT = 100;
    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);


    @Test
    void firstAidTentHeals() {
        List<Creature> creaturesList = new ArrayList();
        final Creature creature1 = new Creature.Builder().statistic(CreatureStats.builder()
                .name("creature1")
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .armor(NOT_IMPORTANT)
                .build())
                .build();
        creature1.setHeroNumber(1);
        creature1.setCurrentHp(30);

        final WarMachinesAbstract firstAidTent;
        firstAidTent = new WarMachinesFactory().create(2, 1, null, 0);
        firstAidTent.setHeroNumber(1);
        //firstAidTent ma skilla na poziomie 0, więc może dodać od 1 do 25 punktów, stąd to minimum i maximum
        double creature1HPminimumvalue = creature1.getCurrentHp() + 1;
        double creature1HPmaximumvalue = creature1.getCurrentHp() + 25;

        creaturesList.add(creature1);
        firstAidTent.performAction(creaturesList);
        double creature1HPValueAfterHeal = creature1.getCurrentHp();
        //Sprawdzamy czy po wyleczeniu HP creatury1 jest w zakresie
        assertTrue(creature1HPminimumvalue <= creature1HPValueAfterHeal && creature1HPValueAfterHeal <= creature1HPmaximumvalue);

    }

    @Test
    void firstAidTentDoesNotHealCreaturesWithDifferentHeroNumber() {
        List<Creature> creaturesList = new ArrayList();
        final Creature creature1 = new Creature.Builder().statistic(CreatureStats.builder()
                .name("creature1")
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .armor(NOT_IMPORTANT)
                .build())
                .build();
        creature1.setHeroNumber(2);
        creature1.setCurrentHp(20);

        final Creature creature2 = new Creature.Builder().statistic(CreatureStats.builder()
                .name("creature2")
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .armor(NOT_IMPORTANT)
                .build())
                .build();
        creature2.setHeroNumber(2);
        creature2.setCurrentHp(50);

        final Creature creature3 = new Creature.Builder().statistic(CreatureStats.builder()
                .name("creature1")
                .maxHp(100)
                .damage(NOT_IMPORTANT_DMG)
                .armor(NOT_IMPORTANT)
                .build())
                .build();
        creature3.setHeroNumber(1);
        creature3.setCurrentHp(15);

        final WarMachinesAbstract firstAidTent;
        firstAidTent = new WarMachinesFactory().create(2, 1, null, 0);
        firstAidTent.setHeroNumber(1);


        creaturesList.add(creature1);
        creaturesList.add(creature2);
        creaturesList.add(creature3);

        double creature3CurrentHP = creature3.getCurrentHp();
        double creature2CurrentHP = creature2.getCurrentHp();
        double creature1CurrentHP = creature1.getCurrentHp();

        firstAidTent.performAction(creaturesList);

        //Ponieważ creature1 i creature2 nie należą do tego samego herosa co firstAidTent to nie zostaną uleczone
        //creature3 zawsze zosanie uleczona poniewaz to jedyna creatura ktora nalezy do tego samego herosa co firstAidTent
        assertEquals(creature1CurrentHP, creature1.getCurrentHp());
        assertEquals(creature2CurrentHP, creature2.getCurrentHp());

        assertNotEquals(creature3CurrentHP, creature3.getCurrentHp());

    }

}
