package pl.psi.creatures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import pl.psi.TurnQueue;

import com.google.common.collect.Range;

class WarMachinesCreatureDecoratorTest {

    //Test do Maszyn :)

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
        final WarMachinesCreatureDecorator Ballista;
        Ballista = new WarMachinesFactory().create(1, 1, new DefaultDamageCalculator(randomMock));
        Ballista.attack(attacker);
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
        final WarMachinesCreatureDecorator Ballista;
        Ballista = new WarMachinesFactory().create(1, 1, new DefaultDamageCalculator(randomMock));

        attacker.attack(Ballista);
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
        final WarMachinesCreatureDecorator Ballista;
        Ballista = new WarMachinesFactory().create(1, 1, calcMock);

        attacker.attack(Ballista);
        Mockito.verify(calcMock, times(0)).calculateDamage(attacker,Ballista);
    }

}