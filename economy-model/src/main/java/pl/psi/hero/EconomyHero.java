package pl.psi.hero;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import lombok.Getter;
import pl.psi.creatures.EconomyCreature;

@Getter
public class EconomyHero
{

    private final Fraction fraction;
    private final List< EconomyCreature > creatureList;
    private int gold;
    private final Equipment equipment;
    private final Backpack backpack;
    private final HeroStatistics heroClass;

    public EconomyHero( final Fraction aFraction, final int aGold, final HeroStatistics aClass )
    {
        fraction = aFraction;
        gold = aGold;
        creatureList = new ArrayList<>();
        equipment = new Equipment();
        backpack = equipment.getBackpack();
        heroClass = aClass;
    }

    void addCreature( final EconomyCreature aCreature )
    {
        if( creatureList.size() >= 7 )
        {
            throw new IllegalStateException( "Hero has not empty slot for creature" );
        }
        creatureList.add( aCreature );
    }


    public void addGold( final int aAmount )
    {
        gold += aAmount;
    }


    void substractGold( final int aAmount )
    {
        if( aAmount > gold )
        {
            throw new IllegalStateException( "Hero has not enought money" );
        }
        gold -= aAmount;
    }

    public void equipArtifact(final Artifact aArtifact){
        equipment.equipArtifact(aArtifact);
    }

    public void addArtifactToBackpack(final Artifact aArtifact){ backpack.addArtifact(aArtifact);}

    public enum Fraction
    {
        NECROPOLIS
    }
}
