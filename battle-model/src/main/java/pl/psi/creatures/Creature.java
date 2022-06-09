package pl.psi.creatures;
//  ******************************************************************
//
//  Copyright 2022 PSI Software AG. All rights reserved.
//  PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
//  ******************************************************************

import com.google.common.collect.Range;
import lombok.Getter;
import lombok.Setter;
import pl.psi.TurnQueue;
import pl.psi.spells.Spell;
import pl.psi.spells.SpellRang;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Random;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
@Setter
public class Creature implements PropertyChangeListener, Comparable<Creature> {
    private CreatureStatisticIf basicStats;
    private CreatureStats externalStats = new CreatureStats.CreatureStatsBuilder().build();
    private CreatureStats buffedStats = new CreatureStats.CreatureStatsBuilder().build();
    private int amount;
    private double currentHp;
    private boolean canCounterAttack = true;
    private DamageCalculatorIf calculator;
    private int heroNumber; /** REMEMBER TO DELETE ANY USAGE OF THIS */
    private double DEFENCE_MULTIPLIER = 0.2;
    private double spellDamageReduction = 1;
    private int morale = 1; // range = < -3;3 >
    private int luck;
    private Alignment alignment;
    private double defenceBonusArmor = 0;
    private boolean isDefending = false;
    private double lastHealAmount;
    private double lastAttackDamage;
    private double lastCounterAttackDamage;

    Creature() {
    }

    private Creature(final CreatureStatisticIf aStats, final DamageCalculatorIf aCalculator,
                     final int aAmount, final Alignment aAlignment,
                     final int aLuck) {
        basicStats = aStats;
        amount = aAmount;
        currentHp = basicStats.getMaxHp();
        calculator = aCalculator;
        alignment = aAlignment;
        luck = aLuck;
    }

    public void attack(final Creature aDefender) {
        if (isAlive()) {
            final double damage = getCalculator().calculateDamage(this, aDefender);
            applyDamage(aDefender, damage);
            if (canCounterAttack(aDefender)) {
                aDefender.counterAttack(this);
            }
        }
    }

    protected void applyDamage(final Creature aDefender, final double aDamage) {
        aDefender.setCurrentHp( ((aDefender.getAmount()-1) * aDefender.getMaxHp()) + aDefender.getCurrentHp() - aDamage );
        if(aDefender.getCurrentHp() < 0){
            setLastAttackDamage(aDamage + aDefender.getCurrentHp());
        }
        else{
            setLastAttackDamage(aDamage);
        }
        aDefender.setAmount( addUnits( aDefender ) );
        aDefender.setCurrentHp( calculateCurrentHp( aDefender ) );
    }

    protected void counterAttack(final Creature aAttacker) {
        final int damage = getCalculator()
                .calculateDamage(this, aAttacker);
        applyCounterAttackDamage(aAttacker, damage);
        setLastCounterAttackDamage(damage);
        setCanCounterAttack(false);
    }

    protected void applyCounterAttackDamage(final Creature aDefender, final double aDamage) {
        aDefender.setCurrentHp( ((aDefender.getAmount()-1) * aDefender.getMaxHp()) + aDefender.getCurrentHp() - aDamage );
        if(aDefender.getCurrentHp() < 0){
            setLastCounterAttackDamage(aDamage + aDefender.getCurrentHp());
        }
        else{
            setLastCounterAttackDamage(aDamage);
        }
        aDefender.setAmount( addUnits( aDefender ) );
        aDefender.setCurrentHp( calculateCurrentHp( aDefender ) );
    }



    public void castSpell(final Creature aDefender, Spell spell) {
        if (isAlive())
            spell.castSpell(aDefender);
    }

    public void castSpell(final List<Creature> aDefender, Spell spell) {
        if (isAlive()) {
            spell.castSpell(aDefender);
        }
    }

    public void increaseStats(CreatureStatisticIf statIncrease) {
        externalStats.addStats(statIncrease);
    }


    private int addUnits(final Creature aDefender ){
        if( aDefender.getCurrentHp() > 0 ){
            if( aDefender.getCurrentHp() > aDefender.getMaxHp() ){
                if( aDefender.getCurrentHp() % aDefender.getMaxHp() == 0){
                    return (int) (aDefender.getCurrentHp() / aDefender.getMaxHp());
                }
                else{
                    return  (int) (aDefender.getCurrentHp() / aDefender.getMaxHp()) + 1 ;
                }
            }
            else{
                return 1;
            }
        }
        else{
            return 0;
        }
    }

