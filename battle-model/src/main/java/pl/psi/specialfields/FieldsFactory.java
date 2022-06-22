package pl.psi.specialfields;

import pl.psi.Point;

import java.util.ArrayList;
import java.util.List;

public class FieldsFactory {
    public List<FieldPointPair> createFields(DensityLevel densityLevel) {
        switch (densityLevel) {
            case LOW:
                return new MapBuilder()
                    .addFieldPointPair(new CrackedIce(), new Point(2, 2))
                    .addFieldPointPair(new CloverField(), new Point(4, 2))
                    .addFieldPointPair(new HolyGround(), new Point(10, 7))
                    .addFieldPointPair(new EvilFog(), new Point(3, 12))
                    .build();
            case MEDIUM:
                return new MapBuilder()
                    .addFieldPointPair(new CrackedIce(), new Point(2, 2))
                    .addFieldPointPair(new CloverField(), new Point(4, 2))
                    .addFieldPointPair(new CrackedIce(), new Point(10, 7))
                    .addFieldPointPair(new CloverField(), new Point(3, 12))
                    .addFieldPointPair(new CrackedIce(), new Point(9, 6))
                    .addFieldPointPair(new HolyGround(), new Point(11, 9))
                    .addFieldPointPair(new EvilFog(), new Point(3, 5))
                    .addFieldPointPair(new HolyGround(), new Point(5, 4))
                    .addFieldPointPair(new EvilFog(), new Point(3, 5))
                    .build();
            case HIGH:
                return new MapBuilder()
                    .addFieldPointPair(new CrackedIce(), new Point(2, 2))
                    .addFieldPointPair(new CloverField(), new Point(4, 2))
                    .addFieldPointPair(new CrackedIce(), new Point(10, 7))
                    .addFieldPointPair(new CloverField(), new Point(3, 12))
                    .addFieldPointPair(new CrackedIce(), new Point(9, 6))
                    .addFieldPointPair(new HolyGround(), new Point(11, 9))
                    .addFieldPointPair(new EvilFog(), new Point(3, 5))
                    .addFieldPointPair(new HolyGround(), new Point(5, 4))
                    .addFieldPointPair(new EvilFog(), new Point(3, 5))
                    .addFieldPointPair(new CloverField(), new Point(2, 4))
                    .addFieldPointPair(new CrackedIce(), new Point(13, 3))
                    .addFieldPointPair(new EvilFog(), new Point(12, 10))
                    .addFieldPointPair(new HolyGround(), new Point(8, 5))
                    .build();
            default:
                return new ArrayList<>();
        }
    }
}
