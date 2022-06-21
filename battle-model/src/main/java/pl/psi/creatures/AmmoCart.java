package pl.psi.creatures;

import pl.psi.TurnQueue;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class AmmoCart extends AbstractWarMachines {


    public AmmoCart(Creature aDecorated, WarMachineActionType actionType,int aSkillLevel) {
        super(aDecorated, actionType, aSkillLevel);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        super.propertyChange(evt);
        if (TurnQueue.NEW_TURN.equals(evt.getPropertyName())) {
            List<Creature> creatures = (List<Creature>) evt.getNewValue();
            creatures.stream()
                    .filter(creature -> creature instanceof ShooterCreatureDecorator)
                    .map(ShooterCreatureDecorator.class::cast)
                    .forEach(ShooterCreatureDecorator::resetShots);
        }
    }

}
