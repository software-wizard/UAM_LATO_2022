package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.creatures.Creature;
import pl.psi.specialfields.Field;

import java.util.List;
import java.util.Optional;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Board {
    private static final int MAX_WITDH = 14;
    private final BiMap<Point, Creature> map = HashBiMap.create();
    private final BiMap<Point, Field> fieldsMap = HashBiMap.create();

    public Board(final List<Creature> aCreatures1, final List<Creature> aCreatures2, final List<Field> aFields) {
        addCreatures(aCreatures1, 0);
        addCreatures(aCreatures2, MAX_WITDH);
        addFields(aFields);
    }

    private void addCreatures(final List<Creature> aCreatures, final int aXPosition) {
        for (int i = 0; i < aCreatures.size(); i++) {
            map.put(new Point(aXPosition, i * 2 + 1), aCreatures.get(i));
        }
    }

    private void addFields(final List<Field> aFields) {
        for (int i = 0; i < aFields.size(); i++) {
            fieldsMap.put(new Point(i, i), aFields.get(i));
        }
    }

    Optional<Creature> getCreature(final Point aPoint) {
        return Optional.ofNullable(map.get(aPoint));
    }

    Optional<Field> getField(final Point aPoint) {
        return Optional.ofNullable(fieldsMap.get(aPoint));
    }

    void move(final Creature aCreature, final Point aPoint) {
        if (canMove(aCreature, aPoint)) {
            map.inverse()
                    .remove(aCreature);
            map.put(aPoint, aCreature);
        }
    }

    boolean canMove(final Creature aCreature, final Point aPoint) {
        final Point oldPosition = map.inverse()
                .get(aCreature);
        return aPoint.distance(oldPosition.getX(), oldPosition.getY()) < aCreature.getMoveRange();
    }
}
