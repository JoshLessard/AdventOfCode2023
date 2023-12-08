package com.adventofcode2023.dec05;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class CategoryMap {

    private final List<CategoryMappingRange> orderedMappingRanges;

    CategoryMap( List<CategoryMappingRange> mappingRanges ) {
        this.orderedMappingRanges = sortAndFillInRangeGaps( mappingRanges );
    }

    private static List<CategoryMappingRange> sortAndFillInRangeGaps( List<CategoryMappingRange> inputMappingRanges ) {
        List<CategoryMappingRange> mappingRangesWithPossibleGaps = new ArrayList<>( inputMappingRanges );
        mappingRangesWithPossibleGaps.sort( comparing( CategoryMappingRange::mappingRangeStartInclusive ) );

        List<CategoryMappingRange> sortedAndCompleteRanges = new ArrayList<>();
        long previousRangeEndExclusive = 0L;
        for ( CategoryMappingRange currentMappingRange : mappingRangesWithPossibleGaps ) {
            if ( currentMappingRange.mappingRangeStartInclusive() > previousRangeEndExclusive ) {
                CategoryMappingRange nonMappingRangeForGap = new CategoryMappingRange(
                    previousRangeEndExclusive,
                    currentMappingRange.mappingRangeStartInclusive(),
                    0L
                );
                sortedAndCompleteRanges.add( nonMappingRangeForGap );
            }
            sortedAndCompleteRanges.add( currentMappingRange );
            previousRangeEndExclusive = currentMappingRange.mappingRangeEndExclusive();
        }

        return sortedAndCompleteRanges;
    }

    long mapSourceToDestination( long source ) {
        return orderedMappingRanges
            .stream()
            .filter( range -> range.isInMappingRange( source ) )
            .findFirst()
            .map( range -> range.mapSourceToDestination( source ) )
            .orElse( source ); // TODO Need to handle seed range after last mapping range
    }

    List<CategoryRange> mapSourcesToDestinations( CategoryRange sourceRange ) {
        List<CategoryRange> destinationRanges = orderedMappingRanges
            .stream()
            .map( mappingRange -> mappingRange.mapSourcesToDestinations( sourceRange ) )
            .flatMap( Optional::stream )
            .collect( toList() );
        long mappedRangeEndExclusive = orderedMappingRanges.get( orderedMappingRanges.size() - 1 ).mappingRangeEndExclusive();
        sourceRange.subRange( mappedRangeEndExclusive )
            .ifPresent( destinationRanges::add );
        return destinationRanges;
    }
}
