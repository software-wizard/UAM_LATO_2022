package pl.psi.creatures;

import lombok.Getter;
import lombok.Setter;
import pl.psi.artifacts.ArtifactApplierTarget;
import pl.psi.artifacts.ArtifactApplyingMode;
import pl.psi.artifacts.ArtifactEffect;
import pl.psi.artifacts.ArtifactEffectApplicable;

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
    public void applyArtifactEffect(final ArtifactEffect<? extends ArtifactEffectApplicable> artifactEffect) {

        final double effectValue = artifactEffect.getEffectValue();
        final ArtifactApplyingMode applyingMode = artifactEffect.getEffectApplyingMode();
        final ArtifactApplierTarget applierTarget = artifactEffect.getApplierTarget();

        int upgradedMaxHp = upgradedStats.getMaxHp();
        int upgradedAttack = upgradedStats.getAttack();
        int upgradedArmor = upgradedStats.getArmor();

        switch (applierTarget) {
            case HEALTH:
                upgradedMaxHp = calculateNewValue(applyingMode, upgradedMaxHp, effectValue);
                break;
            case ATTACK:
                upgradedAttack = calculateNewValue(applyingMode, upgradedAttack, effectValue);
                break;
            case DEFENCE:
                upgradedArmor = calculateNewValue(applyingMode, upgradedArmor, effectValue);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + applierTarget);
        }

        upgradedStats = new CreatureStats(upgradedStats, upgradedAttack, upgradedArmor,
            upgradedMaxHp);
    }

    private int calculateNewValue(final ArtifactApplyingMode applyingMode, final int currentValue,
        final double effectValue) {

        int result;

        if (ArtifactApplyingMode.ADD.equals(applyingMode)) {
            result = currentValue + (int) effectValue;
        } else {
            result = currentValue + (int) effectValue * baseStats.getArmor();
        }

        return result;
    }

}
