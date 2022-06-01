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

    public void clearBoard(){
        for(int x = 0; x<16; x++){
            for(int y = 0; y<10; y++){
                Point point = new Point(x,y);
                if(getCreature( point ).isPresent() && getCreature( point ).get().getCurrentHp() == 0){
                    map.inverse().remove(getCreature( point ).get());
                    map.put( point,null );
                }
            }
        }
    }

    private void addCreatures(final List<Creature> aCreatures, final int aXPosition) {
        for (int i = 0; i < aCreatures.size(); i++) {
            map.put(new Point(aXPosition, i * 2 + 1), aCreatures.get(i));
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
        if( aCreature == null || !aCreature.isAlive() ){
            return false;
        }
        else{
            final Point oldPosition = map.inverse()
                    .get(aCreature);
            return aPoint.distance(oldPosition.getX(), oldPosition.getY()) < aCreature.getMoveRange();
        }

    }

    boolean canAttack(final Creature aCreature, final Point aPoint) {
        if( aCreature == null || !aCreature.isAlive() ){
            return false;
        }
        else{
            final Point currentPosition = map.inverse()
                    .get(aCreature);
            return aPoint.distance(currentPosition.getX(), currentPosition.getY()) <= aCreature.getAttackRange();
        }

    }

    Point getCreaturePosition(final Creature aCreature){
        return map.inverse().get(aCreature);
    }

    List<Point> getAdjacentPositions( final Point point ){
        List<Point> positionsList = new ArrayList<>();
        Point adjacentPoint1 = new Point(point.getX()-1,point.getY()+1 );
        Point adjacentPoint2 = new Point(point.getX(),point.getY()+1 );
        Point adjacentPoint3 = new Point(point.getX()+1,point.getY()+1 );
        Point adjacentPoint4 = new Point(point.getX()-1,point.getY() );
        Point adjacentPoint5 = new Point(point.getX()+1,point.getY() );
        Point adjacentPoint6 = new Point(point.getX()-1,point.getY()-1 );
        Point adjacentPoint7 = new Point(point.getX(),point.getY()-1 );
        Point adjacentPoint8 = new Point(point.getX()+1,point.getY()-1 );
        positionsList.add(adjacentPoint1);
        positionsList.add(adjacentPoint2);
        positionsList.add(adjacentPoint3);
        positionsList.add(adjacentPoint4);
        positionsList.add(adjacentPoint5);
        positionsList.add(adjacentPoint6);
        positionsList.add(adjacentPoint7);
        positionsList.add(adjacentPoint8);
        return positionsList;
    }
}
