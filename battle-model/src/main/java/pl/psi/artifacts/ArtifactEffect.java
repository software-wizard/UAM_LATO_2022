package pl.psi.artifacts;

import java.math.BigDecimal;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArtifactEffect<T extends ArtifactEffectApplicable> {

    private final BigDecimal effectValue;

    private final ArtifactApplyingMode effectApplyingMode;

    private final ArtifactApplierTarget applierTarget;

    void apply( T aAppliableModelObject )
    {
        aAppliableModelObject.applyArtifactEffect( this );
    }

    @Override
    public boolean equals( final Object aObj )
    {
        if ( this == aObj )
        {
            return true;
        }

        if ( !( aObj instanceof ArtifactEffect ) )
        {
            return false;
        }

        final ArtifactEffect<?> that = (ArtifactEffect<?>) aObj;

        return Objects.equals( getEffectValue(), that.getEffectValue() )
                && getEffectApplyingMode() == that.getEffectApplyingMode()
                    && getApplierTarget() == that.getApplierTarget();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( getEffectValue(), getEffectApplyingMode(), getApplierTarget() );
    }
}
