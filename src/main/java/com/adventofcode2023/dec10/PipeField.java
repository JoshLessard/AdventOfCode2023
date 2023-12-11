package com.adventofcode2023.dec10;

import java.util.Map;

class PipeField {

    private final Point startingPoint;
    private final Map<Point, Neighbours> neighboursByPoint;

    PipeField( Point startingPoint, Map<Point, Neighbours> neighboursByPoint ) {
        this.startingPoint = startingPoint;
        this.neighboursByPoint = Map.copyOf( neighboursByPoint );
    }

    int largestNumberOfStepsFromStartingPoint() {
        Point previousPointPath1 = startingPoint;
        Point currentPointPath1 = getNeighbour1( startingPoint );
        Point previousPointPath2 = startingPoint;
        Point currentPointPath2 = getNeighbour2( startingPoint );
        int numberOfSteps = 1;
        while ( ! currentPointPath1.equals( currentPointPath2 ) ) {
            Point nextPointPath1 = getNeighbourOtherThan( previousPointPath1, currentPointPath1 );
            previousPointPath1 = currentPointPath1;
            currentPointPath1 = nextPointPath1;

            Point nextPointPath2 = getNeighbourOtherThan( previousPointPath2, currentPointPath2 );
            previousPointPath2 = currentPointPath2;
            currentPointPath2 = nextPointPath2;

            ++numberOfSteps;
        }

        return numberOfSteps;
    }

    private Point getNeighbour1( Point point ) {
        return neighboursByPoint
            .get( point )
            .neighbour1();
    }

    private Point getNeighbour2( Point point ) {
        return neighboursByPoint
            .get( point )
            .neighbour2();
    }

    private Point getNeighbourOtherThan( Point neighbourToAvoid, Point point ) {
        return neighboursByPoint
            .get( point )
            .neighbourOtherThan( neighbourToAvoid );
    }
}
