package pl.psi.specialfields;

import lombok.*;
import pl.psi.Point;
import pl.psi.creatures.Creature;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class HolyGround implements BuffCreatorIf {

    private Point point;

    @Override
    public void putBuffOnCreature(Creature creature) {

    }

    @Override
    public void putDebuffOnCreature(Creature creature) {

    }

}
