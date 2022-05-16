package pl.psi.creatures;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public void applyArtifactEffect(
        final ArtifactEffect<? extends ArtifactEffectApplicable> artifactEffect) {

        final BigDecimal effectValue = artifactEffect.getEffectValue();
        final ArtifactApplyingMode applyingMode = artifactEffect.getEffectApplyingMode();
        final ArtifactApplierTarget applierTarget = artifactEffect.getApplierTarget();

        int upgradedMaxHp = upgradedStats.getMaxHp();
        int upgradedAttack = upgradedStats.getAttack();
        int upgradedArmor = upgradedStats.getArmor();

        switch (applierTarget) {
            case HEALTH:
                upgradedMaxHp = calculateNewValue(applyingMode,
                    upgradedMaxHp,
                    effectValue,
                    baseStats.getMaxHp());
                break;
            case ATTACK:
                upgradedAttack = calculateNewValue(applyingMode,
                    upgradedAttack,
                    effectValue,
                    baseStats.getAttack());
                break;
            case DEFENCE:
                upgradedArmor = calculateNewValue(applyingMode,
                    upgradedArmor,
                    effectValue,
                    baseStats.getArmor());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + applierTarget);
        }

        upgradedStats = new CreatureStats(upgradedStats, upgradedAttack, upgradedArmor,
            upgradedMaxHp);
    }

    private int calculateNewValue(final ArtifactApplyingMode applyingMode,
        final int currentValue,
        final BigDecimal effectValue,
        final int baseValue) {
        BigDecimal result;

        if (ArtifactApplyingMode.ADD.equals(applyingMode)) {
           result = effectValue.add(BigDecimal.valueOf(currentValue));

        } else {
            result = effectValue.multiply(BigDecimal.valueOf(baseValue)).add(
                BigDecimal.valueOf(currentValue));
        }

        return result.setScale(0, RoundingMode.FLOOR).intValueExact();
    }

}
