package com.adventofcode2023.dec10;

record Neighbours( Point neighbour1, Point neighbour2 ) {

    boolean contains( Point point ) {
        return neighbour1.equals( point ) || neighbour2.equals( point );
    }

    Point neighbourOtherThan( Point neighbourToAvoid ) {
        return neighbourToAvoid.equals( neighbour1 )
            ? neighbour2
            : neighbour1;
    }
}
