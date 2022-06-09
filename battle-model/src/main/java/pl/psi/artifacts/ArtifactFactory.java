package pl.psi.artifacts;

import lombok.NonNull;
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

    public ArtifactIf createArtifact(@NonNull CreatureArtifactNamesHolder aArtifactName) {
        switch (aArtifactName) {
            case RING_OF_LIFE:

                return createRingOfLifeArtifact();

            case RING_OF_VITALITY:

                return createRingOfVitalityArtifact();

            case VIAL_OF_LIFEBLOOD:

                return createVialOfLifebloodArtifact();

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
                .description("Increases health of all your units by 1")
                .placement(ArtifactPlacement.FINGERS)
                .price(BigDecimal.valueOf(10))
                .rank(ArtifactRank.MINOR)
                .target(ArtifactTarget.CREATURES)
                .effects(Set.of(ringOfLifeEffect))
                .build();
    }

    private ArtifactIf createRingOfVitalityArtifact() {
        final ArtifactEffect<ArtifactEffectApplicable> rightOfVitalityEffect = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(1))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(CreatureArtifactApplicableProperty.HEALTH)
                .build();

        return Artifact.builder()
                .name("Ring of Vitality")
                .description("Increases health of all your units by 1")
                .placement(ArtifactPlacement.FINGERS)
                .price(BigDecimal.valueOf(15))
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
                .description("Increases health of all your units by 2")
                .placement(ArtifactPlacement.MISC)
                .price(BigDecimal.valueOf(30))
                .rank(ArtifactRank.TREASURE)
                .target(ArtifactTarget.CREATURES)
                .effects(Set.of(vialOfLifebloodEffect))
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
                .description("+2 attack skill")
                .placement(ArtifactPlacement.RIGHT_HAND)
                .price(BigDecimal.valueOf(5))
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
                .description("+3 attack skill")
                .placement(ArtifactPlacement.RIGHT_HAND)
                .price(BigDecimal.valueOf(7))
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
                .description("+4 attack skill")
                .placement(ArtifactPlacement.RIGHT_HAND)
                .price(BigDecimal.valueOf(8))
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
                .description("+5 attack skill")
                .placement(ArtifactPlacement.RIGHT_HAND)
                .price(BigDecimal.valueOf(10))
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
                .description("+6 attack skill")
                .placement(ArtifactPlacement.RIGHT_HAND)
                .price(BigDecimal.valueOf(12))
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
                .description("+12 attack skill, -3 defence skill")
                .placement(ArtifactPlacement.RIGHT_HAND)
                .price(BigDecimal.valueOf(25))
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
                .description("+2 attack skill")
                .placement(ArtifactPlacement.LEFT_HAND)
                .price(BigDecimal.valueOf(5))
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
                .description("+5 attack skill")
                .placement(ArtifactPlacement.LEFT_HAND)
                .price(BigDecimal.valueOf(10))
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
                .description("+12  defence, -3 attack skill")
                .placement(ArtifactPlacement.LEFT_HAND)
                .price(BigDecimal.valueOf(25))
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
                .description("+2 spell power")
                .placement(ArtifactPlacement.TORSO)
                .price(BigDecimal.valueOf(5))
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
                .description("+4 spell power")
                .placement(ArtifactPlacement.TORSO)
                .price(BigDecimal.valueOf(10))
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
                .description("+10 spell power, -2 knowledge")
                .placement(ArtifactPlacement.TORSO)
                .price(BigDecimal.valueOf(25))
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
                .description("+2 knowledge")
                .placement(ArtifactPlacement.HEAD)
                .price(BigDecimal.valueOf(5))
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
                .description("+4 knowledge")
                .placement(ArtifactPlacement.HEAD)
                .price(BigDecimal.valueOf(10))
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
                .description("+10 knowledge, -2 spell power")
                .placement(ArtifactPlacement.TORSO)
                .price(BigDecimal.valueOf(25))
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
                .description("Hero's air spells do extra 50% damage")
                .placement(ArtifactPlacement.MISC)
                .price(BigDecimal.valueOf(20))
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
                .description("Hero's earth spells do extra 50% damage")
                .placement(ArtifactPlacement.MISC)
                .price(BigDecimal.valueOf(20))
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
                .description("Hero's fire spells do extra 50% damage")
                .placement(ArtifactPlacement.MISC)
                .price(BigDecimal.valueOf(20))
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
                .description("Hero's water spells do extra 50% damage")
                .placement(ArtifactPlacement.MISC)
                .price(BigDecimal.valueOf(20))
                .rank(ArtifactRank.MAJOR)
                .target(ArtifactTarget.SPELLS)
                .effects(Set.of(orbOfDrivingRainEffect))
                .build();
    }
}
