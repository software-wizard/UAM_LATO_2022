package pl.psi.artifacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.artifacts.model.ArtifactEffect;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ArtifactApplierTest {

    private ArtifactApplier artifactApplier;

    @BeforeEach
    void setUp() {
        artifactApplier = new ArtifactApplier();
    }

    @Test
    void shouldThrowWhenInvalidArtifactTargetForCreature() {
        final ArtifactEffect<ArtifactEffectApplicable> effect = ArtifactEffect.builder()
                .applierTarget(SkillArtifactApplicableProperty.ATTACK)
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            artifactApplier.calculateCreatureUpgradedStatisticAfterApplyingArtifact(effect, null, null);
        });
    }

    @Test
    void shouldThrowWhenInvalidArtifactTargetForSkill() {
        final ArtifactEffect<ArtifactEffectApplicable> effect = ArtifactEffect.builder()
                .applierTarget(CreatureArtifactApplicableProperty.ATTACK)
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            artifactApplier.calculateHeroUpgradedStatisticsAfterApplyingArtifact(effect, null);
        });
    }

    @Test
    void shouldThrowWhenInvalidArtifactTargetForSpells() {
        final ArtifactEffect<ArtifactEffectApplicable> effect = ArtifactEffect.builder()
                .applierTarget(SkillArtifactApplicableProperty.ATTACK)
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            artifactApplier.calculateSpellFactorsModifiers(effect, null);
        });
    }
}