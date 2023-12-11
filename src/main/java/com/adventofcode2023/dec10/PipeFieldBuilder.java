package com.adventofcode2023.dec10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class PipeFieldBuilder {

    private int currentY = 0;
    private Point startingPoint;
    private final Map<Point, Neighbours> neighboursByPoint = new HashMap<>();

    void parseRow( String input ) {
        for ( int currentX = 0; currentX < input.length(); ++currentX ) {
            Point currentPoint = new Point( currentX, currentY );
            char currentTile = input.charAt( currentX );
            getNeighbours( currentPoint, currentTile )
                .ifPresent( neighbours -> neighboursByPoint.put( currentPoint, neighbours ) );
        }
        ++currentY;
    }

    private Optional<Neighbours> getNeighbours( Point currentPoint, char tile ) {
        return switch ( tile ) {
            case '|' -> Optional.of( new Neighbours( currentPoint.north(), currentPoint.south() ) );
            case '-' -> Optional.of( new Neighbours( currentPoint.east(), currentPoint.west() ) );
            case 'L' -> Optional.of( new Neighbours( currentPoint.north(), currentPoint.east() ) );
            case 'J' -> Optional.of( new Neighbours( currentPoint.north(), currentPoint.west() ) );
            case '7' -> Optional.of( new Neighbours( currentPoint.south(), currentPoint.west() ) );
            case 'F' -> Optional.of( new Neighbours( currentPoint.south(), currentPoint.east() ) );
            case '.' -> Optional.empty();
            case 'S' -> {
                startingPoint = currentPoint;
                yield Optional.empty();
            }
            default -> throw new IllegalArgumentException();
        };
    }

    PipeField build() {
        addStartingPointNeighbours();
        return new PipeField( startingPoint, neighboursByPoint );
    }

    private void addStartingPointNeighbours() {
        List<Point> startingPointNeighbours = new ArrayList<>();
        startingPoint.neighbours()
            .filter( p ->
                neighboursByPoint.containsKey( p )
                && neighboursByPoint.get( p ).contains( startingPoint )
            )
            .forEach( startingPointNeighbours::add );
        if ( startingPointNeighbours.size() != 2 ) {
            throw new IllegalStateException();
        }
        neighboursByPoint.put( startingPoint, new Neighbours( startingPointNeighbours.get( 0 ), startingPointNeighbours.get( 1 ) ) );
    }
}
