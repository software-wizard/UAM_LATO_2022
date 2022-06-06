package pl.psi.creatures;

public class AreaDamageOnAttackCreature extends AbstractCreature{

    private Creature decorated;
    private Integer[][] area;

    public AreaDamageOnAttackCreature(Creature aDecorated, Integer[][] aArea) {
        super(aDecorated);
        decorated = aDecorated;
        area = aArea;
    }

    @Override
    public Integer[][] getSplashDamageRange(){
        return area;
    }
}
