package pl.psi.creatures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Range;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.psi.TurnQueue;


class WarMachinesCreatureDecoratorTest {

    private static final int NOT_IMPORTANT = 100;
    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);

    @Mock
    Random random;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void warMachineBallistaAttacksTheCreature() {
        final Random randomMock = mock(Random.class);
        when(randomMock.nextInt(anyInt())).thenReturn(5);

        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .armor(5)
                        .build())
                .build();

        final WarMachinesAbstract maszyna_ballista;
        maszyna_ballista = new WarMachinesFactory().create(1, 1, new DefaultDamageCalculator(randomMock), 0);
        maszyna_ballista.performAction(attacker);
        assertThat(attacker.getCurrentHp()).isEqualTo(84);

    }

    @Test
    void warMachineDoesNotCounterAttack() {
        final Random randomMock = mock(Random.class);
        when(randomMock.nextInt(anyInt())).thenReturn(5);

        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .armor(5)
                        .build())
                .build();
        final WarMachinesAbstract ballista;
        ballista = new WarMachinesFactory().create(1, 1, new DefaultDamageCalculator(randomMock), 0);
        attacker.attack(ballista);
        System.out.println(ballista.getCounterAttackCounter());
        assertThat(attacker.getCurrentHp()).isEqualTo(100);
    }

    @Test
    void howManyIterationsOfCalculator() {

        final Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .armor(5)
                        .build())
                .build();
        final DefaultDamageCalculator calcMock = mock(DefaultDamageCalculator.class);
        when(calcMock.getArmor(attacker)).thenReturn(2);
        final WarMachinesAbstract ballista;
        ballista = new WarMachinesFactory().create(1, 1, calcMock, 0);
        attacker.attack(ballista);
        Mockito.verify(calcMock, times(0)).calculateDamage(attacker, ballista);
    }

    @Test
    void warMachineCatapultAttacksTheWall() {
        final Random randomMock = mock(Random.class);
        when(randomMock.nextInt(anyInt())).thenReturn(0);

        final WarMachinesAbstract catapult;
        catapult = new WarMachinesFactory().create(3, 1, new DefaultDamageCalculator(randomMock), 0);

        final SpecialFieldsToAttackDecorator Wall;
        Wall = new SpecialFieldsToAttackFactory().create(1, 1, new DefaultDamageCalculator(randomMock));

        catapult.performAction(Wall);
        assertThat(Wall.getCurrentHp()).isEqualTo(19);
    }
}
    /*@Test
    void firstAidTentHealsRandomCreature() {
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(7);

        final Creature creature1 = new Creature.Builder().statistic(CreatureStats.builder()
                        .name("creature1")
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .armor(NOT_IMPORTANT)
                        .build())
                .build();
        creature1.setCurrentHp(90);
        final Creature creature2 = new Creature.Builder().statistic(CreatureStats.builder()
                        .name("creature2")
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .armor(NOT_IMPORTANT)
                        .build())
                .build();
        creature2.setCurrentHp(80);
        final Creature creature3 = new Creature.Builder().statistic(CreatureStats.builder()
                        .name("creature3")
                        .maxHp(100)
                        .damage(NOT_IMPORTANT_DMG)
                        .armor(NOT_IMPORTANT)
                        .build())
                .build();
        creature3.setCurrentHp(20);

        final WarMachinesAbstract firstAidTent;
        firstAidTent = new WarMachinesFactory().create(2, 1, null, 1);

        final TurnQueue turnQueue = new TurnQueue( List.of( creature1, creature2, creature3 ), List.of( firstAidTent ) );
        Creature randomCreature = turnQueue.getRandomCreature();
        firstAidTent.performAction(randomCreature);



    }
    }
     */
