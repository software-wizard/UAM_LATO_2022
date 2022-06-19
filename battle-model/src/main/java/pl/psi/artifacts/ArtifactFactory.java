package pl.psi.artifacts;

import lombok.NonNull;
import pl.psi.artifacts.holder.ArtifactNamesHolder;
import pl.psi.artifacts.holder.CreatureArtifactNamesHolder;
import pl.psi.artifacts.holder.SkillArtifactNamesHolder;
import pl.psi.artifacts.holder.SpellArtifactNamesHolder;
import pl.psi.artifacts.model.*;

import java.math.BigDecimal;
import java.util.Set;

// TODO: rethink price of Artifacts!

/**
 * Factory for creation of Creature, Spell and Skill Artefacts.
 */
public class ArtifactFactory {
    private static final String NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE = "No implementation provided for artifact of that name.";

    private static final String NO_HOLDER_IMPLEMENTATION_EXCEPTION_MESSAGE = "No implementation provided for artifacts of such holder.";

    public ArtifactIf createArtifact( @NonNull ArtifactNamesHolder aArtifactName )
    {
        ArtifactTarget artifactTarget = aArtifactName.getHolderTarget();
        switch ( artifactTarget )
        {
            case CREATURES:
                return createArtifact( (CreatureArtifactNamesHolder) aArtifactName );
            case SKILL:
                return createArtifact( (SkillArtifactNamesHolder) aArtifactName );
            case SPELLS:
                return createArtifact( (SpellArtifactNamesHolder) aArtifactName );
            default:
                throw new UnsupportedOperationException( NO_HOLDER_IMPLEMENTATION_EXCEPTION_MESSAGE );
        }
    }

    ArtifactIf createArtifact(@NonNull CreatureArtifactNamesHolder aArtifactName) {
        switch (aArtifactName) {
            case RING_OF_LIFE:

                return createRingOfLifeArtifact();

            case RING_OF_VITALITY:

                return createRingOfVitalityArtifact();

            case VIAL_OF_LIFEBLOOD:

                return createVialOfLifebloodArtifact();

            case GARNITURE_OF_INTERFERENCE:

                return createGarnitureOfInterferenceArtifact();

            case SURCOAT_OF_COUNTERPOISE:

                return createsurCoatOfCounterpoiseArtifact();

            case BOOTS_OF_POLARITY:

                return createBootsOfPolarityArtifact();

            default:

                throw new IllegalArgumentException(NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE);
        }
    }

    public ArtifactIf createArtifact(@NonNull SkillArtifactNamesHolder aArtifactName) {
        switch (aArtifactName) {
            case CENTAURS_AX:

                return createCentaursAxArtifact();

            case BLACKSHARD_OF_THE_DEAD_KNIGHT:

                return createBlackshardOfTheDeadKnight();

            case GREATER_GNOLLS_FLAIL:

                return createGreaterGnollsFlail();

            case OGRES_CLUB_OF_HAVOC:

                return createOgresClubOfHavoc();

            case SWORD_OF_HELLFIRE:

                return createSwordOfHellfire();

            case TITANS_GLADIUS:

                return createTitansGladius();

            case SHIELD_OF_THE_DWARVEN_LORDS:

                return createShieldOfTheDwarvenLords();

            case TARG_OF_THE_RAMPAGING_OGRE:

                return createTargOfTheRampagingOgre();

            case SENTLINELS_SHIELD:

                return createSentinelsShield();

            case RIB_CAGE:

                return createRibCage();

            case TUNIC_OF_THE_CYCLOPS_KING:

                return createTunicOfTheCyclopsKing();

            case TITANS_CUIRAS:

                return createTitansCuiras();

            case SKULL_HELMET:

                return createSkullHelmet();

            case CROWN_OF_THE_SUPREME_MAGI:

                return createCrownOfTheSupremeMagi();

            case THUNDER_HELMET:

                return createThunderHelmet();

            default:

                throw new IllegalArgumentException(NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE);
        }
    }

    public ArtifactIf createArtifact(@NonNull SpellArtifactNamesHolder aArtifactName) {
        switch (aArtifactName) {

            case ORB_OF_THE_FIRMAMENT:

                return createOrbOfTheFirmament();

            case ORB_OF_SILT:

                return createOrbOfSilt();

            case ORB_OF_TEMPSTUOUS_FIRE:

                return createOrbOfTempstuousFire();

            case ORB_OF_DRIVING_RAIN:

                return createOrbOfDrivingRain();

            default:
                throw new IllegalArgumentException(NO_ARTIFACT_IMPLEMENTATION_EXCEPTION_MESSAGE);

        }
    }

    private ArtifactIf createRingOfLifeArtifact() {
        final ArtifactEffect<ArtifactEffectApplicable> ringOfLifeEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(1))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(CreatureArtifactApplicableProperty.HEALTH)
                .build();

