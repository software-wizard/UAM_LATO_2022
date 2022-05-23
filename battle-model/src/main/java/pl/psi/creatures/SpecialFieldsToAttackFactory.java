package pl.psi.creatures;

public class SpecialFieldsToAttackFactory {

    private static final String EXCEPTION_MESSAGE = "Illegal Argument Exception";

    public SpecialFieldsToAttackDecorator create(final int aTier, final int aAmount, final DamageCalculatorIf aCalculator) {
        switch (aTier) {
            case 1:
                return new SpecialFieldsToAttackDecorator.Builder().statistic(SpecialFieldsToAttackStatistic.WALL)
                        .amount(aAmount)
                        .calculator(aCalculator)
                        .build();
            default:
                throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }
}
