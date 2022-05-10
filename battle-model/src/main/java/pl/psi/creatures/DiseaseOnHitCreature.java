package pl.psi.creatures;

public class DiseaseOnHitCreature extends AbstractCreature{

    private final Creature decorated;

    public DiseaseOnHitCreature(Creature aDecorated) {
        super( aDecorated );
        decorated = aDecorated;
    }

    @Override
    public void attack( final Creature aDefender )
    {
        decorated.attack( aDefender );
        disease( aDefender );
    }

    private void disease( final Creature aDefender ){
        aDefender.setAttack( aDefender.getAttack() - 2 );
        aDefender.appendAttackEffectList( 2 );
        aDefender.appendAttackEffectList( 0 );
        aDefender.appendAttackEffectList( 0 );
    }

}
