package pl.psi.creatures;

import java.util.Random;

public class DiseaseOnHitCreatureDecorator extends AbstractCreature {

    private final Creature decorated;

    public DiseaseOnHitCreatureDecorator(Creature aDecorated) {
        super(aDecorated);
        decorated = aDecorated;
    }

    @Override
    public void attack(final Creature aDefender) {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if (chance <= 20) {
            attackWithDisease(aDefender);
        } else {
            decorated.attack(aDefender);
        }
    }

    private void disease(final Creature aDefender) {
        CreatureStats statsChange = new CreatureStats
                .CreatureStatsBuilder()
                .armor(-2)
                .attack(-2)
                .build();
        aDefender.buff(statsChange);
    }

    public void attackWithDisease(final Creature aDefender) {
        decorated.attack(aDefender);
        disease(aDefender);
    }
}
