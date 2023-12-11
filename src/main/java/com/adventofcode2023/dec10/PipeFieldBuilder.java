package com.adventofcode2023.dec10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

class PipeFieldBuilder {

    private int currentWidth = 0;
    private int currentHeight = 0;
    private Point startingPoint;
    private final Map<Point, Neighbours> neighboursByPoint = new HashMap<>();
    private final Map<Point, TileType> tileTypeByPoint = new HashMap<>();

    void parseRow( String input ) {
        for ( int currentX = 0; currentX < input.length(); ++currentX ) {
            Point currentPoint = new Point( currentX, currentHeight );
            char currentTile = input.charAt( currentX );
            toTileType( currentTile )
                .ifPresent( tileType -> tileTypeByPoint.put( currentPoint, tileType ) );
            getNeighbours( currentPoint, currentTile )
                .ifPresent( neighbours -> neighboursByPoint.put( currentPoint, neighbours ) );
        }
        currentWidth = Math.max( currentWidth, input.length() );
        ++currentHeight;
    }

    private Optional<TileType> toTileType( char tile ) {
        return switch ( tile ) {
            case '|' -> Optional.of( TileType.VERTICAL );
            case '-' -> Optional.of( TileType.HORIZONTAL );
            case 'L' -> Optional.of( TileType.NORTH_AND_EAST );
            case 'J' -> Optional.of( TileType.NORTH_AND_WEST );
            case '7' -> Optional.of( TileType.SOUTH_AND_WEST );
            case 'F' -> Optional.of( TileType.SOUTH_AND_EAST );
            case '.' -> Optional.of( TileType.GROUND );
            case 'S' -> Optional.empty();
            default -> throw new IllegalArgumentException();
        };
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
        configureStartingPoint();
        return new PipeField( currentWidth, currentHeight, startingPoint, neighboursByPoint, tileTypeByPoint );
    }

    private void configureStartingPoint() {
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
        Neighbours neighbours = new Neighbours( startingPointNeighbours.get( 0 ), startingPointNeighbours.get( 1 ) );
        neighboursByPoint.put( startingPoint, neighbours );

        Set<Direction> startingPointOutgoingDirections = neighbours.incomingDirectionsFrom( startingPoint );
        tileTypeByPoint.put( startingPoint, tileTypeForOutgoingDirections( startingPointOutgoingDirections ) );
    }

    private TileType tileTypeForOutgoingDirections( Set<Direction> outgoingDirections ) {
        if ( outgoingDirections.equals( Set.of( Direction.NORTH, Direction.EAST ) ) ) {
            return TileType.NORTH_AND_EAST;
        } else if ( outgoingDirections.equals( Set.of( Direction.NORTH, Direction.WEST ) ) ) {
            return TileType.NORTH_AND_WEST;
        } else if ( outgoingDirections.equals( Set.of( Direction.SOUTH, Direction.WEST ) ) ) {
            return TileType.SOUTH_AND_WEST;
        } else if ( outgoingDirections.equals( Set.of( Direction.SOUTH, Direction.EAST ) ) ) {
            return TileType.SOUTH_AND_EAST;
        }

        throw new IllegalArgumentException();
    }
}
