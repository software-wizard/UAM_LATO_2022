package pl.psi.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.EconomyEngine;
import pl.psi.ProductType;
import pl.psi.artifacts.ArtifactPlacement;
import pl.psi.artifacts.EconomyArtifactFactory;
import pl.psi.artifacts.holder.CreatureArtifactNamesHolder;
import pl.psi.artifacts.holder.SkillArtifactNamesHolder;
import pl.psi.creatures.EconomyNecropolisFactory;

public class BuyingArtifactTest {

    private final EconomyArtifactFactory artifactFactory = new EconomyArtifactFactory();
    private final EconomyNecropolisFactory creatureFactory = new EconomyNecropolisFactory();
    private EconomyHero hero1;
    private EconomyEngine economyEngine;
    private EconomyHero hero2;

    @BeforeEach
    void init() {
        hero1 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS);
        hero2 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS);
        economyEngine = new EconomyEngine(hero1, hero2);
    }

    @Test
    void shouldHeroBuyArtefact() {
        economyEngine.buy(ProductType.ARTIFACT,
            artifactFactory.create(CreatureArtifactNamesHolder.RING_OF_LIFE));
        assertEquals(1, hero1.getArtifacts().size());
    }

    @Test
    void shouldHeroBuyDifferentTypesOfArtifacts() {
        economyEngine.buy(ProductType.ARTIFACT,
            artifactFactory.create(CreatureArtifactNamesHolder.SURCOAT_OF_COUNTERPOISE));
        economyEngine.buy(ProductType.ARTIFACT,
            artifactFactory.create(SkillArtifactNamesHolder.SHIELD_OF_THE_DWARVEN_LORDS));
        assertEquals(2, hero1.getArtifacts().size());
        assertEquals(ArtifactPlacement.SHOULDERS, hero1.getArtifacts().get(0).getPlacement());
        assertEquals(ArtifactPlacement.LEFT_HAND, hero1.getArtifacts().get(1).getPlacement());
    }

    @Test
    void shouldThrowExceptionWhenHeroTryToBuyArtifactOfTheTypeHeHas() {
        economyEngine.buy(ProductType.ARTIFACT,
            artifactFactory.create(CreatureArtifactNamesHolder.RING_OF_LIFE));
        assertThrows(IllegalStateException.class,
            () -> economyEngine.buy(ProductType.ARTIFACT,
                artifactFactory.create(CreatureArtifactNamesHolder.RING_OF_LIFE)));
    }

}
