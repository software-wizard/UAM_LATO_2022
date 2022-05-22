package pl.psi.specialfields;

import pl.psi.creatures.Creature;

import java.util.List;

public interface BufferIf {
    void buffCreature(Creature creature);
    void buffCreatures(List<Creature> creatures);
}
