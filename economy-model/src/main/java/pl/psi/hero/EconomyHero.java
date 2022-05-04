package pl.psi.hero;

import pl.psi.products.artifacts.ArtifactPlacement;
import pl.psi.products.artifacts.EconomyArtifact;
import pl.psi.products.creatures.EconomyCreature;
import pl.psi.products.skills.EconomySkills;
import pl.psi.products.spells.EconomySpells;

import java.util.ArrayList;
import java.util.List;



public class EconomyHero
{

    private final Fraction fraction;
    private final List<EconomyCreature> creatureList;
    private final List<EconomyArtifact> artifactList;
    private final List<EconomySkills> skillsList;
    private final List<EconomySpells> spellsList;
    private int gold = 4000;
    private int numberOfCreatures;
    private HeroCanBuyStatistics buyStatistics;

    private static int heroCounter = 0;
    private final int heroNumber;

    public EconomyHero( final Fraction aFraction)
    {
        buyStatistics = new HeroCanBuyStatistics(0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        numberOfCreatures = 0;
        heroCounter++;
        heroNumber = heroCounter;
        fraction = aFraction;
        creatureList = new ArrayList<>();
        artifactList = new ArrayList<>();
        skillsList = new ArrayList<>();
        spellsList = new ArrayList<>();
    }

    public HeroCanBuyStatistics getStatistics() {
        return buyStatistics;
    }

    public int getGold()
    {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getNumberOfCreatures()
    {
        return numberOfCreatures;
    }

    public List<EconomyCreature> getCreatureList() {
        return creatureList;
    }


    public List<EconomyArtifact> getArtifactList() {
        return artifactList;
    }

    public List<EconomySkills> getSkillsList() {
        return skillsList;
    }

    public List<EconomySpells> getSpellsList() {
        return spellsList;
    }

    void addCreature( final EconomyCreature aCreature ){

        int w = 0;

        for (EconomyCreature c : this.creatureList){
            if (c.getName().equals(aCreature.getName())){
                c.increaseAmount(aCreature.getAmount());
                numberOfCreatures = numberOfCreatures + aCreature.getAmount();
                buyStatistics.addLimitForAmountOfCreatures(aCreature.getAmount());
                w = 1;
            }
        }

        if( w == 0 ) {
            creatureList.add(aCreature);
            numberOfCreatures = numberOfCreatures + aCreature.getAmount();
            buyStatistics.addLimitForAmountOfCreatures(aCreature.getAmount());
        }

    }


    void addArtifact (final EconomyArtifact artifact){
        artifactList.add(artifact);
        if(artifact.getArtifact().getPlacement().equals(ArtifactPlacement.HEAD)){
            buyStatistics.setBoughtHead(buyStatistics.getBoughtHead() + artifact.getAmount());
        }
    }

    boolean canAddCreature(EconomyCreature creature){

        if( creatureList.size() == 7 )
        {
            for(EconomyCreature c:this.creatureList){
                if(c.getName().equals(creature.getName())){
                    return true;
                }
            }
            return false;
        }

        return true;
    }

    boolean canAddArtifact(ArtifactPlacement placement,int amount){
        if(placement.equals(ArtifactPlacement.HEAD)) {
            if (amount <= (buyStatistics.getLimitHead() - buyStatistics.getBoughtHead()))
                return true;
            else
                return false;
        }
        return false;
    }

    public List< EconomyCreature > getCreatures()
    {
        return List.copyOf( creatureList );
    }

    void substractGold( final int aAmount )
    {
        if( aAmount > gold )
        {
            throw new IllegalStateException( "Hero has not enought money" );
        }
        gold -= aAmount;
    }

    public enum Fraction
    {
        NECROPOLIS,
        CASTLE,
        FRACTION;
    }


    @Override
    public String toString() {
        return "Hero " + heroNumber ;
    }
}
