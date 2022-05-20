package pl.psi.artifacts;

import pl.psi.artifacts.model.ArtifactApplyingMode;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ArtifactUtils
{
    /**
     * Method to calculate new statistic value after applying artifact on.
     *
     * @param aApplyingMode - indicates how we apply artifact's effect value to statistic.
     * @param aCurrentValue - statistic's value before applying changes.
     * @param aEffectValue - value of artifact's effect
     * @param aBaseValue - base statistic value (value without any modifications)
     *
     * @return new value of statistic after artifact effect was applied to it
     */
    public static int calculateStatisticValueAfterApplyingArtifact( final ArtifactApplyingMode aApplyingMode,
                                                                    final int aCurrentValue, final BigDecimal aEffectValue,
                                                                    final int aBaseValue )
    {
        BigDecimal result;
        switch ( aApplyingMode )
        {
            case ADD:
                result = aEffectValue.add( BigDecimal.valueOf( aCurrentValue ) );
                break;
            case MULTIPLY:
                result = aEffectValue.multiply( BigDecimal.valueOf( aBaseValue ) )
                        .add( BigDecimal.valueOf( aCurrentValue ) );
                break;
            default:
                throw new UnsupportedOperationException( "Unrecognised artifact applying mode" );
        }

        return result.setScale( 0, RoundingMode.FLOOR ).intValueExact();
    }
}