        return Artifact.builder()
                .name("Ring of Life")
                .placement(ArtifactPlacement.FINGERS)
                .rank(ArtifactRank.MINOR)
                .target(ArtifactTarget.CREATURES)
                .effects(Set.of(ringOfLifeEffect))
                .build();
    }

    private ArtifactIf createRingOfVitalityArtifact() {
        final ArtifactEffect<ArtifactEffectApplicable> rightOfVitalityEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(1))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(CreatureArtifactApplicableProperty.ATTACK)
                .build();

        return Artifact.builder()
                .name("Ring of Vitality")
                .placement(ArtifactPlacement.FINGERS)
                .rank(ArtifactRank.TREASURE)
                .target(ArtifactTarget.CREATURES)
                .effects(Set.of(rightOfVitalityEffect))
                .build();
    }

    private ArtifactIf createVialOfLifebloodArtifact() {
        final ArtifactEffect<ArtifactEffectApplicable> vialOfLifebloodEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(2))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(CreatureArtifactApplicableProperty.HEALTH)
                .build();

        return Artifact.builder()
                .name("Vial of Lifeblood")
                .placement(ArtifactPlacement.MISC)
                .rank(ArtifactRank.TREASURE)
                .target(ArtifactTarget.CREATURES)
                .effects(Set.of(vialOfLifebloodEffect))
                .build();
    }

    private ArtifactIf createGarnitureOfInterferenceArtifact() {
        final ArtifactEffect<ArtifactEffectApplicable> garnitureOfInterferenceEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(1.05))
                .effectApplyingMode(ArtifactApplyingMode.MULTIPLY)
                .applierTarget(CreatureArtifactApplicableProperty.DEFENCE)
                .build();

        return Artifact.builder()
                .name("Garniture of Interference")
                .placement(ArtifactPlacement.NECK)
                .rank(ArtifactRank.MAJOR)
                .target(ArtifactTarget.CREATURES)
                .effects(Set.of(garnitureOfInterferenceEffect))
                .build();
    }

    private ArtifactIf createsurCoatOfCounterpoiseArtifact() {
        final ArtifactEffect<ArtifactEffectApplicable> coatOfCounterpoiseEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(1.1))
                .effectApplyingMode(ArtifactApplyingMode.MULTIPLY)
                .applierTarget(CreatureArtifactApplicableProperty.DEFENCE)
                .build();

        return Artifact.builder()
                .name("Coat of Counterpoise")
                .placement(ArtifactPlacement.SHOULDERS)
                .rank(ArtifactRank.MAJOR)
                .target(ArtifactTarget.CREATURES)
                .effects(Set.of(coatOfCounterpoiseEffect))
                .build();
    }

    private ArtifactIf createBootsOfPolarityArtifact() {
        final ArtifactEffect<ArtifactEffectApplicable> bootsOfPolarityEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(1.15))
                .effectApplyingMode(ArtifactApplyingMode.MULTIPLY)
                .applierTarget(CreatureArtifactApplicableProperty.DEFENCE)
                .build();

        return Artifact.builder()
                .name("Boots of Polarity")
                .placement(ArtifactPlacement.FEET)
                .rank(ArtifactRank.RELIC)
                .target(ArtifactTarget.CREATURES)
                .effects(Set.of(bootsOfPolarityEffect))
                .build();
    }

    private ArtifactIf createCentaursAxArtifact() {
        final ArtifactEffect<ArtifactEffectApplicable> centaursAxEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(2))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.ATTACK)
                .build();

        return Artifact.builder()
                .name("Centaur's Ax")
                .placement(ArtifactPlacement.RIGHT_HAND)
                .rank(ArtifactRank.TREASURE)
                .target(ArtifactTarget.SKILL)
                .effects(Set.of(centaursAxEffect))
                .build();
    }

    private ArtifactIf createBlackshardOfTheDeadKnight() {
        final ArtifactEffect<ArtifactEffectApplicable> blackshardOfTheDeadKnightEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(3))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.ATTACK)
                .build();

        return Artifact.builder()
                .name("Blackshard of the Dead Knight")
                .placement(ArtifactPlacement.RIGHT_HAND)
                .rank(ArtifactRank.MINOR)
                .target(ArtifactTarget.SKILL)
                .effects(Set.of(blackshardOfTheDeadKnightEffect))
                .build();
    }

    private ArtifactIf createGreaterGnollsFlail() {
        final ArtifactEffect<ArtifactEffectApplicable> greaterGnollsFlailEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(4))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.ATTACK)
                .build();

        return Artifact.builder()
                .name("Greater Gnoll's Flail")
                .placement(ArtifactPlacement.RIGHT_HAND)
                .rank(ArtifactRank.MINOR)
                .target(ArtifactTarget.SKILL)
                .effects(Set.of(greaterGnollsFlailEffect))
                .build();
    }

    private ArtifactIf createOgresClubOfHavoc() {
        final ArtifactEffect<ArtifactEffectApplicable> ogresClubOfHavocEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(5))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.ATTACK)
                .build();

        return Artifact.builder()
                .name("Ogre's Club of Havoc")
                .placement(ArtifactPlacement.RIGHT_HAND)
                .rank(ArtifactRank.MAJOR)
                .target(ArtifactTarget.SKILL)
                .effects(Set.of(ogresClubOfHavocEffect))
                .build();
    }

    private ArtifactIf createSwordOfHellfire() {
        final ArtifactEffect<ArtifactEffectApplicable> swordOfHellfireEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(6))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.ATTACK)
                .build();

        return Artifact.builder()
                .name("Sword of Hellfire")
                .placement(ArtifactPlacement.RIGHT_HAND)
                .rank(ArtifactRank.MAJOR)
                .target(ArtifactTarget.SKILL)
                .effects(Set.of(swordOfHellfireEffect))
                .build();
    }

    private ArtifactIf createTitansGladius() {
        final ArtifactEffect<ArtifactEffectApplicable> titansGladiusEffectAttack = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(12))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.ATTACK)
                .build();

        final ArtifactEffect<ArtifactEffectApplicable> titansGladiusEffectDefence = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(-3))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.DEFENCE)
                .build();

        return Artifact.builder()
                .name("Titan's Gladius")
                .placement(ArtifactPlacement.RIGHT_HAND)
                .rank(ArtifactRank.RELIC)
                .target(ArtifactTarget.SKILL)
                .effects(Set.of(titansGladiusEffectAttack, titansGladiusEffectDefence))
                .build();
    }

    private ArtifactIf createShieldOfTheDwarvenLords() {
        final ArtifactEffect<ArtifactEffectApplicable> shieldOfTheDwarvenLordsEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(2))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.DEFENCE)
                .build();

        return Artifact.builder()
                .name("Shield of the Dwarven Lords")
                .placement(ArtifactPlacement.LEFT_HAND)
                .rank(ArtifactRank.TREASURE)
                .target(ArtifactTarget.SKILL)
                .effects(Set.of(shieldOfTheDwarvenLordsEffect))
                .build();
    }

    private ArtifactIf createTargOfTheRampagingOgre() {
        final ArtifactEffect<ArtifactEffectApplicable> targOfTheRampagingOgreEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(5))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.DEFENCE)
                .build();

        return Artifact.builder()
                .name("Targ of the Rampaging Ogre")
                .placement(ArtifactPlacement.LEFT_HAND)
                .rank(ArtifactRank.MAJOR)
                .target(ArtifactTarget.SKILL)
                .effects(Set.of(targOfTheRampagingOgreEffect))
                .build();
    }

    private ArtifactIf createSentinelsShield() {
        final ArtifactEffect<ArtifactEffectApplicable> sentinelsShieldEffectAttack = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(-3))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.ATTACK)
                .build();

        final ArtifactEffect<ArtifactEffectApplicable> sentinelsShieldEffectDefence = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(12))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.DEFENCE)
                .build();

        return Artifact.builder()
                .name("Titan's Gladius")
                .placement(ArtifactPlacement.LEFT_HAND)
                .rank(ArtifactRank.RELIC)
                .target(ArtifactTarget.SKILL)
                .effects(Set.of(sentinelsShieldEffectDefence, sentinelsShieldEffectAttack))
                .build();
    }

    private ArtifactIf createRibCage() {
        final ArtifactEffect<ArtifactEffectApplicable> ribCageEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(2))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.SPELL_POWER)
                .build();

        return Artifact.builder()
                .name("Rib Cage")
                .placement(ArtifactPlacement.TORSO)
                .rank(ArtifactRank.MINOR)
                .target(ArtifactTarget.SKILL)
                .effects(Set.of(ribCageEffect))
                .build();
    }

    private ArtifactIf createTunicOfTheCyclopsKing() {
        final ArtifactEffect<ArtifactEffectApplicable> tunicOfTheCyclopsKingEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(4))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.SPELL_POWER)
                .build();

        return Artifact.builder()
                .name("Tunic of the Cyclops King")
                .placement(ArtifactPlacement.TORSO)
                .rank(ArtifactRank.MAJOR)
                .target(ArtifactTarget.SKILL)
                .effects(Set.of(tunicOfTheCyclopsKingEffect))
                .build();
    }

    private ArtifactIf createTitansCuiras() {
        final ArtifactEffect<ArtifactEffectApplicable> titansCuirasEffectSpell = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(10))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.SPELL_POWER)
                .build();

        final ArtifactEffect<ArtifactEffectApplicable> titansCuirasEffectKnowledge = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(-2))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.KNOWLEDGE)
                .build();

        return Artifact.builder()
                .name("Titan's Cuiras")
                .placement(ArtifactPlacement.TORSO)
                .rank(ArtifactRank.RELIC)
                .target(ArtifactTarget.SKILL)
                .effects(Set.of(titansCuirasEffectSpell, titansCuirasEffectKnowledge))
                .build();
    }

    private ArtifactIf createSkullHelmet() {
        final ArtifactEffect<ArtifactEffectApplicable> skullHelmetEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(2))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.KNOWLEDGE)
                .build();

        return Artifact.builder()
                .name("Skull Helmet")
                .placement(ArtifactPlacement.HEAD)
                .rank(ArtifactRank.TREASURE)
                .target(ArtifactTarget.SKILL)
                .effects(Set.of(skullHelmetEffect))
                .build();
    }

    private ArtifactIf createCrownOfTheSupremeMagi() {
        final ArtifactEffect<ArtifactEffectApplicable> crownOfTheSupremeMagiEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(4))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.KNOWLEDGE)
                .build();

        return Artifact.builder()
                .name("rown of the Supreme Magi")
                .placement(ArtifactPlacement.HEAD)
                .rank(ArtifactRank.MINOR)
                .target(ArtifactTarget.SKILL)
                .effects(Set.of(crownOfTheSupremeMagiEffect))
                .build();
    }

    private ArtifactIf createThunderHelmet() {
        final ArtifactEffect<ArtifactEffectApplicable> thunderHelmetEffectSpell = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(-2))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.SPELL_POWER)
                .build();

        final ArtifactEffect<ArtifactEffectApplicable> thunderHelmetEffectKnowledge = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(10))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(SkillArtifactApplicableProperty.KNOWLEDGE)
                .build();

        return Artifact.builder()
                .name("Thunder Helmet")
                .placement(ArtifactPlacement.TORSO)
                .rank(ArtifactRank.RELIC)
                .target(ArtifactTarget.SKILL)
                .effects(Set.of(thunderHelmetEffectSpell, thunderHelmetEffectKnowledge))
                .build();
    }

    private ArtifactIf createOrbOfTheFirmament() {
        final ArtifactEffect<ArtifactEffectApplicable> orbOfTheFirmamentEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(1.5))
                .effectApplyingMode(ArtifactApplyingMode.MULTIPLY)
                .applierTarget(SpellArtifactApplicableProperty.AIR_DAMAGE)
                .build();

        return Artifact.builder()
                .name("Orb Of The Firmament")
                .placement(ArtifactPlacement.MISC)
                .rank(ArtifactRank.MAJOR)
                .target(ArtifactTarget.SPELLS)
                .effects(Set.of(orbOfTheFirmamentEffect))
                .build();
    }

    private ArtifactIf createOrbOfSilt() {
        final ArtifactEffect<ArtifactEffectApplicable> orbOfSiltEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(1.5))
                .effectApplyingMode(ArtifactApplyingMode.MULTIPLY)
                .applierTarget(SpellArtifactApplicableProperty.EARTH_DAMAGE)
                .build();

        return Artifact.builder()
                .name("Orb Of Silt")
                .placement(ArtifactPlacement.MISC)
                .rank(ArtifactRank.MAJOR)
                .target(ArtifactTarget.SPELLS)
                .effects(Set.of(orbOfSiltEffect))
                .build();
    }

    private ArtifactIf createOrbOfTempstuousFire() {
        final ArtifactEffect<ArtifactEffectApplicable> orbOfTempstuousFireEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(1.5))
                .effectApplyingMode(ArtifactApplyingMode.MULTIPLY)
                .applierTarget(SpellArtifactApplicableProperty.FIRE_DAMAGE)
                .build();

        return Artifact.builder()
                .name("Orb Of Tempstuous Fire")
                .placement(ArtifactPlacement.MISC)
                .rank(ArtifactRank.MAJOR)
                .target(ArtifactTarget.SPELLS)
                .effects(Set.of(orbOfTempstuousFireEffect))
                .build();
    }

    private ArtifactIf createOrbOfDrivingRain() {
        final ArtifactEffect<ArtifactEffectApplicable> orbOfDrivingRainEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(1.5))
                .effectApplyingMode(ArtifactApplyingMode.MULTIPLY)
                .applierTarget(SpellArtifactApplicableProperty.WATER_DAMAGE)
                .build();

        return Artifact.builder()
                .name("Orb Of Driving Rain")
                .placement(ArtifactPlacement.MISC)
                .rank(ArtifactRank.MAJOR)
                .target(ArtifactTarget.SPELLS)
                .effects(Set.of(orbOfDrivingRainEffect))
                .build();
    }
}
