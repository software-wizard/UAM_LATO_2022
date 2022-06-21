package pl.psi;

import pl.psi.artifacts.EconomyArtifact;
import pl.psi.creatures.EconomyCreature;
import pl.psi.hero.EconomyHero;
import pl.psi.shop.*;
import pl.psi.skills.EconomySkill;
import pl.psi.spells.EconomySpell;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EconomyEngine {
    public static final String HERO_BOUGHT_CREATURE = "HERO_BOUGHT_CREATURE";
    public static final String ACTIVE_HERO_CHANGED = "ACTIVE_HERO_CHANGED";
    public static final String HERO_BOUGHT_ARTIFACT = "HERO_BOUGHT_ARTIFACT";
    public static final String HERO_BOUGHT_SKILL = "HERO_BOUGHT_SKILL";
    public static final String HERO_BOUGHT_SPELL = "HERO_BOUGHT_SPELL";
    public static final String NEXT_ROUND = "NEXT_ROUND";
    private final EconomyHero hero1;
    private final EconomyHero hero2;
    private final PropertyChangeSupport observerSupport;
    private EconomyHero activeHero;

    private int roundNumber;

    public EconomyEngine(final EconomyHero aHero1, final EconomyHero aHero2) {
        hero1 = aHero1;
        hero2 = aHero2;
        activeHero = hero1;
        roundNumber = 1;
        observerSupport = new PropertyChangeSupport(this);
    }

    public void buy(ProductType product, final BuyProductInterface buyProduct) {
        if (product.equals(ProductType.CREATURE)) {
            CreatureShop creatureShop = new CreatureShop();
            EconomyCreature creature = (EconomyCreature) buyProduct;
            creatureShop.addToHero(creature, activeHero);
            observerSupport.firePropertyChange(HERO_BOUGHT_CREATURE, null, null);
        } else if (product.equals(ProductType.ARTIFACT)) {
            ArtifactShop artifactShop = new ArtifactShop();
            EconomyArtifact artifact = (EconomyArtifact) buyProduct;
            artifactShop.addToHero(artifact, activeHero);
            observerSupport.firePropertyChange(HERO_BOUGHT_ARTIFACT, null, null);
        } else if (product.equals(ProductType.SKILL)) {
            SkillShop skillShop = new SkillShop();
            EconomySkill skill = (EconomySkill) buyProduct;
            skillShop.addToHero(skill, activeHero);
            observerSupport.firePropertyChange(HERO_BOUGHT_SKILL, null, null);
        }
        else if(product.equals(ProductType.SPELL)) {
            SpellShop spellShop = new SpellShop();
            EconomySpell spell = (EconomySpell) buyProduct;
            spellShop.addToHero(spell, activeHero);
            observerSupport.firePropertyChange(HERO_BOUGHT_SPELL, null, null);
        }
    }

    public EconomyHero getActiveHero() {
        return new EconomyHero(activeHero.getFraction(), activeHero.getCreatureList(), activeHero.getArtifactList(), activeHero.getSkillsList(), activeHero.getSpellsList(),activeHero.getWarMachines(), activeHero.getGold(), activeHero.getHeroNumber(),activeHero.getHeroStats());
    }

    public void pass() {
        if (activeHero.getCreatureList().size() == 0) {
            throw new IllegalStateException("hero cannot pass round if he didn't buy any creature");
        } else {
            activeHero = hero2;
            observerSupport.firePropertyChange(ACTIVE_HERO_CHANGED, hero1, activeHero);
            endTurn();
        }
    }

    private void endTurn() {
        if (roundNumber != 2) {
            roundNumber += 1;
            observerSupport.firePropertyChange(NEXT_ROUND, roundNumber - 1, roundNumber);
        }
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void addObserver(final String aPropertyName, final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aPropertyName, aObserver);
    }

    public EconomyHero getPlayer1() {
        return new EconomyHero(hero1.getFraction(), hero1.getCreatureList(), hero1.getArtifactList(), hero1.getSkillsList(), hero1.getSpellsList(),hero1.getWarMachines(), hero1.getGold(), hero1.getHeroNumber(),hero1.getHeroStats());
    }

    public EconomyHero getPlayer2() {
        return new EconomyHero(hero2.getFraction(), hero2.getCreatureList(), hero2.getArtifactList(), hero2.getSkillsList(), hero2.getSpellsList(), hero2.getWarMachines(), hero2.getGold(), hero2.getHeroNumber(),hero2.getHeroStats());
    }

}