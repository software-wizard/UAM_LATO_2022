package pl.psi.artifacts;

import pl.psi.artifacts.model.ArtifactTarget;

/**
 * Possible properties to apply Skill Artifacts to.
 */
public enum SkillArtifactApplicableProperty implements ArtifactApplicableProperty
{
    ATTACK,

    DEFENCE,

    SPELL_POWER,

    KNOWLEDGE;

    @Override
    public ArtifactTarget getArtifactTarget()
    {
        return ArtifactTarget.SKILL;
    }
}
