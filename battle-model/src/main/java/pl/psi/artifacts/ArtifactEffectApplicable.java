package pl.psi.artifacts;

public interface ArtifactEffectApplicable {

  void applyArtifactEffect(ArtifactEffect<? extends ArtifactEffectApplicable> artifactEffect);
}
