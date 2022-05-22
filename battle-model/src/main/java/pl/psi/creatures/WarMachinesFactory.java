package pl.psi.creatures;

public class WarMachinesFactory {

    private static final String EXCEPTION_MESSAGE = "Illegal Argument Exception";

    public WarMachinesCreatureDecorator create(final boolean aIsUpgraded, final int aTier, final int aAmount) {
            switch (aTier) {
                case 1:
                    return new WarMachinesCreatureDecorator.Builder().statistic(WarMachinesStatistic.BALLISTA)
                            .amount(aAmount)
                            .build();
                case 2:
                    return new WarMachinesCreatureDecorator.Builder().statistic(WarMachinesStatistic.FIRST_AID_TENT)
                            .amount(aAmount)
                            .build();
                case 3:
                    return new WarMachinesCreatureDecorator.Builder().statistic(WarMachinesStatistic.CATAPULT)
                            .amount(aAmount)
                            .build();
                default:
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
    }
}
