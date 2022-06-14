package pl.psi.spells;

import lombok.Getter;

@Getter
public class SpellCostValueObject {

    private final int goldCost;

    public SpellCostValueObject(int aGoldCost) {
        this.goldCost = aGoldCost;
    }
}
