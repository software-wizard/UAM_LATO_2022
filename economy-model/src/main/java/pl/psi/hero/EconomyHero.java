package pl.psi.hero;

import pl.psi.artifacts.Artifact;
import pl.psi.artifacts.ArtifactPlacement;
import pl.psi.creatures.EconomyCreature;
import pl.psi.skills.EconomySkills;
import pl.psi.spells.EconomySpells;

import java.util.ArrayList;
import java.util.List;

public class EconomyHero
{

    private final Fraction fraction;
    private final List< EconomyCreature > creatureList;
    private final List<Artifact> artifactList;
    private final List<EconomySkills> skillsList;
    private final List<EconomySpells> spellsList;
    // strat amount of gold
    private int gold = 10000;

    private static int heroCounter = 0;
    private final int heroNumber;

    public EconomyHero( final Fraction aFraction)
    {
        heroCounter++;
        heroNumber = heroCounter;
        fraction = aFraction;
        creatureList = new ArrayList<>();
        artifactList = new ArrayList<>();
        skillsList = new ArrayList<>();
        spellsList = new ArrayList<>();
    }


    public int getGold()
    {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }


    public List<EconomyCreature> getCreatureList() {
        List<EconomyCreature> economyCreatureList = new ArrayList<>();
        for( EconomyCreature c: this.creatureList){
            economyCreatureList.add((EconomyCreature) c.clone());
        }
        return economyCreatureList;
    }



    // Hero cannot buy more than 7 creatures
    public void addCreature(final EconomyCreature aCreature){

        int w = 0;
        // check if we have this creature in List
        // and need only to increase amount
        for (EconomyCreature c : this.creatureList){
            if (c.getName().equals(aCreature.getName())){
                c.increaseAmount(aCreature.getAmount());
                w = 1;
            }
        }

        if( w == 0 ) {
            creatureList.add(aCreature);
        }

    }

    public void addArtifact(Artifact artifact){
        this.artifactList.add(artifact);
    }

    // check if Hero can add more Creatures - use in Shop
    public boolean canAddCreature(EconomyCreature economyCreature){

        boolean heroHasThisCreature = false;
        for(int i=0;i<this.creatureList.size();i++){
            if(economyCreature.getName().equals(creatureList.get(i).getName())){
                heroHasThisCreature = true;
            }
        }

        if(creatureList.size() == 7 && !heroHasThisCreature)
        {
            return false;
        }
        return true;
    }

    public boolean canAddArtifact(ArtifactPlacement placement){
        for(Artifact a : this.artifactList){
            if(a.getPlacement().equals(placement))
                return false;
        }
        return true;
    }



    public List< EconomyCreature > getCreatures()
    {
        List<EconomyCreature> economyCreatureList = new ArrayList<>();
        for(EconomyCreature e:creatureList){
            economyCreatureList.add((EconomyCreature) e.clone());
        }
     return economyCreatureList;

    }

    public List< Artifact > getArtifacts()
    {

        List<Artifact> artifacts = new ArrayList<>();
        for(Artifact e:artifactList){
            artifacts.add((Artifact) e.clone());
        }
      return artifacts;

    }

    public void substractGold( final int aAmount )
    {
        if( aAmount > gold )
        {
            throw new IllegalStateException( "Hero has not enought money" );
        }
        gold -= aAmount;
    }

    // potem dodać nazwy
    public enum Fraction
    {
        NECROPOLIS,
        CASTLE,
        FRACTION;
    }

    public int numberOfArtifacts(){
        return this.artifactList.size();
    }


    @Override
    public String toString() {
        return "Hero " + heroNumber ;
    }

    public int getHeroNumber(){
        return heroNumber;
    }
}
