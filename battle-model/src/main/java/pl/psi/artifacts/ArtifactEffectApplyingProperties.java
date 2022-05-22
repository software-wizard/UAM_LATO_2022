package pl.psi.artifacts;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.psi.artifacts.model.ArtifactApplyingMode;


@Getter
@RequiredArgsConstructor
@Builder
public class ArtifactEffectApplyingProperties {

  private final ArtifactApplyingMode applyingMode;
  private final BigDecimal effectValue;
  private final int currentValue;
  private final int baseValue;
}
