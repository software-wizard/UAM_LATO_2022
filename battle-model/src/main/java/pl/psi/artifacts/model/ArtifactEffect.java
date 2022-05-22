package pl.psi.artifacts.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import pl.psi.artifacts.ArtifactEffectApplicable;
import pl.psi.artifacts.ArtifactEffectApplyingProperties;
import pl.psi.artifacts.CreatureArtifactApplicableProperty;

@Getter
@Builder
public class ArtifactEffect<T extends ArtifactEffectApplicable> {

    private final BigDecimal effectValue;

    private final ArtifactApplyingMode effectApplyingMode;

    private final CreatureArtifactApplicableProperty applierTarget;

    public int calculateStatisticValueAfterApplying(final ArtifactEffectApplyingProperties effectStats) {
        BigDecimal result;
        switch (effectStats.getApplyingMode()) {
            case ADD:
                result =
                    effectStats.getEffectValue()
                        .add(BigDecimal.valueOf(effectStats.getCurrentValue()));
                break;
            case MULTIPLY:
                result =
                    effectStats.getEffectValue()
                        .multiply(BigDecimal.valueOf(effectStats.getBaseValue()))
                        .add(BigDecimal.valueOf(effectStats.getCurrentValue()));
                break;
            default:
                throw new UnsupportedOperationException("Unrecognised artifact applying mode");
        }

        return result.setScale(0, RoundingMode.FLOOR).intValueExact();
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
        return getEffectValue().equals(that.getEffectValue())
            && getEffectApplyingMode() == that.getEffectApplyingMode()
            && getApplierTarget() == that.getApplierTarget();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEffectValue(), getEffectApplyingMode(), getApplierTarget());
    }
}
