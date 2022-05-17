package pl.psi.artifacts;

import java.math.BigDecimal;
import java.util.Set;

public interface ArtifactIf {
    ArtifactRank getRank();

    ArtifactPlacement getPlacement();

    String getName();

    String getDescription();

    BigDecimal getPrice();

    Set< ArtifactEffect< ArtifactEffectApplicable > > getEffects();
}
