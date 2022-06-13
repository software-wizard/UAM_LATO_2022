package pl.psi.artifacts;

import lombok.NonNull;
import pl.psi.artifacts.holder.CreatureArtifactNamesHolder;
import pl.psi.artifacts.holder.SkillArtifactNamesHolder;
import pl.psi.artifacts.holder.SpellArtifactNamesHolder;
import pl.psi.shop.Money;

public class EconomyArtifactFactory {

    private static final String NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE = "We don't have artifact with this name. Try again.";

//    public Artifact create(final ArtifactNamesHolder name) {
//
//
//        switch (name) {
//
//            case RING:
//                return new Artifact(ArtifactPlacement.SHOULDERS, name, new Money(300));
//            case "Crown of Dragontooth":
//                return new Artifact(ArtifactPlacement.HEAD, name, new Money(300));
//            case "Blackshard of the Dead Knight":
//                return new Artifact(ArtifactPlacement.RIGHT_HAND, name, new Money(300));
//            default:
//                throw new IllegalArgumentException(EXCEPTION_MESSAGE);
//        }
    public Artifact create( @NonNull CreatureArtifactNamesHolder aArtifactName ) {

        switch ( aArtifactName ) {
            case RING_OF_LIFE:
                return new Artifact( ArtifactPlacement.FINGERS, "Ring of Life", new Money(10),
                        aArtifactName );

            case RING_OF_VITALITY:
                return new Artifact( ArtifactPlacement.FINGERS, "Ring of Vitality", new Money(15),
                        aArtifactName );

            case VIAL_OF_LIFEBLOOD:
                return new Artifact( ArtifactPlacement.MISC, "Vial of Lifeblood", new Money(30),
                        aArtifactName);

            default:
                throw new IllegalArgumentException (NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE);
        }
    }

    public Artifact create( @NonNull SkillArtifactNamesHolder aArtifactName ) {

        switch (aArtifactName) {
            case CENTAURS_AX:

                return new Artifact( ArtifactPlacement.RIGHT_HAND, "Centaur's Ax", new Money(5),
                        aArtifactName);

            case BLACKSHARD_OF_THE_DEAD_KNIGHT:

                return new Artifact( ArtifactPlacement.RIGHT_HAND, "Blackshard of the Dead Knight",
                        new Money(7), aArtifactName);

            case GREATER_GNOLLS_FLAIL:

                return new Artifact( ArtifactPlacement.RIGHT_HAND, "Greater Gnoll's Flail",
                        new Money(8), aArtifactName);

            case OGRES_CLUB_OF_HAVOC:

                return new Artifact( ArtifactPlacement.RIGHT_HAND, "Ogre's Club of Havoc",
                        new Money(10), aArtifactName);

            case SWORD_OF_HELLFIRE:

                return new Artifact( ArtifactPlacement.RIGHT_HAND, "Sword Of Hellfire",
                        new Money(12), aArtifactName);

            case TITANS_GLADIUS:

                return new Artifact( ArtifactPlacement.RIGHT_HAND, "Titan's Gladius",
                        new Money(25), aArtifactName);

            case SHIELD_OF_THE_DWARVEN_LORDS:

                return new Artifact( ArtifactPlacement.LEFT_HAND, "Shield of the Dwarven Lords",
                        new Money(5), aArtifactName);

            case TARG_OF_THE_RAMPAGING_OGRE:

                return new Artifact( ArtifactPlacement.LEFT_HAND, "Targ of the Rampaging Ogre",
                        new Money(10), aArtifactName);

            case SENTLINELS_SHIELD:

                return new Artifact( ArtifactPlacement.LEFT_HAND, "Sentlinel's Shield",
                        new Money(25), aArtifactName);

            case RIB_CAGE:

                return new Artifact( ArtifactPlacement.TORSO, "Rib Cage",
                        new Money(5), aArtifactName);

            case TUNIC_OF_THE_CYCLOPS_KING:

                return new Artifact( ArtifactPlacement.TORSO, "Tunic of the Cyclops King",
                        new Money(10), aArtifactName);

            case TITANS_CUIRAS:

                return new Artifact( ArtifactPlacement.TORSO, "Titan's Cuiras",
                        new Money(25), aArtifactName);

            case SKULL_HELMET:

                return new Artifact( ArtifactPlacement.TORSO, "Skull Helmet",
                        new Money(5), aArtifactName);

            case CROWN_OF_THE_SUPREME_MAGI:

                return new Artifact( ArtifactPlacement.TORSO, "Crown of the Supreme Magi",
                        new Money(10), aArtifactName);

            case THUNDER_HELMET:

                return new Artifact( ArtifactPlacement.TORSO, "Thunder Helmet",
                        new Money(25), aArtifactName);

            default:
                throw new IllegalArgumentException ( NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE );
        }
    }

    public Artifact create(SpellArtifactNamesHolder aArtifactName){

        switch ( aArtifactName ) {

            case ORB_OF_SILT:
                return new Artifact( ArtifactPlacement.MISC, "Orb of the Silt",
                        new Money(20), aArtifactName);

            case ORB_OF_DRIVING_RAIN:
                return new Artifact( ArtifactPlacement.MISC, "Orb of the Driving Rain",
                        new Money(20), aArtifactName);

            case ORB_OF_THE_FIRMAMENT:
                return new Artifact( ArtifactPlacement.MISC, "Orb of the Firmament",
                        new Money(20), aArtifactName);

            case ORB_OF_TEMPSTUOUS_FIRE:
                return new Artifact( ArtifactPlacement.MISC, "Orb of Tempstuous Fire",
                        new Money(20), aArtifactName);

            default:
                throw new IllegalArgumentException ( NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE );
        }

    }

}
