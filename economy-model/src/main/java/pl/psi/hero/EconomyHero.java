package pl.psi.hero;

import lombok.Getter;
import pl.psi.artifacts.Artifact;
import pl.psi.artifacts.ArtifactEffectApplicable;
import pl.psi.artifacts.ArtifactPlacement;
import pl.psi.artifacts.model.ArtifactEffect;
import pl.psi.creatures.ArtifactApplier;
import pl.psi.creatures.EconomyCreature;
import pl.psi.skills.EconomySkill;
import pl.psi.spells.EconomySpell;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class EconomyHero implements ArtifactEffectApplicable {

    private static int heroCounter = 0;
    private final Fraction fraction;
    private final List<EconomyCreature> creatureList;
    private final List<Artifact> artifactList;
    private final List<EconomySkill> skillsList;
    private final List<EconomySpell> spellsList;
    private final Equipment equipment;
    private final Backpack backpack;
    private HeroStatisticsIf heroStats;
    private final int heroNumber;
    // start amount of gold
    private int gold = 10000;
    private final ArtifactApplier artifactApplier = new ArtifactApplier();

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
        backpack = equipment.getBackpack();
        heroStats = aClass;
    }

    public EconomyHero(Fraction fraction, List<EconomyCreature> creatureList, List<Artifact> artifactList, List<EconomySkill> skillsList, List<EconomySpell> spellsList, int gold, int heroNumber) {
        this.fraction = fraction;
        this.creatureList = creatureList;
        this.artifactList = artifactList;
        this.skillsList = skillsList;
        this.spellsList = spellsList;
        this.gold = gold;
        this.heroNumber = heroNumber;
        equipment = new Equipment();
        backpack = equipment.getBackpack();
        heroStats = HeroStatistics.NECROMANCER;
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

    public void equipArtifact(Artifact artifact) {
        this.equipment.equipArtifact(artifact);
    }

    public void addArtifactToBackpack(final Artifact aArtifact){ backpack.addArtifact(aArtifact);}

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

    public void updateHeroStats( HeroStats aStats ) {
        this.heroStats.updateStats(aStats);
    }


    public List<Artifact> getArtifacts() {
        return List.copyOf(equipment.getArtifacts());
    }

    public List<EconomySkill> getSkills() {
        return List.copyOf(skillsList);
    }

    public List<EconomySpell> getSpells() {
        return List.copyOf(spellsList);
    }

    public void substractGold(final int aAmount) {
        gold -= aAmount;
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

    public boolean canAddArtifact(ArtifactPlacement placement) {
        for (Artifact a : this.artifactList) {
            if (a.getPlacement().equals(placement))
                return false;
        }
        return true;
    }

    public enum Fraction {
        NECROPOLIS,
        CASTLE
    }

    public List<EconomySpell> getSpellList() {
        return spellsList;
    }

    public int getSpellPower() {
        return heroStats.getSpellPower();
    }

}
