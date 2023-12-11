package com.adventofcode2023.dec10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class PipeField {

    private final int width;
    private final int height;
    private final Point startingPoint;
    private final Map<Point, Neighbours> neighboursByPoint;
    private final Map<Point, TileType> tileTypeByPoint;

    PipeField( int width, int height, Point startingPoint, Map<Point, Neighbours> neighboursByPoint, Map<Point, TileType> tileTypeByPoint ) {
        this.width = width;
        this.height = height;
        this.startingPoint = startingPoint;
        this.neighboursByPoint = Map.copyOf( neighboursByPoint );
        this.tileTypeByPoint = Map.copyOf( tileTypeByPoint );
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

    int numberOfEnclosedTiles() {
        // TODO Encapsulate main loop?
        List<Point> mainLoop = getMainLoop();
        Set<Point> mainLoopAsSet = Set.copyOf( mainLoop );
        Direction currentScanDirection = getStartingScanDirection( mainLoop, mainLoopAsSet );
        Point previousPoint = null;
        Set<Point> enclosedTileLocations = new HashSet<>();

        for ( Point mainLoopPoint : mainLoop ) {
            enclosedTileLocations.addAll( enclosedTileLocations( mainLoopAsSet, mainLoopPoint, currentScanDirection ) );

            // TODO Duplication with counting tiles
            if ( previousPoint != null && isCorner( mainLoopPoint ) ) {
                Direction incomingDirection = mainLoopPoint.incomingDirectionFrom( previousPoint );
                TileType mainLoopPointType = tileTypeByPoint.get( mainLoopPoint );
                Turn turn = mainLoopPointType.turnWhenEnteringFrom( incomingDirection );
                currentScanDirection = turn.applyTo( currentScanDirection );

                // scan in the "scan direction" after turning the corner
                enclosedTileLocations.addAll( enclosedTileLocations( mainLoopAsSet, mainLoopPoint, currentScanDirection ) );
            }
            previousPoint = mainLoopPoint;
        }

        return enclosedTileLocations.size();
    }

    private Set<Point> enclosedTileLocations( Set<Point> mainLoopAsSet, Point startingPoint, Direction scanDirection ) {
        Set<Point> enclosedTileLocations = new HashSet<>();
        Point currentPoint = startingPoint;
        while ( true ) {
            currentPoint = scanDirection.applyTo( currentPoint );
            if ( mainLoopAsSet.contains( currentPoint ) ) {
                // we've run into the other side of the loop
                break;
            }
            enclosedTileLocations.add( currentPoint );
        }

        return enclosedTileLocations;
    }

    private List<Point> getMainLoop() {
        List<Point> mainLoop = new ArrayList<>();
        Point previousPoint = startingPoint;
        Point currentPoint = getNeighbour1( startingPoint );
        mainLoop.add( previousPoint );
        mainLoop.add( currentPoint );
        while ( ! currentPoint.equals( startingPoint ) ) {
            Point nextPoint = getNeighbourOtherThan( previousPoint, currentPoint );
            mainLoop.add( nextPoint );
            previousPoint = currentPoint;
            currentPoint = nextPoint;
        }

        return mainLoop;
    }

    private Direction getStartingScanDirection( List<Point> mainLoop, Set<Point> mainLoopAsSet ) {
        Direction scanDirectionGuess = guessScanDirection( mainLoop );
        Direction currentScanDirection = scanDirectionGuess;
        Point previousPoint = null;
        for ( Point mainLoopPoint : mainLoop ) {
            if ( reachesEdgeWithoutIntersecting( mainLoopAsSet, mainLoopPoint, currentScanDirection ) ) {
                return scanDirectionGuess.oppositeDirection();
            }
            // TODO Duplication with counting tiles
            if ( previousPoint != null && isCorner( mainLoopPoint ) ) {
                Direction incomingDirection = mainLoopPoint.incomingDirectionFrom( previousPoint );
                TileType mainLoopPointType = tileTypeByPoint.get( mainLoopPoint );
                Turn turn = mainLoopPointType.turnWhenEnteringFrom( incomingDirection );
                currentScanDirection = turn.applyTo( currentScanDirection );

                // scan in the guessed direction after turning the corner
                if ( reachesEdgeWithoutIntersecting( mainLoopAsSet, mainLoopPoint, currentScanDirection ) ) {
                    return scanDirectionGuess.oppositeDirection();
                }
            }
            previousPoint = mainLoopPoint;
        }
        return scanDirectionGuess;
    }

    private Direction guessScanDirection( List<Point> mainLoop ) {
        Point firstPoint = mainLoop.get( 0 );
        Point secondPoint = mainLoop.get( 1 );
        Direction fromFirstPointToSecond = secondPoint.incomingDirectionFrom( firstPoint );
        return switch ( fromFirstPointToSecond ) {
            case NORTH, SOUTH -> Direction.WEST;
            case EAST, WEST -> Direction.NORTH;
        };
    }

    private boolean reachesEdgeWithoutIntersecting( Set<Point> points, Point currentPoint, Direction direction ) {
        while ( true ) {
            currentPoint = direction.applyTo( currentPoint );
            if ( points.contains( currentPoint ) ) {
                // intersected one of the points
                return false;
            } else if ( isOutOfBounds( currentPoint ) ) {
                return true;
            }
        }
    }

    private boolean isCorner( Point point ) {
        TileType tileType = tileTypeByPoint.get( point );
        if ( tileType == null ) {
            throw new IllegalArgumentException( "Point at location " + point + " does not have a tile type configured." );
        }
        return tileType.isCorner();
    }

    private boolean isOutOfBounds( Point point ) {
        return point.x() < 0 || point.x() >= width || point.y() < 0 || point.y() >= height;
    }
}
