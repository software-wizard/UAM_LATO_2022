package pl.psi.artifacts;

import lombok.NonNull;
import pl.psi.artifacts.holder.CreatureArtifactNamesHolder;
import pl.psi.artifacts.holder.SkillArtifactNamesHolder;
import pl.psi.artifacts.holder.SpellArtifactNamesHolder;
import pl.psi.artifacts.model.*;

import java.math.BigDecimal;
import java.util.Set;

// TODO: rethink price of Artifacts!

/**
 * Factory for creation of Creature, Spell and Skill Artefacts.
 */
public class ArtifactFactory
{
    private static final String NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE = "No implementation provided for artifact of that name.";

    public ArtifactIf createArtifact( @NonNull CreatureArtifactNamesHolder aArtifactName )
    {
        switch ( aArtifactName )
        {
            case RING_OF_LIFE:

                return createRingOfLifeArtifact();

            case RING_OF_VITALITY:

                return createRingOfVitalityArtifact();

            case VIAL_OF_LIFEBLOOD:

                return createVialOfLifebloodArtifact();

            default:

                throw new IllegalArgumentException( NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE );
        }
    }

    public ArtifactIf createArtifact( @NonNull SkillArtifactNamesHolder aArtifactName )
    {
        // TODO: create and define skill artifacts
        throw new IllegalArgumentException( NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE );
    }

    public ArtifactIf createArtifact( @NonNull SpellArtifactNamesHolder aArtifactName )
    {
        // TODO: create and define spell artifacts.
        throw new IllegalArgumentException( NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE );
    }

    private ArtifactIf createRingOfLifeArtifact()
    {
        final ArtifactEffect<ArtifactEffectApplicable> ringOfLifeEffect = ArtifactEffect.builder()
                .effectValue( BigDecimal.valueOf( 1 ) )
                .effectApplyingMode( ArtifactApplyingMode.ADD )
                .applierTarget( CreatureArtifactApplicableProperty.HEALTH )
                .build();

        return Artifact.builder()
                .name( "Ring of Life" )
                .description( "Increases health of all your units by 1" )
                .placement( ArtifactPlacement.FINGERS )
                .price( BigDecimal.valueOf( 10 ) )
                .rank( ArtifactRank.MINOR )
                .target( ArtifactTarget.CREATURES )
                .effects( Set.of( ringOfLifeEffect ) )
                .build();
    }

    private ArtifactIf createRingOfVitalityArtifact()
    {
        final ArtifactEffect< ArtifactEffectApplicable > rightOfVitalityEffect = ArtifactEffect.builder()
                .effectValue( BigDecimal.valueOf( 1 ) )
                .effectApplyingMode( ArtifactApplyingMode.ADD )
                .applierTarget( CreatureArtifactApplicableProperty.HEALTH )
                .build();

        return Artifact.builder()
                .name( "Ring of Vitality" )
                .description( "Increases health of all your units by 1" )
                .placement( ArtifactPlacement.FINGERS )
                .price( BigDecimal.valueOf( 15 ) )
                .rank( ArtifactRank.TREASURE )
                .target( ArtifactTarget.CREATURES )
                .effects( Set.of( rightOfVitalityEffect ) )
                .build();
    }

    private ArtifactIf createVialOfLifebloodArtifact()
    {
        final ArtifactEffect< ArtifactEffectApplicable > vialOfLifebloodEffect = ArtifactEffect.builder()
                .effectValue( BigDecimal.valueOf( 2 ) )
                .effectApplyingMode( ArtifactApplyingMode.ADD )
                .applierTarget( CreatureArtifactApplicableProperty.HEALTH )
                .build();

        return Artifact.builder()
                .name( "Vial of Lifeblood" )
                .description( "Increases health of all your units by 2" )
                .placement( ArtifactPlacement.MISC )
                .price( BigDecimal.valueOf( 30 ) )
                .rank( ArtifactRank.TREASURE )
                .target( ArtifactTarget.CREATURES )
                .effects( Set.of( vialOfLifebloodEffect ) )
                .build();
    }
}
