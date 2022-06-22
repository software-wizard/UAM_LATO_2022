package pl.psi.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.artifacts.EconomyArtifact;
import pl.psi.artifacts.ArtifactPlacement;
import pl.psi.artifacts.holder.CreatureArtifactNamesHolder;
import pl.psi.shop.Money;

import static org.junit.jupiter.api.Assertions.*;

class EconomyHeroTest {

    private EconomyHero hero;
    private EconomyArtifact item1;
    private EconomyArtifact item2;
    private EconomyArtifact item3;
    private CreatureArtifactNamesHolder ENUM_NOT_IMPORTANT = CreatureArtifactNamesHolder.RING_OF_LIFE;

    @BeforeEach
    void init() {
        hero = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, HeroStatistics.NECROMANCER);
        item1 = new EconomyArtifact(ArtifactPlacement.FEET, "item1", new Money(4), ENUM_NOT_IMPORTANT,"");
        item2 = new EconomyArtifact(ArtifactPlacement.HEAD, "item2", new Money(4), ENUM_NOT_IMPORTANT,"");
        item3 = new EconomyArtifact(ArtifactPlacement.HEAD, "item22", new Money(5), ENUM_NOT_IMPORTANT,"");
    }

    @Test
    void shouldAddItemToBackpack() {
        hero.addItem(item1);
        assertEquals(hero.getBackpack().size(), 1);
    }

    @Test
    void setNewArtifactFreePlace()
    {
        hero.equipArtifact(item1);
        assertTrue(hero.getEquipment().getArtifacts().contains(item1));

    }


}