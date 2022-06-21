package pl.psi.hero;

import lombok.Getter;
import pl.psi.artifacts.Artifact;
import pl.psi.artifacts.ArtifactPlacement;
import pl.psi.creatures.EconomyCreature;
import pl.psi.skills.EconomySkills;
import pl.psi.spells.EconomySpell;

import java.util.ArrayList;
import java.util.List;

public class EconomyHero implements ArtifactEffectApplicable {

    private static int heroCounter = 0;
    private final Fraction fraction;
    private final List<EconomyCreature> creatureList;
    private final List<EconomyArtifact> artifactList;
    private final List<EconomySkill> skillsList;
    private final List<EconomySpell> spellsList;
    private final List<EconomyCreature> warMachines;
    private final Equipment equipment;
    private final Backpack backpack;
    private HeroStatisticsIf heroStats;
    private final int heroNumber;
    private final ArtifactApplier artifactApplier = new ArtifactApplier();
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
        warMachines = new ArrayList<>();
        equipment = new Equipment();
        backpack = equipment.getBackpack();
        heroStats = aClass;
    }

    // konstruktor samokopiujący
    public EconomyHero(Fraction fraction, List<EconomyCreature> creatureList, List<EconomyArtifact> artifactList, List<EconomySkill> skillsList, List<EconomySpell> spellsList, List<EconomyCreature> warMachines ,Money gold, int heroNumber, HeroStatisticsIf aClass) {
        this.fraction = fraction;
        this.creatureList = creatureList;
        this.artifactList = artifactList;
        this.skillsList = skillsList;
        this.spellsList = spellsList;
        this.gold = gold;
        this.heroNumber = heroNumber;
        this.warMachines = warMachines;
        equipment = new Equipment();
        backpack = equipment.getBackpack();
        heroStats = HeroStatistics.NECROMANCER;
    }

    public List<EconomyCreature> getCreatureList() {
        return List.copyOf(creatureList);
    }


    public void addCreature(final EconomyCreature aCreature) {
        if(aCreature.getStats() instanceof WarMachinesStatistic){
                warMachines.add(aCreature);
        }
        else {
            int w = 0;
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

    }

    public void equipArtifact(Artifact artifact) {
        this.equipment.equipArtifact(artifact);
    }

    public void addArtifactToBackpack(final Artifact aArtifact){ backpack.addArtifact(aArtifact);}

    public boolean canAddCreature(EconomyCreature economyCreature) {

        if(economyCreature.getStats() instanceof WarMachinesStatistic){
            for(int i=0;i<this.warMachines.size();i++){
                if(warMachines.get(i).getStats().getName().equals(economyCreature.getStats().getName()))
                    return false;
            }
            return true;
        }
        else {
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
    }

    public boolean canAddArtifact(ArtifactPlacement placement) {
        for (EconomyArtifact a : this.artifactList) {
            if (a.getPlacement().equals(placement))
                return false;
        }
        return true;
    }

    public void updateHeroStats( HeroStats aStats ) {
        this.heroStats.updateStats(aStats);
    }


    public List<EconomyArtifact> getArtifactList() {
        return List.copyOf(equipment.getArtifacts());
    }

    public List<EconomySkill> getSkillsList() {
        return List.copyOf(skillsList);
    }

    public List<EconomySpell> getSpellsList() {
        return List.copyOf(spellsList);
    }

    public List<EconomyCreature> getWarMachines() {
        return List.copyOf(warMachines);
    }

    public void substractGold(final int aAmount) {
        this.gold = new Money(gold.getPrice() - aAmount);
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

    @Override
    public void applyArtifactEffect(ArtifactEffect<? extends ArtifactEffectApplicable> artifactEffect) {
        heroStats = (HeroStatistics) artifactApplier.calculateHeroUpgradedStatisticsAfterApplyingArtifact(artifactEffect, heroStats);
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

    public boolean canAddMachine(EconomyCreature machine) {
        for(int i=0;i<warMachines.size();i++){
            if(warMachines.get(i).getStats().getName().equals(machine.getStats().getName())){
                return false;
            }
        }
        return true;
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

    @Override
    public void applyArtifactEffect(final ArtifactEffect<? extends ArtifactEffectApplicable> aArtifactEffect) {
        heroStats = artifactApplier.calculateHeroUpgradedStatisticsAfterApplyingArtifact(aArtifactEffect, heroStats);
    }

    public static int getHeroCounter() {
        return heroCounter;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public HeroStatisticsIf getHeroStats() {
        return heroStats;
    }

    public Money getGold() {
        return gold;
    }
}