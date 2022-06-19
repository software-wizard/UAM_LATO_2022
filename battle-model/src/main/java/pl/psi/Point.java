package pl.psi;

import lombok.Value;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Value
public class Point {
    private final int x;
    private final int y;

    public Point(final int aX, final int aY) {
        x = aX;
        y = aY;
    }

    public double distance(double px, double py) {
        px -= getX();
        py -= getY();
        return Math.sqrt(px * px + py * py);
    }

    public Point normalize(){
        int x;
        int y;
        x = Integer.compare(0,getX());
        y = Integer.compare(0,getY());
        return new Point(x,y);
    }
}
