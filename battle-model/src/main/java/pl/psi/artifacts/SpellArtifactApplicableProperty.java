package pl.psi.artifacts;

import pl.psi.artifacts.model.ArtifactTarget;

/**
 * Possible properties to apply Spell Artifacts to.
 */
public enum SpellArtifactApplicableProperty implements ArtifactApplicableProperty {
    AIR_DAMAGE,

    EARTH_DAMAGE,

    FIRE_DAMAGE,

    WATER_DAMAGE,

    DURATION;

    @Override
    public final ArtifactTarget getArtifactTarget() {
        return ArtifactTarget.SPELLS;
    }
}
