package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.AccessLevel;
import lombok.Getter;

import java.beans.PropertyChangeEvent;


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
    public void setAmount( final int aAmount) {
        decorated.setAmount(aAmount);
    }

    @Override
    public DamageCalculatorIf getCalculator() {
        return decorated.getCalculator();
    }

    @Override
    protected void applyDamage(final Creature aDefender, final double aDamage) {
        decorated.applyDamage(aDefender, aDamage);
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
    public void setCanMove(final boolean value){
        decorated.setCanMove( value );
    }

    @Override
    public boolean canMove() {
        return decorated.canMove();
    }

    @Override
    public void setCanAttack(final boolean value){
        decorated.setCanAttack( value );
    }

    @Override
    public boolean canAttack() {
        return decorated.canAttack();
    }

    @Override
    public void age() {
        decorated.age();
    }

    @Override
    public void applySpellDamage(final double damage) {
        decorated.applyDamage(decorated, damage);
    }

    @Override
    public void buff(CreatureStatisticIf statsToAdd) {
        decorated.buff(statsToAdd);
    }

    @Override
    public void increaseStats(CreatureStatisticIf statIncrease) {
        decorated.increaseStats(statIncrease);
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
    double getMaxHp() {
        return decorated.getMaxHp();
    }

    @Override
    double getAttack() {
        return decorated.getAttack();
    }

    @Override
    public double getArmor() {
        return decorated.getArmor();
    }

    @Override
    public String getName() {
        return decorated.getName();
    }

    @Override
    public double getMoveRange() {
        return decorated.getMoveRange();
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
