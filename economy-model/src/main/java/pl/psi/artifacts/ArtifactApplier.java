package pl.psi.artifacts;

import pl.psi.artifacts.model.ArtifactEffect;
import pl.psi.artifacts.model.ArtifactTarget;
import pl.psi.creatures.CreatureStatisticIf;
import pl.psi.creatures.CreatureStats;
import pl.psi.hero.HeroStatisticsIf;
import pl.psi.hero.HeroStats;
import pl.psi.spells.SpellFactorsModifiers;

/**
 * Service responsible for taking care of calculating upgraded values after applying artifact.
 */
public class ArtifactApplier {
    public CreatureStatisticIf calculateCreatureUpgradedStatisticAfterApplyingArtifact(
            final ArtifactEffect<? extends ArtifactEffectApplicable> aArtifactEffect, final CreatureStatisticIf aBaseStats,
            final CreatureStatisticIf aUpgradedStats) {

        if (!(aArtifactEffect.getApplierTarget().getArtifactTarget() == ArtifactTarget.CREATURES)) {
            throw new IllegalArgumentException("Artifact Effect is not meant to be applied to Creatures.");
        }

        final CreatureArtifactApplicableProperty applierTarget = (CreatureArtifactApplicableProperty) aArtifactEffect.getApplierTarget();

        final CreatureStats.CreatureStatsBuilder creatureStatsBuilder = CreatureStats.builder()
                .maxHp(aUpgradedStats.getMaxHp())
                .attack(aUpgradedStats.getAttack())
                .armor(aUpgradedStats.getArmor())
                .name(aUpgradedStats.getName())
                .moveRange(aUpgradedStats.getMoveRange())
                .damage(aUpgradedStats.getDamage())
                .tier(aUpgradedStats.getTier())
                .description(aUpgradedStats.getDescription())
                .isUpgraded(aUpgradedStats.isUpgraded());

        switch (applierTarget) {
            case HEALTH:
                final int upgradedMaxHp = aArtifactEffect.calculateStatisticValueAfterApplying(
                        new ArtifactEffectApplyingProperties(aBaseStats.getMaxHp(), aUpgradedStats.getMaxHp()));
                return creatureStatsBuilder.maxHp(upgradedMaxHp).build();
            case ATTACK:
                final int upgradedAttack = aArtifactEffect.calculateStatisticValueAfterApplying(
                        new ArtifactEffectApplyingProperties(aBaseStats.getAttack(), aUpgradedStats.getAttack()));
                return creatureStatsBuilder.attack(upgradedAttack).build();
            case DEFENCE:
                final int upgradedArmor = aArtifactEffect.calculateStatisticValueAfterApplying(
                        new ArtifactEffectApplyingProperties(aBaseStats.getArmor(), aUpgradedStats.getArmor()));
                return creatureStatsBuilder.armor(upgradedArmor).build();
            default:
                throw new UnsupportedOperationException("Unrecognised applying target type.");
        }
    }

