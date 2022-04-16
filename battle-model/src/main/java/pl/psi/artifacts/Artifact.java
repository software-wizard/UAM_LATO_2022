package pl.psi.artifacts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@AllArgsConstructor
public class Artifact implements ArtifactIf
{
    private final ArtifactRank rank;

    private final ArtifactPlacement placement;

    private final String name;

    private final String description;

    private final double price;

    private final Set< ArtifactEffect > effects;

    public static class Builder
    {
        private ArtifactRank rank;

        private ArtifactPlacement placement;

        private String name;

        private String description;

        private double price;

        private final Set< ArtifactEffect > effects;

        public Builder()
        {
            effects = new HashSet<>();
        }

        public Builder rank( final ArtifactRank aRank )
        {
            rank = aRank;
            return this;
        }

        public Builder placement( final ArtifactPlacement aPlacement )
        {
            placement = aPlacement;
            return this;
        }

        public Builder name( final String aName )
        {
            name = aName;
            return this;
        }

        public Builder description( final String aDescription )
        {
            description = aDescription;
            return this;
        }

        public Builder price( final double aPrice )
        {
            price = aPrice;
            return this;
        }

        public Builder withEffect( final ArtifactEffectType aEffectType, final double aEffectValue,
                                   final ArtifactEffect.ApplyingMode aEffectApplyingMode )
        {
            if( !effects.add( new ArtifactEffect( aEffectType, aEffectValue, aEffectApplyingMode ) ) )
            {
                throw new ArtifactValidationException( "Artifact already has effect specified for " + aEffectType + "." );
            }

            return this;
        }

        private void validate()
        {
            if( Objects.isNull( name ) || name.trim().isEmpty() )
            {
                throw new ArtifactValidationException( "Artifact doesn't have specified name." );
            }

            if( Objects.isNull( description ) || description.trim().isEmpty() )
            {
                throw new ArtifactValidationException( "Artifact doesn't have specified description." );
            }

            if( price <= 0.0D )
            {
                throw new ArtifactValidationException( "Artifact can't cost 0 or less gold." );
            }

            if( Objects.isNull( rank ) )
            {
                throw new ArtifactValidationException( "Artifact doesn't have specified rank." );
            }

            if( Objects.isNull( placement ) )
            {
                throw new ArtifactValidationException( "Artifact doesn't have specified placement." );
            }

            if( effects.isEmpty() )
            {
                throw new ArtifactValidationException( "Artifact doesn't provide any effects." );
            }
        }

        public Artifact build()
        {
            validate();

            return new Artifact( rank, placement, name, description, price, effects );
        }
    }
}
