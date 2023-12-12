package com.adventofcode2023.dec11;

record Point( long x, long y ) {

    Point transformX( long delta ) {
        return new Point( x + delta, y );
    }

    Point transformY( long delta ) {
        return new Point( x, y + delta );
    }

    long manhattanDistanceTo( Point other ) {
        return Math.abs( other.x - x ) + Math.abs( other.y - y );
    }
}
