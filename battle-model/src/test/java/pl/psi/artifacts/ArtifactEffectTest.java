package pl.psi.artifacts;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArtifactEffectTest {


    @Test
    void testEquals() {
        ArtifactEffect aArtifactEffect = new ArtifactEffect( ArtifactEffectType.HEALTH,
                2, ArtifactEffect.ApplyingMode.MULTIPLY );
        ArtifactEffect sameTypeArtifactEffect = new ArtifactEffect( ArtifactEffectType.HEALTH,
                4, ArtifactEffect.ApplyingMode.ADD );
        ArtifactEffect differentTypeArtifactEffect = new ArtifactEffect( ArtifactEffectType.ATTACK,
                2, ArtifactEffect.ApplyingMode.MULTIPLY );

        assertEquals(aArtifactEffect, sameTypeArtifactEffect);
        assertNotEquals(aArtifactEffect, differentTypeArtifactEffect);
    }

    @Test
    void testHashCode() {
        ArtifactEffect aArtifactEffect = new ArtifactEffect( ArtifactEffectType.HEALTH,
                2, ArtifactEffect.ApplyingMode.MULTIPLY );
        ArtifactEffect sameTypeArtifactEffect = new ArtifactEffect( ArtifactEffectType.HEALTH,
                4, ArtifactEffect.ApplyingMode.ADD );
        ArtifactEffect differentTypeArtifactEffect = new ArtifactEffect( ArtifactEffectType.ATTACK,
                2, ArtifactEffect.ApplyingMode.MULTIPLY );

        Set<ArtifactEffect> aSet = new HashSet<>();
        aSet.add( aArtifactEffect );

        assertFalse( aSet.add( sameTypeArtifactEffect ) );
        assertTrue( aSet.add( differentTypeArtifactEffect ) );
    }
}