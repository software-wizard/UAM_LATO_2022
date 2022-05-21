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
        backpack = new Backpack();
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

    public void addItem(final Artifact aItem){
        backpack.addItem(aItem);
    }

    public void equipHead(Artifact aItem){
        equipment.setHead(aItem);
    }

    public void equipNeck(Artifact aItem){
        equipment.setNeck(aItem);
    }
    public void equipTorso(Artifact aItem){
        equipment.setTorso(aItem);
    }
    public void equipShoulders(Artifact aItem){
        equipment.setShoulders(aItem);
    }
    public void equipRightHand(Artifact aItem){
        equipment.setRightHand(aItem);
    }
    public void equipLeftHand(Artifact aItem){
        equipment.setLeftHand(aItem);
    }
    public void equipFeet(Artifact aItem){
        equipment.setFeet(aItem);
    }

    public enum Fraction
    {
        NECROPOLIS
    }
}
