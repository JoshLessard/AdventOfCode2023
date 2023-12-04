package com.adventofcode2023.dec03;

import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class EngineSchematic {

    private final int width;
    private final int height;
    private final Map<Point, Character> symbolsByLocation;
    private final List<ValueLocation> valueLocations;

    EngineSchematic( int width, int height, Map<Point, Character> symbolsByLocation, List<ValueLocation> valueLocations ) {
        this.width = width;
        this.height = height;
        this.symbolsByLocation = Map.copyOf( symbolsByLocation );
        this.valueLocations = List.copyOf( valueLocations );
    }

    List<Integer> partNumbers() {
        return valueLocations
            .stream()
            .filter( this::isSymbolAdjacent )
            .map( ValueLocation::value )
            .toList();
    }

    private boolean isSymbolAdjacent( ValueLocation valueLocation ) {
        return ! getSymbolLocationsAdjacentTo( valueLocation ).isEmpty();
    }

    private Set<Point> getSymbolLocationsAdjacentTo( ValueLocation valueLocation ) {
        Set<Point> points = new HashSet<>();
        Point leftmostValuePoint = valueLocation.leftmostPoint();
        Point rightmostValuePoint = valueLocation.rightmostPoint();
        Point topLeft = new Point( Math.max( 0, leftmostValuePoint.x() - 1 ), Math.max( 0, leftmostValuePoint.y() - 1 ) );
        Point bottomRight = new Point( Math.min( width - 1, rightmostValuePoint.x() + 1 ), Math.min( height - 1, rightmostValuePoint.y() + 1 ) );
        for ( int y = topLeft.y(); y <= bottomRight.y(); ++y ) {
            for ( int x = topLeft.x(); x <= bottomRight.x(); ++x ) {
                Point point = new Point( x, y );
                if ( symbolsByLocation.containsKey( point ) ) {
                    points.add( point );
                }
            }
        }

        return points;
    }

    List<Integer> gearRatios() {
        Map<Point, List<Integer>> valuesAdjacentToAsterisks = mapValuesAdjacentToAsterisks();
        return valuesAdjacentToAsterisks.values()
            .stream()
            .filter( values -> values.size() == 2 )
            .map( values -> values.get( 0 ) * values.get( 1 ) )
            .toList();
    }

    private Map<Point, List<Integer>> mapValuesAdjacentToAsterisks() {
        Map<Point, List<Integer>> valuesByAdjacentAsteriskLocation = new HashMap<>();
        for ( ValueLocation valueLocation : valueLocations ) {
            for ( Point asteriskLocation : getAsteriskLocationsAdjacentTo( valueLocation ) ) {
                valuesByAdjacentAsteriskLocation
                    .computeIfAbsent( asteriskLocation, p -> new ArrayList<>() )
                    .add( valueLocation.value() );
            }
        }
        return valuesByAdjacentAsteriskLocation;
    }

    private Set<Point> getAsteriskLocationsAdjacentTo( ValueLocation valueLocation ) {
        return getSymbolLocationsAdjacentTo( valueLocation )
            .stream()
            .filter( point -> symbolsByLocation.get( point ) == '*' )
            .collect( toSet() );
    }
}
