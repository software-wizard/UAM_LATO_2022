package pl.psi.creatures;

import lombok.Getter;
import lombok.Setter;
import pl.psi.artifacts.ArtifactApplier;
import pl.psi.artifacts.ArtifactEffectApplicable;
import pl.psi.artifacts.model.ArtifactEffect;
import pl.psi.shop.BuyProductInterface;
import pl.psi.shop.Money;

@Getter
@Setter
public class EconomyCreature implements BuyProductInterface, ArtifactEffectApplicable {

    private final CreatureStatisticIf baseStats;
    private final Money goldCost;
    private final ArtifactApplier artifactApplier;
    private CreatureStatisticIf upgradedStats;
    private int amount;

    public EconomyCreature(final CreatureStatisticIf aStats, final int aAmount, final Money aGoldCost) {
        baseStats = aStats;
        amount = aAmount;
        goldCost = aGoldCost;

        upgradedStats = CreatureStats.builder().build().addStats(aStats);
        artifactApplier = new ArtifactApplier();
    }

    public int getAmount() {
        return amount;
    }

    public void increaseAmount(int aAmount) {
        amount = amount + aAmount;
    }

    public Money getGoldCost() {
        return goldCost;
    }

    public CreatureStatisticIf getStats() {
        return baseStats;
    }

    public String getName() {
        return baseStats.getName();
    }

    public boolean isUpgraded() {
        return baseStats.isUpgraded();
    }

    public int getTier() {
        return baseStats.getTier();
    }

    @Override
    public void applyArtifactEffect(final ArtifactEffect<? extends ArtifactEffectApplicable> aArtifactEffect) {
        upgradedStats = artifactApplier.calculateCreatureUpgradedStatisticAfterApplyingArtifact(aArtifactEffect, baseStats, upgradedStats);
    }


}
