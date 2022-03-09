//  ******************************************************************
//
//  Copyright 2022 PSI Software AG. All rights reserved.
//  PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
//  ******************************************************************

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class UamTests
{
    @Test
    void x()
    {
        final Set< Point > set = new HashSet<>();
        final Point point1 = new Point( 1, 1 );
        final Point point2 = new Point( 1, 1 );
        final Point copyPoint2 = new Point( point2 );
        set.add( point2 );
        point2.setX( 2 );
        assertThat( point1 ).isNotEqualTo( point2 );
        assertThat( point1 ).isEqualTo( copyPoint2 );
        set.add( point2 );

        final Segment s1 = new Segment( point1, point2 );
        final Segment s2 = new Segment( copyPoint2, point2 );
        assertThat( s1 ).isEqualTo( s2 );

        // s1.getStartPoint()
        // .setX( 3 );
        // assertThat( s1 ).isNotEqualTo( s2 );

        s1.getEndPoint()
            .setX( 3 );
        assertThat( s1 ).isEqualTo( s2 );
    }
}
