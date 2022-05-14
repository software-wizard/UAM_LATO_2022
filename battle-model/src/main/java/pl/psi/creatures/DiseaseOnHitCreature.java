package pl.psi.creatures;

public class DiseaseOnHitCreature extends AbstractCreature{

    private final Creature decorated;

    public DiseaseOnHitCreature(Creature aDecorated) {
        super( aDecorated );
        decorated = aDecorated;
    }

    @Override
    public void attack( final Creature aDefender ){
        decorated.attack( aDefender );
        disease( aDefender );
    }

    private void disease(final Creature aDefender){
        CreatureStats statsChange = new CreatureStats
                .CreatureStatsBuilder()
                    .armor( -2 )
                    .attack( -2 )
                .build();
        aDefender.updateStats( statsChange );

    }
}