    private double calculateCurrentHp( final Creature aDefender ){
        if( aDefender.getAmount() == 0 ){
            return 0;
        }
        else{
            if( aDefender.getCurrentHp() % aDefender.getMaxHp() == 0){
                return aDefender.getMaxHp();
            }
            else{  // ( a % b + b ) % b == a % b   just like with healing, modulo with negative numbers is crazy
                // a = (aDefender.getCurrentHp() - ( aDefender.getAmount() * aDefender.getMaxHp() ))
                // b = aDefender.getMaxHp()
                return (((aDefender.getCurrentHp() - ( aDefender.getAmount() * aDefender.getMaxHp())) % aDefender.getMaxHp()) + aDefender.getMaxHp()) % aDefender.getMaxHp();
            }
        }
    }

    public void applySpellDamage(Creature aDefender, Integer damage) {
        aDefender.setCurrentHp(aDefender.getCurrentHp() - damage); // ToDo: include magic resist
    }

    public void increaseLuckBy(int factor) {
        setLuck(luck + factor);
    }

    public void age() {
        CreatureStats reduceMaxHp = new CreatureStats
                .CreatureStatsBuilder()
                .maxHp(-(getStats().getMaxHp() / 2))
                .build();
        buff(reduceMaxHp);
        final double currentHpAfterAge = Math.max(getCurrentHp() - (getBasicStats().getMaxHp() - getStats().getMaxHp()), 1);
        setCurrentHp(currentHpAfterAge);
    }

    private void addUnits(final int aAmountToAdd) {
        if (aAmountToAdd > 1) {
            setAmount(amount + aAmountToAdd - 1);
        } else {
            setAmount(amount + aAmountToAdd);
        }
    }

    public void applySpellDamage(final double damage) {
        applyDamage(this, damage * spellDamageReduction);
    }

    protected void heal(double healAmount) {
        setCurrentHp((getCurrentHp() + healAmount));
        addUnits(calculateAmount());
        setCurrentHp(calculateCurrentHp());
        setLastHealAmount(healAmount);
    }

    private int calculateAmount() {
        if (getCurrentHp() < getStats().getMaxHp()){
            return 0;
        }
        else if (getCurrentHp() / getStats().getMaxHp() == 1) {
            return 1;
        } else if (getCurrentHp() % getStats().getMaxHp() == 0) {
            return (int) (getCurrentHp() / getStats().getMaxHp());
        } else {
            return (int) ((getCurrentHp() / getStats().getMaxHp()) + 1);
        }
    }

    private double calculateCurrentHp() {
        if (getCurrentHp() - (getAmount() * getStats().getMaxHp()) == 0) {
            return (int) getStats().getMaxHp();
        } else { // ( a % b + b ) % b == a % b   when a is negative % operator behaves funky
            return (int) (((getCurrentHp() - (getAmount() * getStats().getMaxHp())) % (getStats().getMaxHp()) + (getStats().getMaxHp())) % getStats().getMaxHp());
        }
    }

    protected void setLuck(int aLuck) {
        luck = aLuck;
    }

    public void setMorale(final int aMorale) {
        if (aMorale > 3) {
            throw new IllegalArgumentException("Morale must not be greater than 3");
        }

        if (aMorale < -3) {
            throw new IllegalArgumentException("Morale must not be less than 3");
        }

        morale = aMorale;
    }

    public void setInMelee(final boolean value){}

    public void buff(CreatureStatisticIf statsToAdd) {
        buffedStats.addStats(statsToAdd);
    }

    public CreatureStatisticIf getStats() {
        CreatureStats stats = new CreatureStats.CreatureStatsBuilder().build();
        stats.addStats(basicStats);
        stats.addStats(externalStats);
        stats.addStats(buffedStats);
        return stats;
    }

    public Range<Integer> getDamage() {
        return getStats().getDamage();
    }

    public double getMaxHp() {
        return getStats().getMaxHp();
    }

    public double getAttack() {
        return getStats().getAttack();
    }

    public double getArmor() {
        return getStats().getArmor();
    }

    public Integer[][] getSplashDamageRange(){
        Integer[][] splashDamageArea = new Integer[3][3];
        for(int i = 0; i<3;i++){
            for(int j = 0; j<3;j++){
                splashDamageArea[i][j] = 0;
            }
        }
        splashDamageArea[1][1] = 1;
        return splashDamageArea;
    }

    public String getName() {
        return basicStats.getName();
    }

    public double getMoveRange() {
        return getStats().getMoveRange();
    }

    public String getShotsAmount(){
        return "";
    }

    public int getShots(){
        return 1;
    }

    public boolean getCanCounterAttack(){
        return canCounterAttack;
    }

    protected void setCanCounterAttack(boolean value) {
        canCounterAttack = value;
    }

    protected void setLastAttackDamage(double damage){
        lastAttackDamage = damage;
    }

