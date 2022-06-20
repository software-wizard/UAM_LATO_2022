package pl.psi.specialfields;

import java.util.ArrayList;
import java.util.List;

public class FieldsFactory {
    public List<Field> createFields(DensityLevel densityLevel) {
        switch (densityLevel) {
            case LOW:
                return List.of(
                    new CrackedIce(),
                    new CloverField());
            case MEDIUM:
                return List.of(
                    new CrackedIce(),
                    new CloverField(),
                    new CrackedIce(),
                    new CloverField(),
                    new CrackedIce());
            case HIGH:
                return List.of(
                    new CrackedIce(),
                    new CloverField(),
                    new CloverField(),
                    new CloverField(),
                    new CloverField(),
                    new CloverField());
            default:
                return new ArrayList<>();
        }
    }
}
