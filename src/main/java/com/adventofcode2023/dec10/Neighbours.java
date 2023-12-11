package com.adventofcode2023.dec10;

import java.util.Set;

record Neighbours( Point neighbour1, Point neighbour2 ) {

    boolean contains( Point point ) {
        return neighbour1.equals( point ) || neighbour2.equals( point );
    }

    Point neighbourOtherThan( Point neighbourToAvoid ) {
        return neighbourToAvoid.equals( neighbour1 )
            ? neighbour2
            : neighbour1;
    }

    Set<Direction> incomingDirectionsFrom( Point point ) {
        return Set.of(
            neighbour1.incomingDirectionFrom( point ),
            neighbour2.incomingDirectionFrom( point )
        );
    }
}
