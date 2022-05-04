package pl.psi.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.psi.products.creatures.EconomyNecropolisFactory;

class EconomyHeroTest
{

    private EconomyHero hero;

    @BeforeEach
    void init()
    {
        hero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS );
    }

    @Test
    void shouldNotAddNewCreaturesWhileHeroHas7TypesOfCreatures()
    {
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 2, 1 ) );
        hero.addCreature( factory.create( true, 3, 1 ) );
        hero.addCreature( factory.create( true, 4, 1 ) );
        hero.addCreature( factory.create( true, 5, 1 ) );
        hero.addCreature( factory.create( true, 6, 1 ) );
        hero.addCreature( factory.create( true, 7, 1 ) );

        assertEquals( false, hero.canAddCreature(factory.create( false, 7, 1 )));
    }

    // New Test
    @Test
    void canAddNewCreaturesWhileHeroHas7PiecesOfCreatureOfTheSameTypeAndListOfCreaturesHasSize1()
    {
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );

        assertEquals( true, hero.canAddCreature(factory.create( true, 1, 1 )));
        assertEquals(7,hero.getCreatureList().get(0).getAmount());
        assertEquals( 1, hero.getCreatureList().size());
    }



    @Test
    void shouldAddAmountForExcitingInListCreatureInsteadOfAddCreatureToList()
    {
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();

        hero.addCreature( factory.create( true, 7, 1 ) );
        hero.addCreature( factory.create( true, 7, 1 ) );

        assertEquals( 2, hero.getCreatureList().get(0).getAmount() );
    }


    @Test
    void shouldThrowExceptionWhileYouTrySubstractMoreGoldThanHeroHas()
    {
        assertThrows( IllegalStateException.class, () -> hero.substractGold( hero.getGold() + 1 ) );
    }

    @Test
    void shouldAddNumbersOfCreaturesAndChangeLimitOfArtifactsOfHeroCorrectly(){
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 2, 3 ) );
        hero.addCreature( factory.create( true, 3, 5 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        assertEquals(10, hero.getNumberOfCreatures());
        assertEquals(10,hero.getStatistics().getLimitHead());
        hero.addCreature( factory.create( true, 1, 2 ) );
        assertEquals(12, hero.getNumberOfCreatures());
        assertEquals(12,hero.getStatistics().getLimitHead());

    }

    @Test
    void shouldHeroBuyCreaturesOfTheTypeHeHasIfHeHas7TypesOfCreatures(){
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 2, 1 ) );
        hero.addCreature( factory.create( true, 3, 1 ) );
        hero.addCreature( factory.create( true, 4, 1 ) );
        hero.addCreature( factory.create( true, 5, 1 ) );
        hero.addCreature( factory.create( true, 6, 1 ) );
        hero.addCreature( factory.create( true, 7, 1 ) );

        assertEquals(7,hero.getCreatureList().size());
        assertEquals( true, hero.canAddCreature(factory.create( true, 7, 1 )));
    }

}