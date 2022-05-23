package pl.psi.creatures;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import pl.psi.artifacts.ArtifactEffectApplicable;
import pl.psi.artifacts.ArtifactEffectApplyingProperties;
import pl.psi.artifacts.CreatureArtifactApplicableProperty;
import pl.psi.artifacts.model.ArtifactApplyingMode;
import pl.psi.artifacts.model.ArtifactEffect;

@Getter
@Setter
public class EconomyCreature implements ArtifactEffectApplicable {

    private CreatureStatisticIf upgradedStats;
    private final CreatureStatistic baseStats;
    private final int amount;
    private final int goldCost;

    EconomyCreature(final CreatureStatistic aStats, final int aAmount, final int aGoldCost) {
        upgradedStats = new CreatureStats(aStats);
        baseStats = aStats;
        amount = aAmount;
        goldCost = aGoldCost;
    }

    public int getAmount() {
        return amount;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public String getName() {
        return baseStats.getTranslatedName();
    }

    public boolean isUpgraded() {
        return baseStats.isUpgraded();
    }

    public int getTier() {
        return baseStats.getTier();
    }

    @Override
    public void applyArtifactEffect(
        final ArtifactEffect<? extends ArtifactEffectApplicable> artifactEffect) {
        final CreatureArtifactApplicableProperty applierTarget = artifactEffect.getApplierTarget();

        int upgradedMaxHp = upgradedStats.getMaxHp();
        int upgradedAttack = upgradedStats.getAttack();
        int upgradedArmor = upgradedStats.getArmor();

        switch (applierTarget) {
            case HEALTH:
                upgradedMaxHp = artifactEffect.calculateStatisticValueAfterApplying(
                    new ArtifactEffectApplyingProperties(upgradedMaxHp, baseStats.getMaxHp()));
                break;
            case ATTACK:
                upgradedAttack = artifactEffect.calculateStatisticValueAfterApplying(
                    new ArtifactEffectApplyingProperties(upgradedAttack, baseStats.getAttack()));
                break;
            case DEFENCE:
                upgradedArmor = artifactEffect.calculateStatisticValueAfterApplying(
                    new ArtifactEffectApplyingProperties(upgradedArmor, baseStats.getArmor()));
                break;
            default:
                throw new UnsupportedOperationException("Unrecognised applying target type.");
        }

        upgradedStats = CreatureStats.builder()
            .maxHp(upgradedMaxHp)
            .attack(upgradedAttack)
            .armor(upgradedArmor)
            .name(upgradedStats.getName())
            .moveRange(upgradedStats.getMoveRange())
            .damage(upgradedStats.getDamage())
            .tier(upgradedStats.getTier())
            .description(upgradedStats.getDescription())
            .isUpgraded(upgradedStats.isUpgraded())
            .build();
    }
}
