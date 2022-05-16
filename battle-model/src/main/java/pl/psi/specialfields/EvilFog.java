package pl.psi.specialfields;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.psi.Point;
import pl.psi.creatures.Creature;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EvilFog implements BuffCreatorIf {

    public EvilFog(Point point) {
    }

    @Override
    public void putBuffOnCreature(Creature creature) {

    }

    @Override
    public void putDebuffOnCreature(Creature creature) {

    }

}
