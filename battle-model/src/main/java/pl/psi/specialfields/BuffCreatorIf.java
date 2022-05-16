package pl.psi.specialfields;

import pl.psi.creatures.Creature;

public interface BuffCreatorIf {

    void putBuffOnCreature(Creature creature);
    void putDebuffOnCreature(Creature creature);
}
