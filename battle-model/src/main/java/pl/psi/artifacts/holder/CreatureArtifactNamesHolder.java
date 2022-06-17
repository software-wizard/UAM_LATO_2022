package pl.psi.artifacts.holder;

import pl.psi.artifacts.model.ArtifactTarget;

public enum CreatureArtifactNamesHolder implements ArtifactNamesHolder{
    RING_OF_VITALITY,

    RING_OF_LIFE,

    VIAL_OF_LIFEBLOOD;


    @Override
    public ArtifactTarget getHolderTarget()
    {
        return ArtifactTarget.CREATURES;
    }
}
