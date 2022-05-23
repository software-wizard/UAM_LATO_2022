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
public class EconomyCreature implements ArtifactEffectApplicable
{

    private CreatureStatisticIf upgradedStats;
    private final CreatureStatistic baseStats;
    private final int amount;
    private final int goldCost;

    private final ArtifactApplier artifactApplier;

    EconomyCreature( final CreatureStatistic aStats, final int aAmount, final int aGoldCost )
    {
        baseStats = aStats;
        amount = aAmount;
        goldCost = aGoldCost;

        upgradedStats = new CreatureStats( aStats );
        artifactApplier = new ArtifactApplier();
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
    public void applyArtifactEffect( final ArtifactEffect< ? extends ArtifactEffectApplicable > aArtifactEffect )
    {
        upgradedStats = artifactApplier.calculateCreatureUpgradedStatisticAfterApplyingArtifact( aArtifactEffect, baseStats, upgradedStats );
    }
}
