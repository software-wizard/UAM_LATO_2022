package pl.psi.artifacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.artifacts.model.ArtifactApplyingMode;
import pl.psi.artifacts.model.ArtifactEffect;
import pl.psi.hero.HeroStatistics;
import pl.psi.hero.HeroStatisticsIf;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArtifactApplierTest {

    private static final double MULTIPLIER = 2.1;
    public static final double VALUE_TO_ADD = 3;

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

        assertThrows(IllegalArgumentException.class, () -> artifactApplier.calculateCreatureUpgradedStatisticAfterApplyingArtifact(effect, null, null));
    }

    @Test
    void shouldThrowWhenInvalidArtifactTargetForSkill() {
        final ArtifactEffect<ArtifactEffectApplicable> effect = ArtifactEffect.builder()
                .applierTarget(CreatureArtifactApplicableProperty.ATTACK)
                .build();

        assertThrows(IllegalArgumentException.class, () -> artifactApplier.calculateHeroUpgradedStatisticsAfterApplyingArtifact(effect, null));
    }

    @Test
    void shouldThrowWhenInvalidArtifactTargetForSpells() {
        final ArtifactEffect<ArtifactEffectApplicable> effect = ArtifactEffect.builder()
                .applierTarget(SkillArtifactApplicableProperty.ATTACK)
                .build();

        assertThrows(IllegalArgumentException.class, () -> artifactApplier.calculateSpellFactorsModifiers(effect, null));
    }

    @Test
    void shouldUpgradeHeroStatisticsCorrectlyWhenMultiplying() {
        // given
        final ArtifactEffect<ArtifactEffectApplicable> effect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(MULTIPLIER))
                .effectApplyingMode(ArtifactApplyingMode.MULTIPLY)
                .applierTarget(SkillArtifactApplicableProperty.ATTACK)
                .build();
        final HeroStatisticsIf heroStatistics = HeroStatistics.KNIGHT;
        final int expected = (int) (heroStatistics.getAttack() + heroStatistics.getAttack() * MULTIPLIER);
        // when
        final HeroStatisticsIf result = artifactApplier.calculateHeroUpgradedStatisticsAfterApplyingArtifact(effect, heroStatistics);
        // then
        assertEquals(expected, result.getAttack());
    }

    @Test
    void shouldUpgradeHeroStatisticsCorrectlyWhenAdding() {
        // given
        final ArtifactEffect<ArtifactEffectApplicable> effect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(VALUE_TO_ADD))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.ATTACK)
                .build();
        final HeroStatisticsIf heroStatistics = HeroStatistics.KNIGHT;
        final int expected = (int) (heroStatistics.getAttack() + VALUE_TO_ADD);
        // when
        final HeroStatisticsIf result = artifactApplier.calculateHeroUpgradedStatisticsAfterApplyingArtifact(effect, heroStatistics);
        // then
        assertEquals(expected, result.getAttack());
    }
}