package pl.psi.hero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@AllArgsConstructor
@Builder
public class Artifact {


    private final String name;

    private final String description;

    private final BigDecimal price;
    private final ArtifactPlacement placement;
}
