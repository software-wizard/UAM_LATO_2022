package pl.psi.creatures;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class AbstractWarMachines extends AbstractCreature {
    protected Creature decorated;
    protected int skillLevel;
    protected WarMachineActionType actionType;
    protected DamageCalculatorIf damageCalculator;

    public AbstractWarMachines(Creature aDecorated, WarMachineActionType actionType, int skillLevel) {
        super(aDecorated);
        this.actionType = actionType;
        this.skillLevel = skillLevel;
    }

    @Override
    public boolean getCanCounterAttack() {
        return false;
    }

    @Override
    public boolean isActive() {
        return skillLevel != 0;
    }

    public void upgradeSkillLevel( int aNewLevel) {
        if (aNewLevel < 4 && aNewLevel > 0) {
            this.skillLevel = aNewLevel;
        }
    }
}