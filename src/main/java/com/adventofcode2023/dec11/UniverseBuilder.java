package com.adventofcode2023.dec11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UniverseBuilder {

    private int currentWidth = 0;
    private int currentHeight = 0;
    private final Map<Long, List<PointContainer>> galaxyLocationsByRow = new HashMap<>();
    private final Map<Long, List<PointContainer>> galaxyLocationsByColumn = new HashMap<>();

    void parseLine( String input ) {
        for ( int x = 0; x < input.length(); ++x ) {
            if ( input.charAt( (int) x ) == '#' ) {
                PointContainer galaxyLocation = new PointContainer( new Point( x, currentHeight ) );
                galaxyLocationsByRow.computeIfAbsent( (long) currentHeight, row -> new ArrayList<>() )
                    .add( galaxyLocation );
                galaxyLocationsByColumn.computeIfAbsent( (long) x, column -> new ArrayList<>() )
                    .add( galaxyLocation );
            }
        }
        currentWidth = Math.max( currentWidth, input.length() );
        ++currentHeight;
    }

    Universe build() {
        return new Universe( currentWidth, currentHeight, galaxyLocationsByRow, galaxyLocationsByColumn );
    }
}
