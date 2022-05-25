package pl.psi.specialfields;

import lombok.*;
import pl.psi.Point;
import pl.psi.creatures.Creature;

import java.util.List;

@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class FieldOfGlory implements BufferIf {

    private Point point;


    @Override
    public void buffCreature(Creature creature) {

    }

    @Override
    public void buffCreatures(List<Creature> creatures) {
        if (creatures == null) {
            throw new IllegalArgumentException("Creatures list must not be null");
        }

        if (creatures.isEmpty()) {
            throw new IllegalArgumentException("Creatures list must not be empty");
        }

        creatures.forEach(creature -> {
            creature.increaseLuckBy(- 2);
        });
    }
}
