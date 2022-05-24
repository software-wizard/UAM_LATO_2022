package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class WarMachinesAbstract extends Creature {

    protected CreatureStatisticIf stats;
    protected int amount;
    protected double currentHp;
    protected DamageCalculatorIf calculator;
    protected Creature decorated;
    protected int skillLevel;


    @Override
    protected boolean canCounterAttack(final Creature aDefender) {
        return false;
    }

    public abstract void performAction(List<Creature> creatureList);

    @Override
    double getAttack() {
        return stats.getAttack();
    }

    @Override
    double getArmor() {
        return stats.getArmor();
    }

    @Override
    public String getName() {
        return stats.getName();
    }

    @Override
    public double getMoveRange() {
        return stats.getMoveRange();
    }

    @Override
    public Range<Integer> getDamage() {
        return stats.getDamage();
    }


}
