package pl.psi;

import pl.psi.creatures.Creature;

class Pair {
    private Creature creature;
    private Point point;
    public Pair(Creature aCreature, Point aPoint){
        creature = aCreature;
        point = aPoint;
    }
    public Creature getCreature(){ return creature; }
    public Point getPoint(){ return point; }
}