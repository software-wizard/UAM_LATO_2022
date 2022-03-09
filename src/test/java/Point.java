//  ******************************************************************
//
//  Copyright 2022 PSI Software AG. All rights reserved.
//  PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
//  ******************************************************************

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Point
{
    private int x;
    private int y;

    Point( Point aPoint )
    {
        x = aPoint.getX();
        y = aPoint.getY();
    }

    Point withX( int aX )
    {
        return new Point( aX, y );
    }

    @Override
    public boolean equals( Object aO )
    {
        if( this == aO )
        {
            return true;
        }
        if( aO == null || getClass() != aO.getClass() )
        {
            return false;
        }
        Point point = (Point)aO;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( x, y );
    }

}
