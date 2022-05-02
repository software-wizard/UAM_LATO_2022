package pl.psi.skills;

import lombok.Getter;

@Getter
// TODO find out if this class should be abstract and other classes should inherit from it or should every statistic be
// TODO in valueObject without inheritance like creatures
public class EconomySkill {

    // TODO put this field in value object with other statistics like creature statistics
    private final int cost;

    public EconomySkill(int cost)
    {
        this.cost = cost;
    }
}
