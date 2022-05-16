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

    void apply(T obj) {
        obj.applyArtifactEffect(this);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArtifactEffect)) {
            return false;
        }
        final ArtifactEffect<?> that = (ArtifactEffect<?>) o;
        return Objects.equals(getEffectValue(), that.getEffectValue())
            && getEffectApplyingMode() == that.getEffectApplyingMode()
            && getApplierTarget() == that.getApplierTarget();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEffectValue(), getEffectApplyingMode(), getApplierTarget());
    }
}
