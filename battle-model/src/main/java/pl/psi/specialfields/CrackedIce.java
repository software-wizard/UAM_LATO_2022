package pl.psi.specialfields;

import lombok.ToString;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

@ToString
public class CrackedIce extends Field implements DebufferIf {

    public CrackedIce() {
        super("/images/cracked_ice.png");
    }

    public CrackedIce(String imagePath) {
        super(imagePath);
    }

    @Override
    public void debuffCreature(Creature creature) {
        creature.reduceDefenseBy(5);
    }

    @Override
    public void debuffCreatures(List<Creature> creatures) {
        if (!creatures.isEmpty()) {
            creatures.forEach(this::debuffCreature);
        }
    }

    @Override
    public void handleEffect(List<Creature> creatures) {
        debuffCreatures(creatures);
    }
}
