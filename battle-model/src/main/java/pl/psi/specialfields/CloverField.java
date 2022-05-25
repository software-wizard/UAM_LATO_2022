package pl.psi.specialfields;

import pl.psi.creatures.Alignment;
import pl.psi.creatures.Creature;

import java.util.List;

public class CloverField implements BufferIf {
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
