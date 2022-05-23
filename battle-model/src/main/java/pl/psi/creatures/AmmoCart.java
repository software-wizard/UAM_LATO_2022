package pl.psi.creatures;

import java.util.List;

public class AmmoCart extends WarMachinesAbstract{
    @Override
    public void performAction(List<Creature> creatureList) {
        if (isAlive()) {
            creatureList.stream()
                    .filter(creature -> this.getHeroNumber() == creature.getHeroNumber())
                    .filter(creature -> creature instanceof ShooterCreature)
                    .map(ShooterCreature.class::cast)
                    .forEach(ShooterCreature::resetShots);
        }
    }
}
