package com.adventofcode2023.dec11;

import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

class Universe {

    private final int initialWidth;
    private final int initialHeight;
    private final Map<Long, List<PointContainer>> galaxyLocationsByRow;
    private final Map<Long, List<PointContainer>> galaxyLocationsByColumn;

    Universe( int initialWidth, int initialHeight, Map<Long, List<PointContainer>> galaxyLocationsByRow, Map<Long, List<PointContainer>> galaxyLocationsByColumn ) {
        this.initialWidth = initialWidth;
        this.initialHeight = initialHeight;
        this.galaxyLocationsByRow = galaxyLocationsByRow;
        this.galaxyLocationsByColumn = galaxyLocationsByColumn;
    }

    void expand( long expansionFactor ) {
        expandEmptyRows( expansionFactor - 1 );
        expandEmptyColumns( expansionFactor - 1 );
    }

    private void expandEmptyRows( long expansionDelta ) {
        long[] expansionDeltasByRow = getExpansionDeltasByRow( expansionDelta );
        for ( int row = initialHeight - 1; row >= 0; --row ) {
            if ( expansionDeltasByRow[row] > 0 && rowHasGalaxies( row ) ) {
                long rowExpansionDelta = expansionDeltasByRow[row];
                List<PointContainer> galaxyLocations = galaxyLocationsByRow.remove( (long) row );
                galaxyLocations
                    .forEach( galaxyLocation -> galaxyLocation.transformY( rowExpansionDelta ) );
                galaxyLocationsByRow.put( row + rowExpansionDelta, galaxyLocations );
            }
        }
    }

    private void expandEmptyColumns( long expansionDelta ) {
        long[] expansionDeltasByColumn = getExpansionDeltasByColumn( expansionDelta );
        for ( int column = initialWidth - 1; column >= 0; --column ) {
            if ( expansionDeltasByColumn[column] > 0 && columnHasGalaxies( column ) ) {
                long columnExpansionDelta = expansionDeltasByColumn[column];
                List<PointContainer> galaxyLocations = galaxyLocationsByColumn.remove( (long) column );
                galaxyLocations
                    .forEach( galaxyLocation -> galaxyLocation.transformX( columnExpansionDelta ) );
                galaxyLocationsByColumn.put( column + columnExpansionDelta, galaxyLocations );
            }
        }
    }

    private long[] getExpansionDeltasByRow( long expansionDelta ) {
        long[] expansionDeltasByRow = new long[initialHeight];
        for (int row = 0; row < initialHeight; ++row ) {
            if ( ! rowHasGalaxies( row ) ) {
                for (int y = row; y < initialHeight; ++y ) {
                    expansionDeltasByRow[y] += expansionDelta;
                }
            }
        }
        return expansionDeltasByRow;
    }

    private long[] getExpansionDeltasByColumn( long expansionDelta ) {
        long[] expansionDeltasByColumn = new long[initialWidth];
        for (int column = 0; column < initialWidth; ++column ) {
            if ( ! columnHasGalaxies( column ) ) {
                for (int x = column; x < initialWidth; ++x ) {
                    expansionDeltasByColumn[x] += expansionDelta;
                }
            }
        }
        return expansionDeltasByColumn;
    }

    private boolean rowHasGalaxies( long row ) {
        return galaxyLocationsByRow.containsKey( row );
    }

    private boolean columnHasGalaxies( long column ) {
        return galaxyLocationsByColumn.containsKey( column );
    }

    long galaxyShortestPathSum() {
        List<Point> uniqueGalaxyLocations = new ArrayList<>( getUniqueGalaxyLocations() );
        long shortestPathSum = 0L;
        for ( int i = 0; i < uniqueGalaxyLocations.size() - 1; ++i ) {
            for ( int j = i + 1; j < uniqueGalaxyLocations.size(); ++j ) {
                Point point1 = uniqueGalaxyLocations.get( i );
                Point point2 = uniqueGalaxyLocations.get( j );
                shortestPathSum += point1.manhattanDistanceTo( point2 );
            }
        }

        return shortestPathSum;
    }

    private Set<Point> getUniqueGalaxyLocations() {
        return Stream.concat( galaxyLocationsByColumn.values().stream(), galaxyLocationsByRow.values().stream() )
            .flatMap( List::stream )
            .map( PointContainer::point )
            .collect( toSet() );
    }
}
