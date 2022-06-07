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
    public boolean getCanCounterAttack(){
        return decorated.getCanCounterAttack();
    }

    @Override
    public void setCanCounterAttack(final boolean value){
        decorated.setCanCounterAttack(value);
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
    protected void applyCounterAttackDamage(final Creature aDefender, final double aDamage){
        decorated.applyCounterAttackDamage(aDefender,aDamage);
    }

    @Override
    public void attack(final Creature aDefender) {
        decorated.attack(aDefender);
    }

    @Override
    protected void setLastAttackDamage(double damage){
        decorated.setLastAttackDamage(damage);
    }

    @Override
    public double getLastAttackDamage() {
        return decorated.getLastAttackDamage();
    }

    @Override
    protected void setLastCounterAttackDamage(final double damage) {
        decorated.setLastCounterAttackDamage(damage);
    }

    @Override
    public double getLastCounterAttackDamage() {
        return decorated.getLastCounterAttackDamage();
    }

    @Override
    public void clearLastCounterAttackDamage(){
        decorated.clearLastCounterAttackDamage();
    }

    @Override
    public boolean isAlive() {
        return decorated.isAlive();
    }

    @Override
    public void age() {
        decorated.age();
    }

    @Override
    protected void setLastHealAmount(final double healAmount){
        decorated.setLastHealAmount(healAmount);
    }

    @Override
    public double getLastHealAmount() {
        return decorated.getLastHealAmount();
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
    public Range<Integer> getDamage() {
        return decorated.getDamage();
    }

    @Override
    public double getMaxHp() {
        return decorated.getMaxHp();
    }

    @Override
    public double getAttack() {
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
    public void addShots(int i) {
        decorated.addShots(i);
    }

    @Override
    public int getShots(){
        return decorated.getShots();
    }

    @Override
    protected void restoreCurrentHpToMax() {
        decorated.restoreCurrentHpToMax();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        decorated.propertyChange(evt);
    }

    @Override
    public Integer[][] getSplashDamageRange(){
        return decorated.getSplashDamageRange();
    }
}
