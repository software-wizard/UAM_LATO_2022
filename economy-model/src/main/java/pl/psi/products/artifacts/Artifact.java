package pl.psi.products.artifacts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.psi.products.artifacts.ArtifactPlacement;


@Getter
@AllArgsConstructor
public class Artifact {

    private final ArtifactPlacement placement;

    private final String name;

    private final int price;

}
