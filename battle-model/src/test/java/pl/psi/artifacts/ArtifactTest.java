package pl.psi.artifacts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtifactTest {
    @Test
    void addTwoSameTypeEffectsException(){
        ArtifactValidationException aException = assertThrows(
                ArtifactValidationException.class,
                () -> new Artifact.Builder()
                        .withEffect( ArtifactEffectType.HEALTH, 2, ArtifactEffect.ApplyingMode.MULTIPLY)
                        .withEffect( ArtifactEffectType.HEALTH, 4, ArtifactEffect.ApplyingMode.ADD )
                        .build(),
                "Did not throw exception"
        );

        assertTrue( aException.getMessage().contains( "HEALTH" ) );
    }

    @Test
    void lackingNameException(){
        ArtifactValidationException aException = assertThrows(
                ArtifactValidationException.class,
                () -> new Artifact.Builder()
                        .withEffect( ArtifactEffectType.HEALTH, 2, ArtifactEffect.ApplyingMode.MULTIPLY)
                        .rank(ArtifactRank.TREASURE)
                        .description("Lorem")
                        .placement(ArtifactPlacement.HEAD)
                        .price(200)
                        .build(),
                    "Did not throw exception"
        );

        assertTrue( aException.getMessage().contains("name") );
    }

    @Test
    void lackingEffectsException(){
        ArtifactValidationException aException = assertThrows(
                ArtifactValidationException.class,
                () -> new Artifact.Builder()
                        .name("Ipsum")
                        .rank(ArtifactRank.TREASURE)
                        .description("Lorem")
                        .placement(ArtifactPlacement.HEAD)
                        .price(200)
                        .build(),
                "Did not throw exception"
        );

        assertTrue( aException.getMessage().contains("effects") );
    }

    @Test
    void lackingRankException(){
        ArtifactValidationException aException = assertThrows(
                ArtifactValidationException.class,
                () -> new Artifact.Builder()
                        .name("Ipsum")
                        .withEffect( ArtifactEffectType.HEALTH, 2, ArtifactEffect.ApplyingMode.MULTIPLY)
                        .description("Lorem")
                        .placement(ArtifactPlacement.HEAD)
                        .price(200)
                        .build(),
                "Did not throw exception"
        );

        assertTrue( aException.getMessage().contains("rank") );
    }

    @Test
    void lackingDescriptionException(){
        ArtifactValidationException aException = assertThrows(
                ArtifactValidationException.class,
                () -> new Artifact.Builder()
                        .name("Ipsum")
                        .withEffect( ArtifactEffectType.HEALTH, 2, ArtifactEffect.ApplyingMode.MULTIPLY)
                        .rank(ArtifactRank.TREASURE)
                        .placement(ArtifactPlacement.HEAD)
                        .price(200)
                        .build(),
                "Did not throw exception"
        );

        assertTrue( aException.getMessage().contains("description") );
    }

    @Test
    void lackingPlacementException(){
        ArtifactValidationException aException = assertThrows(
                ArtifactValidationException.class,
                () -> new Artifact.Builder()
                        .name("Ipsum")
                        .withEffect( ArtifactEffectType.HEALTH, 2, ArtifactEffect.ApplyingMode.MULTIPLY)
                        .description("Lorem")
                        .rank(ArtifactRank.TREASURE)
                        .price(200)
                        .build(),
                "Did not throw exception"
        );

        assertTrue( aException.getMessage().contains("placement") );
    }

    @Test
    void lackingPriceException(){
        ArtifactValidationException aException = assertThrows(
                ArtifactValidationException.class,
                () -> new Artifact.Builder()
                        .name("Ipsum")
                        .withEffect( ArtifactEffectType.HEALTH, 2, ArtifactEffect.ApplyingMode.MULTIPLY)
                        .description("Lorem")
                        .rank(ArtifactRank.TREASURE)
                        .placement(ArtifactPlacement.HEAD)
                        .build(),
                "Did not throw exception"
        );

        assertTrue( aException.getMessage().contains("gold") );
    }

    @Test
    void wrongPriceException(){
        ArtifactValidationException aException = assertThrows(
                ArtifactValidationException.class,
                () -> new Artifact.Builder()
                        .name("Ipsum")
                        .withEffect( ArtifactEffectType.HEALTH, 2, ArtifactEffect.ApplyingMode.MULTIPLY)
                        .description("Lorem")
                        .rank(ArtifactRank.TREASURE)
                        .placement(ArtifactPlacement.HEAD)
                        .price(-20)
                        .build(),
                "Did not throw exception"
        );

        assertTrue( aException.getMessage().contains("gold") );
    }

}