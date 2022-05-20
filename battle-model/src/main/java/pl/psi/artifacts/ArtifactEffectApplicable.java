package pl.psi.artifacts;

import pl.psi.artifacts.model.ArtifactEffect;

/**
 * Interface implemented by objects which can be effected by Artifacts.
 */
public interface ArtifactEffectApplicable
{
  void applyArtifactEffect( ArtifactEffect< ? extends ArtifactEffectApplicable > artifactEffect );
}
