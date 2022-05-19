package pl.psi.specialfields;

import lombok.*;
import pl.psi.Point;
import pl.psi.creatures.Creature;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class HolyGround implements BuffCreatorIf {

    private Point point;

    @Override
    public void putBuffOnAllCreatures(List<Creature> creatures) {

        if (creatures == null) {
            throw new IllegalArgumentException("Creatures list must not be null");
        }

        if (creatures.isEmpty()) {
            throw new IllegalArgumentException("Creatures list must not be empty");
        }

        creatures.forEach(creature -> {
            var currentMorale = creature.getMorale();

            if (creature.getStats().isGoodAligned()) {
                creature.setMorale(currentMorale + 1);
            } else {
                creature.setMorale(currentMorale - 1);
            }
        });
    }

}
