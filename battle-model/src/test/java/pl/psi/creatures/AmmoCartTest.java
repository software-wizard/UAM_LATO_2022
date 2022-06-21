package pl.psi.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.TurnQueue;

import java.beans.PropertyChangeEvent;
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
        List<Creature> creaturesList1 = new ArrayList();
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

        final AbstractWarMachines ammoCart;
        ammoCart = new WarMachinesFactory().create(4, 1, null, 0);
        ammoCart.setHeroNumber(1);

        creaturesList.add(shooterCreature);
        creaturesList1.add(defender);
        creaturesList.add(ammoCart);

        //when
        shooterCreature.attack(defender);
        PropertyChangeEvent evt = new PropertyChangeEvent(TurnQueue.class, TurnQueue.NEW_TURN,creaturesList1, creaturesList );
        ammoCart.propertyChange(evt);

        //then
        assertEquals(shooterCreature.getMaxShots(), shooterCreature.getShots());
    }

    @Test
    public void shouldNotResetAmmoToUnFriendlyUnits() {
        //given
        List<Creature> creaturesList = new ArrayList();
        List<Creature> creaturesList1 = new ArrayList();
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

        final AbstractWarMachines ammoCart;
        ammoCart = new WarMachinesFactory().create(4, 1, null, 0);
        ammoCart.setHeroNumber(2);

        creaturesList.add(shooterCreature);
        creaturesList1.add(defender);
        creaturesList.add(ammoCart);

        //when
        shooterCreature.attack(defender);
        PropertyChangeEvent evt = new PropertyChangeEvent(TurnQueue.class, TurnQueue.NEW_TURN,creaturesList1, creaturesList );
        ammoCart.propertyChange(evt);

        //then
        assertNotEquals(shooterCreature.getMaxShots(), shooterCreature.getShotsAmount());
    }

}