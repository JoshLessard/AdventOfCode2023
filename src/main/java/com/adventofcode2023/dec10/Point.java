package com.adventofcode2023.dec10;

import java.util.stream.Stream;

record Point( int x, int y ) {

    Point north() {
        return new Point( x, y - 1 );
    }

    Point south() {
        return new Point( x, y + 1 );
    }

    Point east() {
        return new Point( x + 1, y );
    }

    Point west() {
        return new Point( x - 1, y );
    }

    Stream<Point> neighbours() {
        return Stream.of( north(), south(), east(), west() );
    }
}
