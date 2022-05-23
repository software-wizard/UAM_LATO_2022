package pl.psi.creatures;

public class WarMachinesFactory implements WarMachinesIf<WarMachinesAbstract> {

    @Override
    public WarMachinesAbstract create(final int aTier, final int aAmount, final DamageCalculatorIf aCalculator) {
        switch(aTier) {
            case 1:
                return new Ballista.Builder().statistic(WarMachinesStatistic.BALLISTA)
                        .amount(aAmount)
                        .calculator(aCalculator)
                        .build();
            case 2:
                return new FirstAidTent.Builder().statistic(WarMachinesStatistic.FIRST_AID_TENT)
                        .amount(aAmount)
                        .calculator(aCalculator)
                        .build();
            default:
                throw new IllegalArgumentException("Illegal Argument Exception");
        }

    }
}
