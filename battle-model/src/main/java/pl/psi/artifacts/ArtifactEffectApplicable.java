package pl.psi.artifacts;

import pl.psi.artifacts.model.ArtifactEffect;


public interface ArtifactEffectApplicable
{
  void applyArtifactEffect(ArtifactEffect<? extends ArtifactEffectApplicable> artifactEffect);
}
