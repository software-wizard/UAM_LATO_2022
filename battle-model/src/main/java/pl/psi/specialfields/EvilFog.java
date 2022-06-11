package pl.psi.specialfields;

import lombok.*;
import pl.psi.Point;
import pl.psi.creatures.Creature;

import java.util.List;

import static pl.psi.creatures.Alignment.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class EvilFog implements BufferIf {

    private Point point;

    /**
     * this method is responsible for buffing single morale points
     * of given creature
     * @param creature - given creature we want to buff
     */
    @Override
    public void buffCreature(Creature creature) {
        if (creature == null) {
            throw new IllegalArgumentException("Creature must not be null");
        }

        var currentMorale = creature.getMorale();
        if (creature.getAlignment().equals(EVIL)) {
            creature.setMorale(currentMorale + 1);
        } else {
            creature.setMorale(currentMorale - 1);
        }
    }

    /**
     * this method is responsible for buffing all creatures
     * from given list by 'buffCreature' method
     * @param creatures - given list of creatures we want to buff
     */
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

    public static final class Builder {
        private Point point;

        public Builder point(Point point) {
            this.point = point;
            return this;
        }

        public EvilFog build() {
            var evilFog = new EvilFog();
            evilFog.point = this.point;

            return evilFog;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
