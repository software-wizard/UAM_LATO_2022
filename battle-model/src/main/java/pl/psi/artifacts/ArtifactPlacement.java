package pl.psi.artifacts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
enum ArtifactPlacement
{
    HEAD( "HEAD" ),

    NECK( "NECK" ),

    TORSO( "TORSO" ),

    SHOULDERS( "SHOULDERS" ),

    RIGHT_HAND( "RIGHT HAND" ),

    LEFT_HAND( "LEFT HAND" ),

    FEET( "FEET" );

    private final String placement;

    @Override
    public String toString()
    {
        return placement;
    }
}
