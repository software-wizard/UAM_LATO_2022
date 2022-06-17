package pl.psi.hero;

import lombok.Getter;
import pl.psi.artifacts.EconomyArtifact;
import pl.psi.artifacts.ArtifactPlacement;
import pl.psi.creatures.EconomyCreature;
import pl.psi.shop.Money;
import pl.psi.skills.EconomySkill;
import pl.psi.spells.EconomySpell;

import java.util.ArrayList;
import java.util.List;

@Getter
// TODO - zamiast Artifact - Slots
// TODO - Economy Engine decide if we have slots and can Buy
public class EconomyHero {

    private static int heroCounter = 0;
    private final Fraction fraction;
    private final List<EconomyCreature> creatureList;
    private final List<EconomyArtifact> artifactList;
    private final List<EconomySkill> skillsList;
    private final List<EconomySpell> spellsList;
    private final Equipment equipment;
    private final Backpack backpack;
    private final HeroStatistics heroStats;
    private final int heroNumber;
    // start amount of gold
    // TODO - MOney zamiast int !!
    private Money gold = new Money(10000);

    public EconomyHero(final Fraction aFraction) {
        this(aFraction, HeroStatistics.NECROMANCER);
    }

    public EconomyHero(final Fraction aFraction, final HeroStatistics aClass) {
        heroCounter++;
        heroNumber = heroCounter;
        fraction = aFraction;
        creatureList = new ArrayList<>();
        artifactList = new ArrayList<>();
        skillsList = new ArrayList<>();
        spellsList = new ArrayList<>();
        equipment = new Equipment();
        backpack = new Backpack();
        heroStats = aClass;
    }

    // konstruktor samokopiujący
    public EconomyHero(Fraction fraction, List<EconomyCreature> creatureList, List<EconomyArtifact> artifactList, List<EconomySkill> skillsList, List<EconomySpell> spellsList, Money gold, int heroNumber, HeroStatistics aClass) {
        this.fraction = fraction;
        this.creatureList = creatureList;
        this.artifactList = artifactList;
        this.skillsList = skillsList;
        this.spellsList = spellsList;
        this.gold = gold;
        this.heroNumber = heroNumber;
        // TODO copy of backpack and equipment
        equipment = new Equipment();
        backpack = new Backpack();
        heroStats = aClass;
    }

    public List<EconomyCreature> getCreatures() {
        List<EconomyCreature> economyCreatureList = new ArrayList<>();
        for (EconomyCreature c : this.creatureList) {
            economyCreatureList.add(new EconomyCreature(c.getStats(), c.getAmount(), c.getGoldCost()));
        }
        return economyCreatureList;
    }

    // Hero cannot buy more than 7 creatures
    public void addCreature(final EconomyCreature aCreature) {

        int w = 0;
        // check if we have this creature in List
        // and need only to increase amount
        for (EconomyCreature c : this.creatureList) {
            if (c.getName().equals(aCreature.getName())) {
                c.increaseAmount(aCreature.getAmount());
                w = 1;
            }
        }
        if (w == 0) {
            creatureList.add(aCreature);
        }

    }

    public void addArtifact(EconomyArtifact artifact) {
        this.artifactList.add(artifact);
    }

    public boolean canAddCreature(EconomyCreature economyCreature) {

        boolean heroHasThisCreature = false;
        for (int i = 0; i < this.creatureList.size(); i++) {
            if (economyCreature.getName().equals(creatureList.get(i).getName())) {
                heroHasThisCreature = true;
            }
        }

        if (creatureList.size() == 7 && !heroHasThisCreature) {
            return false;
        }

        return true;
    }

    public boolean canAddArtifact(ArtifactPlacement placement) {
        for (EconomyArtifact a : this.artifactList) {
            if (a.getPlacement().equals(placement))
                return false;
        }
        return true;
    }

    public List<EconomyArtifact> getArtifacts() {
        return List.copyOf(artifactList);
    }

    public List<EconomySkill> getSkills() {
        return List.copyOf(skillsList);
    }

    public List<EconomySpell> getSpells() {
        return List.copyOf(spellsList);
    }

    public void substractGold(final int aAmount) {
        this.gold = new Money(gold.getPrice() - aAmount);
    }

    public void addItem(final EconomyArtifact aItem) {
        backpack.addItem(aItem);
    }

    public void equipHead(EconomyArtifact aItem) {
        equipment.setHead(aItem);
    }

    public void equipNeck(EconomyArtifact aItem) {
        equipment.setNeck(aItem);
    }

    public void equipTorso(EconomyArtifact aItem) {
        equipment.setTorso(aItem);
    }

    public void equipShoulders(EconomyArtifact aItem) {
        equipment.setShoulders(aItem);
    }

    public void equipRightHand(EconomyArtifact aItem) {
        equipment.setRightHand(aItem);
    }

    public void equipLeftHand(EconomyArtifact aItem) {
        equipment.setLeftHand(aItem);
    }

    public void equipFeet(EconomyArtifact aItem) {
        equipment.setFeet(aItem);
    }

    public Fraction getFraction() {
        return fraction;
    }

    @Override
    public String toString() {
        return "Hero " + heroNumber;
    }

    public int getHeroNumber() {
        return heroNumber;
    }

    public boolean canAddSkill(EconomySkill economySkill) {
        for(EconomySkill s: skillsList){
            if(s.getSkillType().equals(economySkill.getSkillType()))
                return false;
        }
        return true;
    }

    public void addSkill(EconomySkill economySkill) {
        this.skillsList.add(economySkill);
    }

    public boolean canAddSpell(EconomySpell economySpell){

        for(EconomySpell spell:spellsList){
            if(spell.getSpellStats().equals(economySpell.getSpellStats()))
                return false;
        }

        return true;
    }

    public void addSpell(EconomySpell spell){
        this.spellsList.add(spell);
    }



    public enum Fraction {
        NECROPOLIS,
        CASTLE,
        STRONGHOLD
    }

    public List<EconomySpell> getSpellList() {
        return spellsList;
    }

    public int getSpellPower() {
        return heroStats.getSpellPower();
    }
}