package pl.psi.specialfields;

import pl.psi.creatures.Creature;

import java.util.List;

public class CrackedIce implements BufferIf {
    @Override
    public void buffCreature(Creature creature) {

    }

    @Override
    public void buffCreatures(List<Creature> creatures) {
        if (!creatures.isEmpty()) {
            creatures.forEach(this::buffCreature);
        }
    }
}
