package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class BoardTest {
    @Test
    void unitsMoveProperly() {
        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
                .moveRange(5)
                .build())
                .build();
        final List<Creature> c1 = List.of(creature);
        final List<Creature> c2 = List.of();
        final Board board = new Board(c1, c2);

        board.move(creature, new Point(3, 3));

        assertThat(board.getCreature(new Point(3, 3))
                .isPresent()).isTrue();
    }

    @Test
    void boardShouldReturnCreaturePosition() {
        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
                .moveRange(5)
                .build())
                .build();
        final List<Creature> c1 = List.of(creature);
        final List<Creature> c2 = List.of();
        final Board board = new Board(c1, c2);

        assertThat( board.getCreaturePosition( creature ) ).isEqualTo( new Point(0,1) );
    }

    @Test
    void boardShouldReturnProperAdjacentPoints() {
        final Creature creature = new Creature.Builder().statistic(CreatureStats.builder()
                .moveRange(5)
                .build())
                .build();
        final List<Creature> c1 = List.of(creature);
        final List<Creature> c2 = List.of();
        final Board board = new Board(c1, c2);

        List<Point> pointList = board.getAdjacentPositions( new Point( 1,1 ) );

        assertThat(pointList.get(0)).isEqualTo( new Point(0,2) );
        assertThat(pointList.get(1)).isEqualTo( new Point(1,2) );
        assertThat(pointList.get(2)).isEqualTo( new Point(2,2) );
        assertThat(pointList.get(3)).isEqualTo( new Point(0,1) );
        assertThat(pointList.get(4)).isEqualTo( new Point(2,1) );
        assertThat(pointList.get(5)).isEqualTo( new Point(0,0) );
        assertThat(pointList.get(6)).isEqualTo( new Point(1,0) );
        assertThat(pointList.get(7)).isEqualTo( new Point(2,0) );
    }

}