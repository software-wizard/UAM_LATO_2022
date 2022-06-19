package pl.psi.artifacts.holder;

import pl.psi.artifacts.model.ArtifactTarget;

public enum SpellArtifactNamesHolder implements ArtifactNamesHolder{
    ORB_OF_THE_FIRMAMENT,

    ORB_OF_SILT,

    ORB_OF_TEMPSTUOUS_FIRE,

    ORB_OF_DRIVING_RAIN;


    @Override
    public ArtifactTarget getHolderTarget()
    {
        return ArtifactTarget.SPELLS;
    }
}
