package pl.psi.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.EconomyEngine;
import pl.psi.products.Products;
import pl.psi.products.artifacts.ArtifactPlacement;
import pl.psi.products.artifacts.EconomyArtifact;
import pl.psi.products.artifacts.EconomyArtifactFactory;
import pl.psi.products.creatures.EconomyNecropolisFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuyingArtifactTest {

    private final EconomyArtifactFactory artifactFactory = new EconomyArtifactFactory();
    private final EconomyNecropolisFactory creatureFactory = new EconomyNecropolisFactory();
    private EconomyHero hero1;
    private EconomyEngine economyEngine;
    private EconomyHero hero2;

    @BeforeEach
    void init()
    {
        hero1 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS );
        hero2 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS );
        economyEngine = new EconomyEngine( hero1, hero2 );
    }

    @Test
    void shouldHeroBuyArtifactAndNumberOfArtifactsCanChangeCorrectlyAndNumberOfBoughtArtifactsShouldIncrease(){
        economyEngine.buy( Products.CREATURE,creatureFactory.create( false, 1, 1 ) );
        EconomyArtifact economyArtifact = artifactFactory.create("Crown of Dragontooth",1);
        economyEngine.buy(Products.ARTIFACT,economyArtifact);
        assertEquals(3600,hero1.getGold());
        assertEquals(1,hero1.getArtifactList().size());
        assertEquals(1,hero1.getCreatureList().size());
        assertEquals(1,hero1.getStatistics().getBoughtHead());
        assertEquals(false,hero1.canAddArtifact(ArtifactPlacement.HEAD,1));
    }


    @Test
    void shouldNotBuyArtifactsIfHeroDidntBuyCreatures(){
        EconomyArtifact economyArtifact = artifactFactory.create("Crown of Dragontooth",1);
        assertThrows( IllegalStateException.class,
                () -> economyEngine.buy(Products.ARTIFACT,economyArtifact) );
    }
}
