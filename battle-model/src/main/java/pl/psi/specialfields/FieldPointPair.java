package pl.psi.specialfields;

import lombok.Getter;
import lombok.ToString;
import pl.psi.Point;

@Getter
@ToString
public class FieldPointPair {
    private final Field field;
    private final Point point;

    public FieldPointPair(Field aField, Point aPoint) {
        field = aField;
        point = aPoint;
    }
}
