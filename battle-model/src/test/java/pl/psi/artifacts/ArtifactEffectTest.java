package pl.psi.artifacts;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArtifactEffectTest {


    @Test
    void testEquals() {
        ArtifactEffect aArtifactEffect = new ArtifactEffect(2,
                ArtifactApplyingMode.MULTIPLY, ArtifactApplierTarget.HEALTH);
        ArtifactEffect sameArtifactEffect = new ArtifactEffect(2,
                ArtifactApplyingMode.MULTIPLY, ArtifactApplierTarget.HEALTH);
        ArtifactEffect differentArtifactEffect = new ArtifactEffect(4,
                ArtifactApplyingMode.MULTIPLY, ArtifactApplierTarget.ATTACK);

        assertEquals(aArtifactEffect, sameArtifactEffect);
        assertNotEquals(aArtifactEffect, differentArtifactEffect);
    }

    @Test
    void testHashCode() {
        ArtifactEffect aArtifactEffect = new ArtifactEffect(2,
                ArtifactApplyingMode.MULTIPLY, ArtifactApplierTarget.HEALTH);
        ArtifactEffect sameArtifactEffect = new ArtifactEffect(2,
                ArtifactApplyingMode.MULTIPLY, ArtifactApplierTarget.HEALTH);
        ArtifactEffect differentArtifactEffect = new ArtifactEffect(4,
                ArtifactApplyingMode.MULTIPLY, ArtifactApplierTarget.ATTACK);

        Set<ArtifactEffect> aSet = new HashSet<>();
        aSet.add( aArtifactEffect );

        assertFalse( aSet.add( sameArtifactEffect ) );
        assertTrue( aSet.add( differentArtifactEffect ) );
    }
}