package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import pl.psi.specialfields.FieldPointPair;

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
        final List<FieldPointPair> f = List.of();
        final Board board = new Board(c1, c2, f);

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
        final List<FieldPointPair> f1 = List.of();
        final Board board = new Board(c1, c2, f1);

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
        final List<FieldPointPair> f1 = List.of();
        final Board board = new Board(c1, c2, f1);

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

    @Test
    void pathFinderTest(){
        final List<Creature> c1 = List.of();
        final List<Creature> c2 = List.of();
        final List<FieldPointPair> f1 = List.of();
        final Board board = new Board(c1, c2, f1);

        final Point startPoint = new Point(1,2);
        final Point endPoint = new Point(5,0);
        final List<Point> path = new ArrayList<>();
        path.add(new Point(2, 1));
        path.add(new Point(3, 0));
        path.add(new Point(4, 0));
        path.add(new Point(5, 0));

        assertThat(board.getPathToPoint(startPoint,endPoint)).isEqualTo(path);  //"path" is path from 1,2 to 5,0
    }

}