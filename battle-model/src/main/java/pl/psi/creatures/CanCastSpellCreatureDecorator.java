package pl.psi.creatures;

import pl.psi.spells.SpellRang;

public class CanCastSpellCreatureDecorator extends AbstractCreature{

    private Creature decorated;
    private final String spellName;
    private int castCounter;
    private final int spellPower;
    private final SpellRang spellRang;

    public CanCastSpellCreatureDecorator(Creature aDecorated, String aSpellName, int aCastCounter, int aSpellPower,
                                         SpellRang aSpellRang) {
        super(aDecorated);
        decorated = aDecorated;
        spellName = aSpellName;
        castCounter = aCastCounter;
        spellPower = aSpellPower;
        spellRang = aSpellRang;
    }

    @Override
    public boolean canCastSpell(){
        return castCounter > 0;
    }

    @Override
    public int getSpellCastCounter(){
        return castCounter;
    }

    @Override
    public void reduceNumberOfSpellCasts(){
        castCounter -= 1;
        System.out.println(castCounter);
    }

    @Override
    public String getSpellName(){
        return spellName;
    }

    @Override
    public int getSpellPower(){
        return spellPower;
    }

    @Override
    public SpellRang getSpellRang(){
        return spellRang;
    }

}
