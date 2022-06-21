package pl.psi.artifacts.holder;

import pl.psi.artifacts.model.ArtifactTarget;

public enum CreatureArtifactNamesHolder implements ArtifactNamesHolder{
    RING_OF_VITALITY,

    RING_OF_LIFE,

    VIAL_OF_LIFEBLOOD,

    GARNITURE_OF_INTERFERENCE,

    SURCOAT_OF_COUNTERPOISE,

    BOOTS_OF_POLARITY;

    @Override
    public ArtifactTarget getHolderTarget()
    {
        return ArtifactTarget.CREATURES;
    }
}
