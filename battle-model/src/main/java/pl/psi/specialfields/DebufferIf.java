package pl.psi.specialfields;

import pl.psi.creatures.Creature;

import java.util.List;

public interface DebufferIf {
    void debuffCreature(Creature creature);
    void debuffCreatures(List<Creature> creatures);
}
