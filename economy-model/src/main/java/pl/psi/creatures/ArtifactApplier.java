package pl.psi.creatures;

import pl.psi.artifacts.ArtifactEffectApplicable;
import pl.psi.artifacts.ArtifactEffectApplyingProperties;
import pl.psi.artifacts.CreatureArtifactApplicableProperty;
import pl.psi.artifacts.model.ArtifactEffect;
import pl.psi.artifacts.model.ArtifactTarget;

/**
 * Service responsible for taking care of calculating upgraded values after applying artifact.
 */
public class ArtifactApplier
{
    protected CreatureStatisticIf calculateCreatureUpgradedStatisticAfterApplyingArtifact(
            final ArtifactEffect< ? extends ArtifactEffectApplicable > aArtifactEffect, final CreatureStatisticIf aBaseStats,
            final CreatureStatisticIf aUpgradedStats )
    {

        if( !( aArtifactEffect.getApplierTarget().getArtifactTarget() == ArtifactTarget.CREATURES ) )
        {
            throw new IllegalArgumentException( "Artifact Effect is not meant to be applied to Creatures." );
        }

        final CreatureArtifactApplicableProperty applierTarget = (CreatureArtifactApplicableProperty) aArtifactEffect.getApplierTarget();

        final CreatureStats.CreatureStatsBuilder creatureStatsBuilder = CreatureStats.builder()
                .maxHp( aUpgradedStats.getMaxHp() )
                .attack( aUpgradedStats.getAttack() )
                .armor( aUpgradedStats.getArmor() )
                .name( aUpgradedStats.getName() )
                .moveRange( aUpgradedStats.getMoveRange() )
                .damage( aUpgradedStats.getDamage() )
                .tier( aUpgradedStats.getTier() )
                .description( aUpgradedStats.getDescription() )
                .isUpgraded( aUpgradedStats.isUpgraded() );

        switch ( applierTarget )
        {
            case HEALTH:
                final int upgradedMaxHp = aArtifactEffect.calculateStatisticValueAfterApplying(
                        new ArtifactEffectApplyingProperties( aBaseStats.getMaxHp(), aUpgradedStats.getMaxHp()) );
                return creatureStatsBuilder.maxHp( upgradedMaxHp ).build();
            case ATTACK:
                final int upgradedAttack = aArtifactEffect.calculateStatisticValueAfterApplying(
                        new ArtifactEffectApplyingProperties( aBaseStats.getAttack(), aUpgradedStats.getAttack() ) );
                return creatureStatsBuilder.attack( upgradedAttack ).build();
            case DEFENCE:
                final int upgradedArmor = aArtifactEffect.calculateStatisticValueAfterApplying(
                        new ArtifactEffectApplyingProperties( aBaseStats.getArmor(), aUpgradedStats.getArmor() ) );
                return creatureStatsBuilder.armor( upgradedArmor ).build();
            default:
                throw new UnsupportedOperationException( "Unrecognised applying target type." );
        }
    }

    // TODO: Two new methods for calculating upgraded skills & spells.
}
