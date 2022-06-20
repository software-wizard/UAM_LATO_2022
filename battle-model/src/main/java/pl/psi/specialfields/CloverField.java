package pl.psi.specialfields;

import lombok.ToString;
import pl.psi.creatures.Alignment;
import pl.psi.creatures.Creature;

import java.util.List;

@ToString
public class CloverField extends Field implements BufferIf {
    public CloverField() {
        super("/images/clover_field.png");
    }

    public CloverField(String imagePath) {
        super(imagePath);
    }

    @Override
    public void handleEffect(Creature creature) {
        buffCreature(creature);
    }

    @Override
    public void buffCreature(Creature creature) {
        if (creature.getAlignment() == Alignment.NEUTRAL) {
            creature.increaseLuckBy(2);
        }
    }

    @Override
    public void buffCreatures(List<Creature> creatures) {
        if (!creatures.isEmpty()) {
            creatures.forEach(this::buffCreature);
        }
    }
}
