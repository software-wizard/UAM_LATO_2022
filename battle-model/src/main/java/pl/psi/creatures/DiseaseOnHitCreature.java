package pl.psi.creatures;

import java.util.Random;

public class DiseaseOnHitCreature extends AbstractCreature{

    private final Creature decorated;

    public DiseaseOnHitCreature(Creature aDecorated) {
        super( aDecorated );
        decorated = aDecorated;
    }

    @Override
    public void attack( final Creature aDefender ){
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if(chance <= 20){
            attackWithDisease( aDefender );
        }
        else{
            decorated.attack( aDefender );
        }
    }

    private void disease(final Creature aDefender){
        CreatureStats statsChange = new CreatureStats
                .CreatureStatsBuilder()
                    .armor( -2 )
                    .attack( -2 )
                .build();
        aDefender.updateStats( statsChange );
    }

    public void attackWithDisease( final Creature aDefender ){
        decorated.attack( aDefender );
        disease( aDefender );
    }
}
