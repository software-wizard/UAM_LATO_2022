package pl.psi.artifacts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.psi.artifacts.ArtifactEffectApplicable;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Getter
@AllArgsConstructor
@Builder
public class Artifact implements ArtifactIf {
    private final ArtifactTarget target;

    private final ArtifactRank rank;

    private final ArtifactPlacement placement;

    private final String name;

    private final Set<ArtifactEffect<ArtifactEffectApplicable>> effects;

    public void applyTo(final ArtifactEffectApplicable aArtifactEffectApplicable) {
        effects.forEach(artifactEffect -> artifactEffect.apply(aArtifactEffectApplicable));
    }

    @Override
    public boolean equals(final Object aObj) {
        if (this == aObj) {
            return true;
        }

        if (!(aObj instanceof Artifact)) {
            return false;
        }

        final Artifact artifact = (Artifact) aObj;

        return getRank() == artifact.getRank() && getPlacement() == artifact.getPlacement()
                && Objects.equals(getEffects(), artifact.getEffects());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRank(), getPlacement(), getEffects());
    }
}
