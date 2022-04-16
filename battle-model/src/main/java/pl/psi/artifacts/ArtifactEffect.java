package pl.psi.artifacts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
class ArtifactEffect
{
    private ArtifactEffectType effectType;

    private double effectValue;

    private ArtifactEffect.ApplyingMode effectApplyingMode;

    /**
     * As ArtifactEffect should only be compared within Artifact's set of effects, we consider
     * ArtifactEffects to be equal when they have the same effectType.
     */
    @Override
    public boolean equals( Object aObj )
    {
        if( Objects.isNull( aObj ) || !aObj.getClass().equals( ArtifactEffect.class ) )
        {
            return false;
        }
        ArtifactEffect comparedEffect = (ArtifactEffect) aObj;
        return this.getEffectType() == comparedEffect.getEffectType();
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode( effectType );
    }

    enum ApplyingMode
    {
        MULTIPLY,

        ADD
    }
}
