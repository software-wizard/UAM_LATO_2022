package pl.psi.creatures;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.Getter;
import lombok.Setter;
import pl.psi.artifacts.*;

@Getter
@Setter
public class EconomyCreature implements ArtifactEffectApplicable
{
    private CreatureStatisticIf upgradedStats;
    private final CreatureStatistic baseStats;
    private final int amount;
    private final int goldCost;

    EconomyCreature( final CreatureStatistic aStats, final int aAmount, final int aGoldCost )
    {
        upgradedStats = new CreatureStats( aStats );
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
    public void applyArtifactEffect( final ArtifactEffect< ? extends ArtifactEffectApplicable > artifactEffect )
    {
        final BigDecimal effectValue = artifactEffect.getEffectValue();
        final ArtifactApplyingMode applyingMode = artifactEffect.getEffectApplyingMode();
        final ArtifactApplierTarget applierTarget = artifactEffect.getApplierTarget();

        int upgradedMaxHp = upgradedStats.getMaxHp();
        int upgradedAttack = upgradedStats.getAttack();
        int upgradedArmor = upgradedStats.getArmor();

        switch ( applierTarget )
        {
            case HEALTH:
                upgradedMaxHp = ArtifactUtils.calculateStatisticValueAfterApplyingArtifact( applyingMode, upgradedMaxHp,
                        effectValue, baseStats.getMaxHp() );
                break;
            case ATTACK:
                upgradedAttack = ArtifactUtils.calculateStatisticValueAfterApplyingArtifact( applyingMode, upgradedAttack,
                        effectValue, baseStats.getAttack() );
                break;
            case DEFENCE:
                upgradedArmor = ArtifactUtils.calculateStatisticValueAfterApplyingArtifact( applyingMode, upgradedArmor,
                        effectValue, baseStats.getArmor() );
                break;
            default:
                throw new UnsupportedOperationException( "Unrecognised applying target type." );
        }

        upgradedStats = new CreatureStats( upgradedStats, upgradedAttack, upgradedArmor, upgradedMaxHp );
    }
}
