package pl.psi.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.psi.creatures.EconomyNecropolisFactory;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EconomyHeroTest
{

    private EconomyHero hero;
    private Artifact item1;
    private Artifact item2;
    private Artifact item3;

    @BeforeEach
    void init()
    {
        hero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 3000, HeroStatistics.NECROMANCER);
        item1 = new Artifact("item1", "adadadadad", new BigDecimal("4"), ArtifactPlacement.FEET);
        item2 = new Artifact("item12", "adadadadadadad", new BigDecimal("4"), ArtifactPlacement.HEAD);
        item3 = new Artifact("ite12m12", "adadadadad123adad", new BigDecimal("42"), ArtifactPlacement.HEAD);
    }

    @Test
    void shouldThrowExceptionWhileHeroHas7CreatureAndYoTryToAddNextOne()
    {
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );

        assertThrows( IllegalStateException.class, () -> hero.addCreature( factory.create( true, 1, 1 ) ) );
    }

    @Test
    void shouldThrowExceptionWhileYouTrySubstractMoreGoldThanHeroHas()
    {
        assertThrows( IllegalStateException.class, () -> hero.substractGold( 3001 ) );
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
}