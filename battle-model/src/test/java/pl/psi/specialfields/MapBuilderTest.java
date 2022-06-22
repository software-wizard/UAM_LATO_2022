package pl.psi.specialfields;

import org.junit.jupiter.api.Test;
import pl.psi.Point;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MapBuilderTest {
    @Test
    void shouldNotAddFieldPointPairIfItAlreadyExists() {
        // given
        final MapBuilder mapBuilder = new MapBuilder();
        final Field field = new CrackedIce();
        final Point point = new Point(0, 1);

        // when
        final List<FieldPointPair> fieldPointPairs = mapBuilder
            .addFieldPointPair(field, point)
            .addFieldPointPair(field, point)
            .addFieldPointPair(field, point)
            .build();
        final List<FieldPointPair> expectedFieldPointsPairs = List.of(new FieldPointPair(field, point));

        // then
        assertThat(fieldPointPairs.size()).isEqualTo(expectedFieldPointsPairs.size());
        assertThat(fieldPointPairs.get(0).getPoint().getX()).isEqualTo(expectedFieldPointsPairs.get(0).getPoint().getX());
        assertThat(fieldPointPairs.get(0).getPoint().getY()).isEqualTo(expectedFieldPointsPairs.get(0).getPoint().getY());
    }

    @Test
    void shouldAddFieldPointPairsProperly() {
        // given
        final MapBuilder mapBuilder = new MapBuilder();
        final Field field = new CrackedIce();
        final Point point1 = new Point(0, 1);
        final Point point2 = new Point(2, 5);
        final Point point3 = new Point(10, 12);

        // when
        final List<FieldPointPair> fieldPointPairs = mapBuilder
            .addFieldPointPair(field, point1)
            .addFieldPointPair(field, point2)
            .addFieldPointPair(field, point3)
            .build();
        final List<FieldPointPair> expectedFieldPointsPairs = List.of(
            new FieldPointPair(field, point1),
            new FieldPointPair(field, point2),
            new FieldPointPair(field, point3)
        );

        // then
        assertThat(fieldPointPairs.size()).isEqualTo(expectedFieldPointsPairs.size());
        assertThat(fieldPointPairs.get(0).getPoint().getX()).isEqualTo(expectedFieldPointsPairs.get(0).getPoint().getX());
        assertThat(fieldPointPairs.get(0).getPoint().getY()).isEqualTo(expectedFieldPointsPairs.get(0).getPoint().getY());
        assertThat(fieldPointPairs.get(1).getPoint().getX()).isEqualTo(expectedFieldPointsPairs.get(1).getPoint().getX());
        assertThat(fieldPointPairs.get(1).getPoint().getY()).isEqualTo(expectedFieldPointsPairs.get(1).getPoint().getY());
        assertThat(fieldPointPairs.get(2).getPoint().getX()).isEqualTo(expectedFieldPointsPairs.get(2).getPoint().getX());
        assertThat(fieldPointPairs.get(2).getPoint().getY()).isEqualTo(expectedFieldPointsPairs.get(2).getPoint().getY());
    }
}
