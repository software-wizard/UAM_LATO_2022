package pl.psi.specialfields;

import lombok.*;
import pl.psi.Point;
import pl.psi.creatures.Alignment;
import pl.psi.creatures.Creature;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class HolyGround implements BufferIf {

    private Point point;

    @Override
    public void buffCreature(Creature creature) {
        if (creature == null) {
            throw new IllegalArgumentException("Creature must not be null");
        }

        var currentMorale = creature.getMorale();
        if (creature.getAlignment().equals(Alignment.GOOD)) {
            creature.setMorale(currentMorale + 1);
        } else {
            creature.setMorale(currentMorale - 1);
        }
    }

    @Override
    public void buffCreatures(List<Creature> creatures) {
        if (creatures == null) {
            throw new IllegalArgumentException("Creatures list must not be null");
        }

        if (creatures.isEmpty()) {
            throw new IllegalArgumentException("Creatures list must not be empty");
        }

        creatures.forEach(this::buffCreature);
    }

}
