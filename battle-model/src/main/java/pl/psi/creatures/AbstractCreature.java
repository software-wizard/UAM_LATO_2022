package pl.psi.creatures;

import com.google.common.collect.Range;

import java.beans.PropertyChangeEvent;

import lombok.AccessLevel;
import lombok.Getter;


public class AbstractCreature extends Creature {
    @Getter(AccessLevel.PROTECTED)
    private final Creature decorated;

    public AbstractCreature(final Creature aDecorated) {
        decorated = aDecorated;
    }

    @Override
    public CreatureStatisticIf getBasicStats() {
        return decorated.getBasicStats();
    }

    @Override
    public CreatureStatisticIf getStats() {
        return decorated.getStats();
    }

    @Override
    public int getAmount() {
        return decorated.getAmount();
    }

    @Override
    public DamageCalculatorIf getCalculator() {
        return decorated.getCalculator();
    }

    @Override
    public void attack(final Creature aDefender) {
        decorated.attack(aDefender);
    }

    @Override
    public boolean isAlive() {
        return decorated.isAlive();
    }


    @Override
    public void heal(final double healAmount) {
        decorated.heal(healAmount);
    }

    @Override
    public double getCurrentHp() {
        return decorated.getCurrentHp();
    }

    @Override
    protected void setCurrentHp(final double aCurrentHp) {
        decorated.setCurrentHp(aCurrentHp);
    }

    @Override
    Range<Integer> getDamage() {
        return decorated.getDamage();
    }

    @Override
    double getAttack() {
        return decorated.getAttack();
    }

    @Override
    double getArmor() {
        return decorated.getArmor();
    }

    @Override
    protected void restoreCurrentHpToMax() {
        decorated.restoreCurrentHpToMax();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        decorated.propertyChange(evt);
    }

}
