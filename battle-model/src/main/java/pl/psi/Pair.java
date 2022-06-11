package pl.psi;

import pl.psi.creatures.Creature;

public class Pair {
    private final Creature creature;
    private final Point point;
    public Pair(Creature aCreature, Point aPoint){
        creature = aCreature;
        point = aPoint;
    }
    public Creature getCreature(){ return creature; }
    public Point getPoint(){ return point; }
}