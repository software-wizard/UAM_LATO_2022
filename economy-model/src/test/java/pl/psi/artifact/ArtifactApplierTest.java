package pl.psi.artifact;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.psi.artifacts.*;
import pl.psi.artifacts.model.ArtifactApplyingMode;
import pl.psi.artifacts.model.ArtifactEffect;
import pl.psi.creatures.CreatureStatisticIf;
import pl.psi.creatures.CreatureStats;
import pl.psi.hero.HeroStatisticsIf;
import pl.psi.hero.HeroStats;
import pl.psi.spells.SpellFactorsModifiers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArtifactApplierTest
{
    private static ArtifactApplier artifactApplier;

    private static final double EPSILON = 0.00001;

    @BeforeAll
    public static void init()
    {
        artifactApplier = new ArtifactApplier();
    }

    @Test
    public void artifactApplierShouldUpgradeCreatureStatistics()
    {
        // given
        final ArtifactEffect< ? extends ArtifactEffectApplicable > artifactEffect = ArtifactEffect.builder()
                .effectValue( BigDecimal.valueOf( 5 ) )
                .effectApplyingMode( ArtifactApplyingMode.ADD )
                .applierTarget( CreatureArtifactApplicableProperty.ATTACK )
                .build();

        final CreatureStatisticIf baseStatistics = CreatureStats.builder()
                .build();
        final CreatureStatisticIf upgradedStatistics = CreatureStats.builder()
                .attack( 10 )
                .build();
        // when
        final CreatureStatisticIf resultOfApplier
                = artifactApplier.calculateCreatureUpgradedStatisticAfterApplyingArtifact( artifactEffect,
                baseStatistics, upgradedStatistics );

        // then
        final double expectedValue = artifactEffect.getEffectValue().doubleValue();
        assertTrue( Math.abs( ( expectedValue + upgradedStatistics.getAttack() ) - resultOfApplier.getAttack()  ) < EPSILON );
    }

    @Test
    public void artifactApplierShouldUpgradeHeroStatistics()
    {
        // given
        final ArtifactEffect< ? extends ArtifactEffectApplicable > artifactEffect = ArtifactEffect.builder()
                .effectValue( BigDecimal.valueOf( 5 ) )
                .effectApplyingMode( ArtifactApplyingMode.ADD )
                .applierTarget( SkillArtifactApplicableProperty.KNOWLEDGE )
                .build();

        final HeroStatisticsIf baseStats = HeroStats.builder()
                .knowledge( 10 )
                .build();

        // when
        final HeroStatisticsIf resultOfApplier = artifactApplier.calculateHeroUpgradedStatisticsAfterApplyingArtifact( artifactEffect, baseStats );

        // then
        final double expectedValue = artifactEffect.getEffectValue().doubleValue();
        assertTrue( Math.abs( ( expectedValue + baseStats.getKnowledge() ) - resultOfApplier.getKnowledge()  ) < EPSILON );
    }

    @Test
    public void artifactApplierShouldUpgradeSpellFactors()
    {
        // given
        final ArtifactEffect< ? extends ArtifactEffectApplicable > artifactEffect = ArtifactEffect.builder()
                .effectValue( BigDecimal.valueOf( 5 ) )
                .effectApplyingMode( ArtifactApplyingMode.ADD )
                .applierTarget( SpellArtifactApplicableProperty.FIRE_DAMAGE )
                .build();

        final SpellFactorsModifiers baseStats = SpellFactorsModifiers.builder()
                .fireDamageModifier( 5 )
                .build();

        // when
        final SpellFactorsModifiers resultOfApplier = artifactApplier.calculateSpellFactorsModifiers( artifactEffect, baseStats );

        // then
        final double expectedValue = artifactEffect.getEffectValue().doubleValue();
        assertTrue( Math.abs( ( expectedValue + baseStats.getFireDamageModifier() ) - resultOfApplier.getFireDamageModifier() ) < EPSILON );
    }
}
