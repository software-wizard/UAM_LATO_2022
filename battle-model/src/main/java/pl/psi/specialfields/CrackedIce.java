package pl.psi.specialfields;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

public class CrackedIce implements BufferIf {
    @Override
    public void buffCreature(Creature creature) {
        creature.reduceDefenseBy(5);
    }

    @Override
    public void buffCreatures(List<Creature> creatures) {
        if (!creatures.isEmpty()) {
            creatures.forEach(this::buffCreature);
        }
    }
}
