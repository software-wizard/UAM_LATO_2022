package pl.psi.skills;

import lombok.Getter;

@Getter
public class SkillCostValueObject {
    private final int goldCost;

    public SkillCostValueObject(int aGoldCost) {
        this.goldCost = aGoldCost;
    }
}
