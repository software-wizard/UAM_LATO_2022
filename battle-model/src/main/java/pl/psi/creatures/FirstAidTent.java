package pl.psi.creatures;

import pl.psi.TurnQueue;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FirstAidTent extends AbstractWarMachines {

    final private Random random = new Random();

    public FirstAidTent(Creature aDecorated, WarMachineActionType actionType, int aSkillLevel) {
        super(aDecorated, actionType, aSkillLevel);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        super.propertyChange(evt);
        if (TurnQueue.NEW_TURN.equals(evt.getPropertyName())) {
            List<Creature> creatureList = (List<Creature>) evt.getNewValue();

            int maxHp = calculateHealHp(getSkillLevel());
            double hp = random.nextInt(maxHp - 1) + 1;

            List<Creature> creatures = new ArrayList<>(creatureList);
            Collections.shuffle(creatures);

            creatures.stream()
                    .filter(Creature::isAlive)
                    .findAny()
                    .ifPresent(creature -> creature.heal(hp));
        }

    }

    public void healCreature(Creature creature) {
        int maxHp = calculateHealHp(getSkillLevel());
        double hp = random.nextInt(maxHp - 1) + 1;

        creature.heal(hp);
    }

    private int calculateHealHp(int skillLevel) {
        switch (skillLevel) {
            case 0:
                return 25;
            case 1:
                return 50;
            case 2:
                return 75;
            case 3:
                return 100;
            default:
                return 0;
        }
    }
}
