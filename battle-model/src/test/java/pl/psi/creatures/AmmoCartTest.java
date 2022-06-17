package pl.psi.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AmmoCartTest {

    private static final int NOT_IMPORTANT = 100;
    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);

    @Test
    public void shouldResetAmmoToFriendlyUnits() {
        //given
        List<Creature> creaturesList = new ArrayList();
        final Creature creature1 = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(NOT_IMPORTANT_DMG)
                .armor(NOT_IMPORTANT)
                .build())
                .build();
        creature1.setCurrentHp(NOT_IMPORTANT);
        ShooterCreatureDecorator shooterCreature = new ShooterCreatureDecorator(creature1, 10);
        shooterCreature.setHeroNumber(1);

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(NOT_IMPORTANT_DMG)
                .armor(NOT_IMPORTANT)
                .build())
                .build();
        defender.setHeroNumber(2);

        final WarMachinesAbstract firstAidTent;
        firstAidTent = new WarMachinesFactory().create(4, 1, null, 0);
        firstAidTent.setHeroNumber(1);

        creaturesList.add(shooterCreature);
        creaturesList.add(defender);
        creaturesList.add(firstAidTent);

        //when
        shooterCreature.attack(defender);
        firstAidTent.performAction(creaturesList);

        //then
        assertEquals(shooterCreature.getMaxShots(), shooterCreature.getShots());
    }

    @Test
    public void shouldNotResetAmmoToUnFriendlyUnits() {
        //given
        List<Creature> creaturesList = new ArrayList();
        final Creature creature1 = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(NOT_IMPORTANT_DMG)
                .armor(NOT_IMPORTANT)
                .build())
                .build();
        creature1.setCurrentHp(20);
        ShooterCreatureDecorator shooterCreature = new ShooterCreatureDecorator(creature1, 10);
        shooterCreature.setHeroNumber(1);

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(NOT_IMPORTANT)
                .damage(NOT_IMPORTANT_DMG)
                .armor(NOT_IMPORTANT)
                .build())
                .build();
        defender.setHeroNumber(2);

        final WarMachinesAbstract firstAidTent;
        firstAidTent = new WarMachinesFactory().create(4, 1, null, 0);
        firstAidTent.setHeroNumber(2);

        creaturesList.add(shooterCreature);
        creaturesList.add(defender);
        creaturesList.add(firstAidTent);

        //when
        shooterCreature.attack(defender);
        firstAidTent.performAction(creaturesList);

        //then
        assertNotEquals(shooterCreature.getMaxShots(), shooterCreature.getShots());
    }

}