package pl.psi.specialfields;

import lombok.ToString;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

@ToString
public class CrackedIce extends Field implements BufferIf {

    public CrackedIce() {
        super("/images/cracked_ice.png");
    }

    public CrackedIce(String imagePath) {
        super(imagePath);
    }

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

    @Override
    public void handleEffect(Creature creature) {
        buffCreature(creature);
    }
}
