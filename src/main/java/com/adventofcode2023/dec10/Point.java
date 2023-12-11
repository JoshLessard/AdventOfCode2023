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

    Direction incomingDirectionFrom( Point other ) {
        if ( isNorthOf( other ) ) {
            return Direction.NORTH;
        } else if ( isSouthOf( other ) ) {
            return Direction.SOUTH;
        } else if ( isEastOf( other ) ) {
            return Direction.EAST;
        } else if ( isWestOf( other ) ) {
            return Direction.WEST;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isNorthOf( Point other ) {
        return x == other.x && y < other.y;
    }

    private boolean isSouthOf( Point other ) {
        return x == other.x && y > other.y;
    }

    private boolean isEastOf( Point other ) {
        return x > other.x && y == other.y;
    }

    private boolean isWestOf( Point other ) {
        return x < other.x && y == other.y;
    }
}
