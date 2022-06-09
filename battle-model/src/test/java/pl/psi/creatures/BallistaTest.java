package pl.psi.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class BallistaTest {

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

        final Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                .maxHp(100)
                .damage(Range.closed(10, 10))
                .armor(5)
                .build())
                .build();
        defender.setHeroNumber(1);

        final WarMachinesAbstract maszyna_ballista;
        maszyna_ballista = new WarMachinesFactory().create(1, 1, new DefaultDamageCalculator(randomMock), 0);
        maszyna_ballista.setHeroNumber(2);
        List<Creature> list = new ArrayList<Creature>();
        list.add(defender);
        maszyna_ballista.performAction(list);
        assertThat(list.get(0).getCurrentHp()).isEqualTo(84);
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
        attacker.setHeroNumber(1);

        final WarMachinesAbstract ballista;
        ballista = new WarMachinesFactory().create(1, 1, new DefaultDamageCalculator(randomMock), 0);
        ballista.setHeroNumber(2);
        attacker.attack(ballista);

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
        when(calcMock.getArmor(attacker)).thenReturn(2.0);
        final WarMachinesAbstract ballista;
        ballista = new WarMachinesFactory().create(1, 1, calcMock, 0);
        attacker.attack(ballista);
        Mockito.verify(calcMock, times(0)).calculateDamage(attacker, ballista);
    }
}
