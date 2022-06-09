package pl.psi.artifacts;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@Builder
public class ArtifactEffectApplyingProperties {

    private final double baseValue;
    private final double upgradedValue;
}