    public HeroStatisticsIf calculateHeroUpgradedStatisticsAfterApplyingArtifact(
            final ArtifactEffect<? extends ArtifactEffectApplicable> aArtifactEffect, final HeroStatisticsIf aBaseStats) {

        if ( !( aArtifactEffect.getApplierTarget().getArtifactTarget() == ArtifactTarget.SKILL) ) {
            throw new IllegalArgumentException("Artifact Effect is not meant to be applied to Hero's skill.");
        }

        final SkillArtifactApplicableProperty applierTarget = (SkillArtifactApplicableProperty) aArtifactEffect.getApplierTarget();

        final HeroStats.HeroStatsBuilder heroStatsBuilder = HeroStats.builder()
                .name(aBaseStats.getName())
                .attack(aBaseStats.getAttack())
                .defence(aBaseStats.getDefence())
                .spellPower(aBaseStats.getSpellPower())
                .knowledge(aBaseStats.getKnowledge())
                .morale(aBaseStats.getMorale())
                .luck(aBaseStats.getLuck())
                .spellPoints(aBaseStats.getSpellPoints());

        switch (applierTarget) {
            case ATTACK:
                final int upgradedAttack = aArtifactEffect.calculateStatisticValueAfterApplying(
                        new ArtifactEffectApplyingProperties(aBaseStats.getAttack(), aBaseStats.getAttack()));
                return heroStatsBuilder.attack(upgradedAttack).build();
            case DEFENCE:
                final int upgradedDefence = aArtifactEffect.calculateStatisticValueAfterApplying(
                        new ArtifactEffectApplyingProperties(aBaseStats.getDefence(), aBaseStats.getDefence()));
                return heroStatsBuilder.defence(upgradedDefence).build();
            case KNOWLEDGE:
                final int upgradedKnowledge = aArtifactEffect.calculateStatisticValueAfterApplying(
                        new ArtifactEffectApplyingProperties(aBaseStats.getKnowledge(), aBaseStats.getKnowledge()));
                return heroStatsBuilder.knowledge(upgradedKnowledge).build();
            case SPELL_POWER:
                final int upgradedSpellPower = aArtifactEffect.calculateStatisticValueAfterApplying(
                        new ArtifactEffectApplyingProperties(aBaseStats.getSpellPower(), aBaseStats.getSpellPower()));
                return heroStatsBuilder.spellPower(upgradedSpellPower).build();
            default:
                throw new UnsupportedOperationException("Unrecognised applying target type.");
        }

    }

    public SpellFactorsModifiers calculateSpellFactorsModifiers(
            final ArtifactEffect<? extends ArtifactEffectApplicable> aArtifactEffect,
            final SpellFactorsModifiers aBaseStats) {

        if (!(aArtifactEffect.getApplierTarget().getArtifactTarget() == ArtifactTarget.SPELLS)) {
            throw new IllegalArgumentException("Artifact Effect is not meant to be applied to spells.");
        }

        final SpellArtifactApplicableProperty applierTarget = (SpellArtifactApplicableProperty) aArtifactEffect.getApplierTarget();

        final SpellFactorsModifiers.SpellFactorsModifiersBuilder spellFactorsModifiersBuilder = SpellFactorsModifiers.builder();

        switch (applierTarget) {
            case AIR_DAMAGE:
                final double upgradedAirDamage = aArtifactEffect.calculateStatisticValueAfterApplying(
                        new ArtifactEffectApplyingProperties(aBaseStats.getAirDamageModifier(), aBaseStats.getAirDamageModifier()));
                return spellFactorsModifiersBuilder.airDamageModifier(upgradedAirDamage).build();
            case FIRE_DAMAGE:
                final double upgradedFireDamage = aArtifactEffect.calculateStatisticValueAfterApplying(
                        new ArtifactEffectApplyingProperties(aBaseStats.getFireDamageModifier(), aBaseStats.getFireDamageModifier()));
                return spellFactorsModifiersBuilder.fireDamageModifier(upgradedFireDamage).build();
            case EARTH_DAMAGE:
                final double upgradedEarthDamage = aArtifactEffect.calculateStatisticValueAfterApplying(
                        new ArtifactEffectApplyingProperties(aBaseStats.getEarthDamageModifier(), aBaseStats.getEarthDamageModifier()));
                return spellFactorsModifiersBuilder.earthDamageModifier(upgradedEarthDamage).build();
            case WATER_DAMAGE:
                final double upgradedWaterDamage = aArtifactEffect.calculateStatisticValueAfterApplying(
                        new ArtifactEffectApplyingProperties(aBaseStats.getWaterDamageModifier(), aBaseStats.getWaterDamageModifier()));
                return spellFactorsModifiersBuilder.waterDamageModifier(upgradedWaterDamage).build();
            case DURATION:
                final int upgradedDuration = aArtifactEffect.calculateStatisticValueAfterApplying(
                        new ArtifactEffectApplyingProperties(aBaseStats.getAddedDuration(), aBaseStats.getAddedDuration()));
                return spellFactorsModifiersBuilder.addedDuration(upgradedDuration).build();
            default:
                throw new UnsupportedOperationException("Unrecognised applying target type.");
        }
    }
}
