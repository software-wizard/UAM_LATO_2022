package pl.psi.creatures;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CatapultTest {

    @Test
    void warMachineCatapultAttacksTheWall() {
        final Random randomMock = mock(Random.class);
        when(randomMock.nextInt(anyInt())).thenReturn(5);

        final WarMachinesAbstract catapult;
        catapult = new WarMachinesFactory().create(3, 1, new DefaultDamageCalculator(randomMock), 0);
        catapult.setHeroNumber(2);

        final SpecialFieldsToAttackDecorator Wall;
        Wall = new SpecialFieldsToAttackFactory().create(1, 1, new DefaultDamageCalculator(randomMock));
        Wall.setHeroNumber(1);

        List<Creature> list = new ArrayList<Creature>();
        list.add(Wall);
        catapult.performAction(list);

        assertThat(list.get(0).getCurrentHp()).isEqualTo(11);
    }

}
