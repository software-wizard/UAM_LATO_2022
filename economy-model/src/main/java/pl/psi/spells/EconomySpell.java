package pl.psi.spells;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EconomySpell {

    private final SpellStats stats;
    private final int goldCost;

}
