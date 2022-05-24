package pl.psi.creatures;

public interface SpecialFieldsToAttackIf<T> {
    T create(int aTier, int aAmount, DamageCalculatorIf aCalculator) ;
}