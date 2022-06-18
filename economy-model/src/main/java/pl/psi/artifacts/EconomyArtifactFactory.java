package pl.psi.artifacts;

import lombok.NonNull;
import pl.psi.artifacts.holder.CreatureArtifactNamesHolder;
import pl.psi.artifacts.holder.SkillArtifactNamesHolder;
import pl.psi.artifacts.holder.SpellArtifactNamesHolder;
import pl.psi.shop.Money;

public class EconomyArtifactFactory {

    private static final String NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE = "We don't have artifact with this name. Try again.";

    public EconomyArtifact create(@NonNull CreatureArtifactNamesHolder aArtifactName ) {

        switch ( aArtifactName ) {
            case RING_OF_LIFE:
                return new EconomyArtifact( ArtifactPlacement.FINGERS, "Ring of Life", new Money(10),
                        aArtifactName, "Increases health of all your units by 1" );

            case RING_OF_VITALITY:
                return new EconomyArtifact( ArtifactPlacement.FINGERS, "Ring of Vitality", new Money(15),
                        aArtifactName, "Increases health of all your units by 1" );

            case VIAL_OF_LIFEBLOOD:
                return new EconomyArtifact( ArtifactPlacement.MISC, "Vial of Lifeblood", new Money(30),
                        aArtifactName, "Increases health of all your units by 2" );

            case GARNITURE_OF_INTERFERENCE:
                return new EconomyArtifact( ArtifactPlacement.NECK, "Garniture Of Interference", new Money (5),
                        aArtifactName, "Increases armor of all your units by 5%");

            case SURCOAT_OF_COUNTERPOISE:
                return new EconomyArtifact( ArtifactPlacement.SHOULDERS, "Surcoat Of Counterpoise", new Money (10),
                        aArtifactName, "Increases armor of all your units by 10%");

            case BOOTS_OF_POLARITY:
                return new EconomyArtifact( ArtifactPlacement.FEET, "Boots Of Polarity", new Money (15),
                        aArtifactName, "Increases armor of all your units by 10%");

            default:
                throw new IllegalArgumentException (NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE);
        }
    }

    public EconomyArtifact create(@NonNull SkillArtifactNamesHolder aArtifactName ) {

        switch (aArtifactName) {
            case CENTAURS_AX:

                return new EconomyArtifact( ArtifactPlacement.RIGHT_HAND, "Centaur's Ax", new Money(5),
                        aArtifactName, "+2 attack skill" );

            case BLACKSHARD_OF_THE_DEAD_KNIGHT:

                return new EconomyArtifact( ArtifactPlacement.RIGHT_HAND, "Blackshard of the Dead Knight",
                        new Money(7), aArtifactName, "+3 attack skill" );

            case GREATER_GNOLLS_FLAIL:

                return new EconomyArtifact( ArtifactPlacement.RIGHT_HAND, "Greater Gnoll's Flail",
                        new Money(8), aArtifactName, "+4 attack skill" );

            case OGRES_CLUB_OF_HAVOC:

                return new EconomyArtifact( ArtifactPlacement.RIGHT_HAND, "Ogre's Club of Havoc",
                        new Money(10), aArtifactName, "+5 attack skill" );

            case SWORD_OF_HELLFIRE:

                return new EconomyArtifact( ArtifactPlacement.RIGHT_HAND, "Sword Of Hellfire",
                        new Money(12), aArtifactName, "+6 attack skill" );

            case TITANS_GLADIUS:

                return new EconomyArtifact( ArtifactPlacement.RIGHT_HAND, "Titan's Gladius",
                        new Money(25), aArtifactName, "+12 attack skill, -3 defence skill");

            case SHIELD_OF_THE_DWARVEN_LORDS:

                return new EconomyArtifact( ArtifactPlacement.LEFT_HAND, "Shield of the Dwarven Lords",
                        new Money(5), aArtifactName, "+2 defence skill" );

            case TARG_OF_THE_RAMPAGING_OGRE:

                return new EconomyArtifact( ArtifactPlacement.LEFT_HAND, "Targ of the Rampaging Ogre",
                        new Money(10), aArtifactName, "+5 defence skill");

            case SENTLINELS_SHIELD:

                return new EconomyArtifact( ArtifactPlacement.LEFT_HAND, "Sentlinel's Shield",
                        new Money(25), aArtifactName, "+12 defence skill, -3 attack skill");

            case RIB_CAGE:

                return new EconomyArtifact( ArtifactPlacement.TORSO, "Rib Cage",
                        new Money(5), aArtifactName, "+2 spell power" );

            case TUNIC_OF_THE_CYCLOPS_KING:

                return new EconomyArtifact( ArtifactPlacement.TORSO, "Tunic of the Cyclops King",
                        new Money(10), aArtifactName, "+4 spell power" );

            case TITANS_CUIRAS:

                return new EconomyArtifact( ArtifactPlacement.TORSO, "Titan's Cuiras",
                        new Money(25), aArtifactName, "+10 spell power, -2 knowledge" );

            case SKULL_HELMET:

                return new EconomyArtifact( ArtifactPlacement.TORSO, "Skull Helmet",
                        new Money(5), aArtifactName, "+2 knowledge" );

            case CROWN_OF_THE_SUPREME_MAGI:

                return new EconomyArtifact( ArtifactPlacement.TORSO, "Crown of the Supreme Magi",
                        new Money(10), aArtifactName, "+4 knowledge" );

            case THUNDER_HELMET:

                return new EconomyArtifact( ArtifactPlacement.TORSO, "Thunder Helmet",
                        new Money(25), aArtifactName, "+10 knowledge, -2 spell power");

            default:
                throw new IllegalArgumentException ( NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE );
        }
    }

    public EconomyArtifact create(SpellArtifactNamesHolder aArtifactName){

        switch ( aArtifactName ) {

            case ORB_OF_SILT:
                return new EconomyArtifact( ArtifactPlacement.MISC, "Orb of the Silt",
                        new Money(20), aArtifactName, "Hero's earth spells do extra 50% damage" );

            case ORB_OF_DRIVING_RAIN:
                return new EconomyArtifact( ArtifactPlacement.MISC, "Orb of the Driving Rain",
                        new Money(20), aArtifactName, "Hero's water spells do extra 50% damage" );

            case ORB_OF_THE_FIRMAMENT:
                return new EconomyArtifact( ArtifactPlacement.MISC, "Orb of the Firmament",
                        new Money(20), aArtifactName, "Hero's air spells do extra 50% damage" );

            case ORB_OF_TEMPSTUOUS_FIRE:
                return new EconomyArtifact( ArtifactPlacement.MISC, "Orb of Tempstuous Fire",
                        new Money(20), aArtifactName, "Hero's fire spells do extra 50% damage" );

            default:
                throw new IllegalArgumentException ( NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE );
        }

    }

}
