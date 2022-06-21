package pl.psi.spells;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SpellFactorsModifiers {

    private final int addedDuration;
    private final double airDamageModifier;
    private final double fireDamageModifier;
    private final double earthDamageModifier;
    private final double waterDamageModifier;

}
