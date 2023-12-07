package com.adventofcode2023.dec05;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.List;

class CategoryMap {

    private final List<CategoryMappingRange> orderedMappingRanges;

    CategoryMap( List<CategoryMappingRange> mappingRanges ) {
        this.orderedMappingRanges = sortAndFillInRangeGaps( mappingRanges );
    }

    private static List<CategoryMappingRange> sortAndFillInRangeGaps( List<CategoryMappingRange> inputMappingRanges ) {
        List<CategoryMappingRange> mappingRangesWithPossibleGaps = new ArrayList<>( inputMappingRanges );
        mappingRangesWithPossibleGaps.sort( comparing( CategoryMappingRange::sourceRangeStartInclusive ) );

        List<CategoryMappingRange> sortedAndCompleteRanges = new ArrayList<>();
        long previousRangeEndExclusive = 0L;
        for ( CategoryMappingRange currentMappingRange : mappingRangesWithPossibleGaps ) {
            if ( currentMappingRange.sourceRangeStartInclusive() > previousRangeEndExclusive ) {
                CategoryMappingRange mappingRangeForGap = new CategoryMappingRange( previousRangeEndExclusive, previousRangeEndExclusive, currentMappingRange.sourceRangeStartInclusive() - previousRangeEndExclusive );
                sortedAndCompleteRanges.add( mappingRangeForGap );
            }
            sortedAndCompleteRanges.add( currentMappingRange );
            previousRangeEndExclusive = currentMappingRange.sourceRangeEndExclusive();
        }

        return sortedAndCompleteRanges;
    }

    long mapSourceToDestination( long source ) {
        return orderedMappingRanges
            .stream()
            .filter( range -> range.isInSourceRange( source ) )
            .findFirst()
            .map( range -> range.mapSourceToDestination( source ) )
            .orElse( source ); // TODO Need to handle seed range after last mapping range
    }
}
