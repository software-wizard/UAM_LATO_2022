package pl.psi.creatures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import java.util.Random;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Range;
import org.mockito.Mockito;

class WarMachinesCreatureDecoratorTest {

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
        maszyna_ballista = new WarMachinesFactory().create(1, 1, new DefaultDamageCalculator(randomMock));
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
        ballista = new WarMachinesFactory().create(1, 1, new DefaultDamageCalculator(randomMock));
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
        ballista = new WarMachinesFactory().create(1, 1, calcMock);
        attacker.attack(ballista);
        Mockito.verify(calcMock, times(0)).calculateDamage(attacker, ballista);
    }
}