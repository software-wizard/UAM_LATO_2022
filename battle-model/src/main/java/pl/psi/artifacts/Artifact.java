package pl.psi.artifacts;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Artifact implements ArtifactIf {

    private final ArtifactRank rank;

    private final ArtifactPlacement placement;

    private final String name;

    private final String description;

    private final double price;

    private final Set<ArtifactEffect> effects;

    private final ArtifactType type;

}
