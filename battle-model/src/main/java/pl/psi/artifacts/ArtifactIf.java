package pl.psi.artifacts;

import java.util.Set;

public interface ArtifactIf
{
    ArtifactRank getRank();

    ArtifactPlacement getPlacement();

    String getName();

    String getDescription();

    double getPrice();

    Set< ArtifactEffect > getEffects();
}
