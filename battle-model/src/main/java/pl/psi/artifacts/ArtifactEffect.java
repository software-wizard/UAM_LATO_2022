package pl.psi.artifacts;

import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArtifactEffect<T extends ArtifactEffectApplicable> {

    private final double effectValue;

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
        return Double.compare(that.getEffectValue(), getEffectValue()) == 0
            && getEffectApplyingMode() == that.getEffectApplyingMode()
            && getApplierTarget() == that.getApplierTarget();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEffectValue(), getEffectApplyingMode(), getApplierTarget());
    }
}
