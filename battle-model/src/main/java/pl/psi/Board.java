package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.creatures.Creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Board {
    private static final int MAX_WITDH = 14;
    private final BiMap<Point, Creature> map = HashBiMap.create();

    public Board(final List<Creature> aCreatures1, final List<Creature> aCreatures2) {
        addCreatures(aCreatures1, 0);
        addCreatures(aCreatures2, MAX_WITDH);
    }

    public boolean canCreatureAttackAnyone(Creature aCreature) {
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 10; y++) {
                Point point = new Point(x, y);
                if (getCreature(point).isPresent() && !getCreature(point).get().getName().equals(aCreature.getName()) && canAttack(aCreature, point) && getCreature(point).get().isAlive()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void addCreatures(final List<Creature> aCreatures, final int aXPosition) {
        for (int i = 0; i < aCreatures.size(); i++) {
            if (i > 4) {
                if (aXPosition > 2) {
                    map.put(new Point(aXPosition - 1, (i - 5) * 2), aCreatures.get(i));
                } else {
                    map.put(new Point(aXPosition + 1, (i - 5) * 2), aCreatures.get(i));
                }
            } else {
                map.put(new Point(aXPosition, i * 2 + 1), aCreatures.get(i));
            }
        }
    }

    Optional<Creature> getCreature(final Point aPoint) {
        return Optional.ofNullable(map.get(aPoint));
    }

    void move(final Creature aCreature, final Point aPoint) {
        if (canMove(aCreature, aPoint)) {
            map.inverse()
                    .remove(aCreature);
            map.put(aPoint, aCreature);
        }
    }

    boolean canMove(final Creature aCreature, final Point aPoint) {
        if (aCreature == null || !aCreature.isAlive()) {
            return false;
        } else {
            final Point oldPosition = map.inverse()
                    .get(aCreature);
            return aPoint.distance(oldPosition.getX(), oldPosition.getY()) < aCreature.getMoveRange();
        }

    }

    boolean canAttack(final Creature aCreature, final Point aPoint) {
        if (aCreature == null  || !getCreature(aPoint).get().isAlive()) {
            return false;
        } else {
            final Point currentPosition = map.inverse()
                    .get(aCreature);
            if (getCreature(aPoint).isPresent()) {
                return aPoint.distance(currentPosition.getX(), currentPosition.getY()) <= aCreature.getAttackRange() && getCreature(aPoint).get().getHeroNumber() != aCreature.getHeroNumber();

            } else {
                return aPoint.distance(currentPosition.getX(), currentPosition.getY()) <= aCreature.getAttackRange();

            }
        }

    }

    Point getCreaturePosition(final Creature aCreature) {
        return map.inverse().get(aCreature);
    }

    List<Point> getAdjacentPositions(final Point aPoint) {
        List<Point> positionsList = new ArrayList<>();
        if(aPoint != null){
            Point adjacentPoint1 = new Point(aPoint.getX() - 1, aPoint.getY() + 1);
            Point adjacentPoint2 = new Point(aPoint.getX(), aPoint.getY() + 1);
            Point adjacentPoint3 = new Point(aPoint.getX() + 1, aPoint.getY() + 1);
            Point adjacentPoint4 = new Point(aPoint.getX() - 1, aPoint.getY());
            Point adjacentPoint5 = new Point(aPoint.getX() + 1, aPoint.getY());
            Point adjacentPoint6 = new Point(aPoint.getX() - 1, aPoint.getY() - 1);
            Point adjacentPoint7 = new Point(aPoint.getX(), aPoint.getY() - 1);
            Point adjacentPoint8 = new Point(aPoint.getX() + 1, aPoint.getY() - 1);
            positionsList.add(adjacentPoint1);
            positionsList.add(adjacentPoint2);
            positionsList.add(adjacentPoint3);
            positionsList.add(adjacentPoint4);
            positionsList.add(adjacentPoint5);
            positionsList.add(adjacentPoint6);
            positionsList.add(adjacentPoint7);
            positionsList.add(adjacentPoint8);
        }
        return positionsList;
    }

    public List<Point> getCreatureSplashDamagePointsList(Creature aCreature, Point aDefender) {
        Integer[][] area = aCreature.getSplashDamageRange();
        List<Point> positionsList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (area[i][j] == 1) {
                    positionsList.add(new Point(aDefender.getX() - 1 + j, aDefender.getY() + 1 - i));
                }
            }
        }
        return positionsList;
    }

    public List<Point> getPathToPoint(Point aStartingPoint, Point aEndPoint) {
        Point difference;
        int currentX = aStartingPoint.getX();
        int currentY = aStartingPoint.getY();
        List<Point> path = new ArrayList<>();
        while (true) {
            difference = new Point(currentX - aEndPoint.getX(), currentY - aEndPoint.getY());
            difference = difference.normalize();
            if (currentX == aEndPoint.getX() && currentY == aEndPoint.getY()) {
                break;
            }
            currentX = currentX + difference.getX();
            currentY = currentY + difference.getY();
            path.add(new Point(currentX, currentY));
        }
        return path;
    }

    public void putDeadCreaturesOnBoard(List<Creature> aCreaturesList, List<Point> aPointList) {
        for(int i = 0; i <aCreaturesList.size(); i++){
            int finalI = i;
            map.computeIfAbsent(aPointList.get(i), k -> aCreaturesList.get(finalI));
        }
    }
}
