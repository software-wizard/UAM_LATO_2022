package pl.psi.creatures;

public class AreaDamageOnAttackCreatureDecorator extends AbstractCreature{

    private Creature decorated;
    private Integer[][] area;

    public AreaDamageOnAttackCreatureDecorator(Creature aDecorated, Integer[][] aArea) {
        super(aDecorated);
        decorated = aDecorated;
        area = aArea;
    }

    @Override
    public Integer[][] getSplashDamageRange(){
        return area;
    }
}
