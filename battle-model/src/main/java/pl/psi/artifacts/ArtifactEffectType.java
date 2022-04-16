package pl.psi.artifacts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
enum ArtifactEffectType
{
    HEALTH( "HEALTH" ),

    ATTACK( "ATTACK" ),

    DEFENCE( "DEFENCE" ),

    SPELL_POWER( "SPELL POWER" ),

    KNOWLEDGE( "KNOWLEDGE" ),

    PRIMARY( "PRIMARY" ),

    MORALE( "MORALE" ),

    LUCK( "LUCK" ),

    FIRE_SPELLS("FIRE SPELLS" ),

    EARTH_SPELLS( "EARTH SPELLS" ),

    AIR_SPELLS( "AIR SPELLS" ),

    WATTER_SPELLS( "WATER SPELLS" );

    private final String effectName;

    @Override
    public String toString()
    {
        return effectName;
    }
}
