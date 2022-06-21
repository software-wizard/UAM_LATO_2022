package pl.psi.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.artifacts.Artifact;
import pl.psi.artifacts.ArtifactPlacement;
import pl.psi.creatures.EconomyCreature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.shop.Money;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EconomyHeroTest {

    private EconomyHero hero;
    private Artifact item1;
    private Artifact item2;
    private Artifact item3;

    @BeforeEach
    void init() {
        hero = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, HeroStatistics.NECROMANCER);
        item1 = new Artifact(ArtifactPlacement.FEET, "item1", new Money(4));
        item2 = new Artifact(ArtifactPlacement.HEAD, "item2", new Money(4));
        item3 = new Artifact(ArtifactPlacement.HEAD, "item3", new Money(6));
    }

    @Test
    void shouldNotAddNewCreaturesWhenHeroHas7TypesOfCreaturesAndHeroTryToBuyNewTypeOfCreature() {
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero.addCreature(factory.create(true, 1, 1));
        hero.addCreature(factory.create(true, 2, 1));
        hero.addCreature(factory.create(true, 3, 1));
        hero.addCreature(factory.create(true, 4, 1));
        hero.addCreature(factory.create(true, 5, 1));
        hero.addCreature(factory.create(true, 6, 1));
        hero.addCreature(factory.create(true, 7, 1));

        assertEquals(false, hero.canAddCreature(factory.create(false, 7, 1)));
    }


    @Test
    void canAddNewCreaturesWhileHeroHas7CreatureOfTheSameTypeAndListOfCreaturesHasSize1() {
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero.addCreature(factory.create(true, 1, 1));
        hero.addCreature(factory.create(true, 1, 1));
        hero.addCreature(factory.create(true, 1, 1));
        hero.addCreature(factory.create(true, 1, 1));
        hero.addCreature(factory.create(true, 1, 1));
        hero.addCreature(factory.create(true, 1, 1));
        hero.addCreature(factory.create(true, 1, 1));

        assertEquals(true, hero.canAddCreature(factory.create(true, 1, 1)));
        assertEquals(7, hero.getCreatures().get(0).getAmount());
        assertEquals(1, hero.getCreatures().size());
    }


    @Test
    void shouldAddAmountForExcitingInListCreatureInsteadOfAddCreatureToList() {
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero.addCreature(factory.create(true, 7, 1));
        hero.addCreature(factory.create(true, 7, 1));

        assertEquals(2, hero.getCreatures().get(0).getAmount());
    }

    @Test
    void shouldAddItemToBackpack()
    {
        hero.addArtifactToBackpack(item1);
        assertEquals(hero.getBackpack().getArtifacts().size(),1);
    }

    @Test
    void setNewArtifactFreePlace()
    {
        hero.equipArtifact(item1);
        assertTrue(hero.getEquipment().getArtifacts().contains(item1));

    }

    @Test
    void setNewArtifactTakenPlace()
    {
        hero.equipArtifact(item2);
        hero.equipArtifact(item3);
        assertFalse(hero.getEquipment().getArtifacts().contains(item2));
        assertTrue(hero.getBackpack().getArtifacts().contains(item2));
        assertTrue(hero.getEquipment().getArtifacts().contains(item3));

    }


    @Test
    void getCreatureListNotReturnReference() {
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero.addCreature(factory.create(true, 1, 1));
        hero.addCreature(factory.create(true, 2, 3));
        hero.addCreature(factory.create(true, 3, 5));

        List<EconomyCreature> economyCreatureList = hero.getCreatures();
        economyCreatureList.add(factory.create(true, 4, 1));
        assertEquals(4, economyCreatureList.size());
        assertEquals(3, hero.getCreatures().size());
    }

}