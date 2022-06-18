package pl.psi.artifacts;

import pl.psi.artifacts.model.ArtifactEffect;
import pl.psi.artifacts.model.ArtifactTarget;
import pl.psi.creatures.CreatureStatisticIf;
import pl.psi.creatures.CreatureStats;
import pl.psi.hero.HeroStatisticsIf;
import pl.psi.hero.HeroStats;

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

    // TODO: Two new methods for calculating upgraded skills & spells.
}
