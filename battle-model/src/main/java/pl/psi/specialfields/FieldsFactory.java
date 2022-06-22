package pl.psi.specialfields;

import java.util.ArrayList;
import java.util.List;

public class FieldsFactory {
    public List<Field> createFields(DensityLevel densityLevel) {
        switch (densityLevel) {
            case LOW:
                return List.of(
                        new CrackedIce(),
                        new CloverField(),
                        new HolyGround(),
                        new EvilFog());
            case MEDIUM:
                return List.of(
                        new CrackedIce(),
                        new CloverField(),
                        new CrackedIce(),
                        new CloverField(),
                        new CrackedIce(),
                        new HolyGround(),
                        new EvilFog(),
                        new HolyGround(),
                        new EvilFog());
            case HIGH:
                return List.of(
                        new CrackedIce(),
                        new CloverField(),
                        new CloverField(),
                        new CloverField(),
                        new CloverField(),
                        new CloverField(),
                        new HolyGround(),
                        new HolyGround(),
                        new EvilFog(),
                        new EvilFog(),
                        new EvilFog());
            default:
                return new ArrayList<>();
        }
    }
}
