package pl.psi.artifacts.model;

import pl.psi.artifacts.ArtifactEffectApplicable;

import java.math.BigDecimal;
import java.util.Set;

public interface ArtifactIf {
    ArtifactTarget getTarget();

    ArtifactRank getRank();

    ArtifactPlacement getPlacement();

    String getName();

    String getDescription();

    Set<ArtifactEffect<ArtifactEffectApplicable>> getEffects();
}
