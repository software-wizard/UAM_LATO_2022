package pl.psi.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.psi.creatures.EconomyNecropolisFactory;

import java.math.BigDecimal;
import java.util.ArrayList;

class EconomyHeroTest
{

    private EconomyHero hero;
    private Artifact item1;
    private Artifact item2;

    @BeforeEach
    void init()
    {
        hero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 3000, HeroStatistics.NECROMANCER);
        item1 = new Artifact("item1", "adadadadad", new BigDecimal("4"), ArtifactPlacement.FEET);
        item2 = new Artifact("item12", "adadadadadadad", new BigDecimal("4"), ArtifactPlacement.HEAD);
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
        hero.addItem(item1);
        assertEquals(hero.getBackpack().size(),1);
    }

    @Test
    void addItemtoEqSlot()
    {
        EqSlot slot = new EqSlot(ArtifactPlacement.FEET);
        slot.setItem(item1);
        assertEquals(slot.getItem(),item1);
        assertThrows(IllegalStateException.class, () -> slot.setItem(item2));

    }

    @Test
    void addItemToEqSlotErrorThrow()
    {
        EqSlot slot = new EqSlot(ArtifactPlacement.FEET);
        assertThrows(IllegalStateException.class, () -> slot.setItem(item2));

    }
}