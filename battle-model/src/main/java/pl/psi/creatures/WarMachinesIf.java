package pl.psi.creatures;

public interface WarMachinesIf<T> {
    T create(int aTier, int aAmount, DamageCalculatorIf aCalculator, int aSkillLevel) ;
}