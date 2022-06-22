package pl.psi.specialfields;

import lombok.ToString;
import pl.psi.Point;

import java.util.ArrayList;
import java.util.List;

@ToString
public class MapBuilder {
    private final List<FieldPointPair> fieldPointPairs = new ArrayList<>();

    private boolean isPointTaken(Point point) {
        return fieldPointPairs.stream().anyMatch(fieldPointPair ->
            fieldPointPair.getPoint().getX() == point.getX() && fieldPointPair.getPoint().getY() == point.getY());
    }

    public MapBuilder addFieldPointPair(Field field, Point point) {
        if (!isPointTaken(point)) {
            fieldPointPairs.add(new FieldPointPair(field, point));
        }

        return this;
    }

    public List<FieldPointPair> build() {
        return fieldPointPairs;
    }
}