    public double getLastAttackDamage(){
        return lastAttackDamage;
    }

    protected void setLastCounterAttackDamage( final double damage ) {
        lastCounterAttackDamage = damage;
    }

    public void clearLastCounterAttackDamage(){
        setLastCounterAttackDamage(0);
    }

    public double getLastCounterAttackDamage(){
        return lastCounterAttackDamage;
    }

    private String getSpecial(){
        String description = getBasicStats().getDescription();
        String[] special = description.split(";");
        return special[1];
    }

    public String getCreatureInformation(){
        return "Name: " + getBasicStats().getName() + "\nAttack: " + (int)getBasicStats().getAttack() + "(" + (int)getStats().getAttack() + ")" + "\nArmor: " + (int)getBasicStats().getArmor() + "(" + (int)getStats().getArmor() + ")" + "\nShots: " + getShotsAmount() + "\nDamage: " + getStats().getDamage().lowerEndpoint() + "-" + getStats().getDamage().upperEndpoint() + "\nMax health: " + (int)getStats().getMaxHp() + "\nCurrent health: " + (int)getCurrentHp() + "\nSpeed: " + (int)getStats().getMoveRange() + "(" + (int)getStats().getMoveRange() + ")\n" + getSpecial();
    }

    protected void setLastHealAmount(final double healAmount){
        lastHealAmount = healAmount;
    }

    public double getLastHealAmount(){
        return lastHealAmount;
    }

    public double getAttackRange() {
        return 1.5;
    }

    public void defend(final boolean value) {
        if(value){
            if(!isDefending()){
                defenceBonusArmor = getArmor() * 0.2;
                isDefending = true;

                buff(new CreatureStats.CreatureStatsBuilder().armor(defenceBonusArmor).build());
            }
            else{
                throw new RuntimeException("Creature already defending.");
            }
        }
        else{
            if(isDefending()){
                isDefending = false;
                buff(new CreatureStats.CreatureStatsBuilder().armor(-defenceBonusArmor).build());
            }
            else{
                throw new RuntimeException("Creature is not defending.");
            }
        }
    }

    public boolean isAlive() {
        return getAmount() > 0;
    }

    protected boolean canCounterAttack(final Creature aDefender) {
        return aDefender.getCanCounterAttack() && aDefender.getCurrentHp() > 0;
    }

    public boolean hasSpecial(){
        return getSpecial().length() > 1;
    }

    public boolean isRange(){
        return false;
    }

    protected void setCurrentHp(final double aCurrentHp) {
        currentHp = aCurrentHp;
    }

    public void applyStatsWithSpells(CreatureStats aCreatureStats) {
        setStatsWithSpells(CreatureStats.builder()
                .attack((buffedStats == null) ? aCreatureStats.getAttack() : buffedStats.getAttack() + aCreatureStats.getAttack() )
                .armor((buffedStats == null) ? aCreatureStats.getArmor() : buffedStats.getArmor() + aCreatureStats.getArmor() )
                .moveRange((buffedStats == null) ? aCreatureStats.getMoveRange() : buffedStats.getMoveRange() + aCreatureStats.getMoveRange() )
                .build());
    }

    private void setStatsWithSpells(CreatureStats aStatsWithSpells) {
        buffedStats = aStatsWithSpells;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (TurnQueue.END_OF_TURN.equals(evt.getPropertyName())) {
            setCanCounterAttack(true);
            if(isDefending()){
                defend(false);
            }
        }
    }

    protected void restoreCurrentHpToMax() {
        currentHp = getStats().getMaxHp();
    }

    @Override
    public int compareTo(Creature c) {
        return Double.compare(c.getMoveRange(), getMoveRange());
    }

    public void addShots(int i) {
    }

    public boolean canCastSpell() {
        return false;
    }

    public String getSpellName(){
        return "";
    }

    public SpellRang getSpellRang(){
        return null;
    }

    public int getSpellPower(){
        return 0;
    }


    public static class Builder {
        private int amount = 1;
        private DamageCalculatorIf calculator = new DefaultDamageCalculator(new Random());
        private int luck = 10;
        private Alignment alignment = Alignment.NEUTRAL;
        private CreatureStatisticIf statistic;

        public Builder statistic(final CreatureStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }

        public Builder amount(final int aAmount) {
            amount = aAmount;
            return this;
        }

        public Builder alignment(final Alignment aAlignment) {
            alignment = aAlignment;
            return this;
        }

        public Builder luck(final int aLuck) {
            luck = aLuck;
            return this;
        }

        Builder calculator(final DamageCalculatorIf aCalc) {
            calculator = aCalc;
            return this;
        }


        public Creature build() {
            return new Creature(statistic, calculator, amount, alignment, luck);
        }
    }
}
