package pl.psi.artifacts;

import pl.psi.artifacts.model.ArtifactTarget;

/**
 * Possible properties to apply Creature Artifacts to.
 */
public enum CreatureArtifactApplicableProperty implements ArtifactApplicableProperty
{
  HEALTH,

  ATTACK,

  DEFENCE;


  @Override
  public ArtifactTarget getArtifactTarget()
  {
      return ArtifactTarget.CREATURES;
  }
}
