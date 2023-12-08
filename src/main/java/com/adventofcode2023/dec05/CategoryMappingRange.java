package com.adventofcode2023.dec05;

import java.util.Optional;

class CategoryMappingRange {

    private final long mappingRangeStartInclusive;
    private final long mappingRangeEndExclusive;
    private final long mappingDelta;

    CategoryMappingRange( long mappingRangeStartInclusive, long mappingRangeEndExclusive, long mappingDelta ) {
        this.mappingRangeStartInclusive = mappingRangeStartInclusive;
        this.mappingRangeEndExclusive = mappingRangeEndExclusive;
        this.mappingDelta = mappingDelta;
    }

    long mappingRangeStartInclusive() {
        return mappingRangeStartInclusive;
    }

    long mappingRangeEndExclusive() {
        return mappingRangeEndExclusive;
    }

    Optional<CategoryRange> mapSourcesToDestinations( CategoryRange sourceRange ) {
        long destinationRangeStartInclusive = Math.max( sourceRange.startInclusive(), mappingRangeStartInclusive );
        long destinationRangeEndExclusive = Math.min( sourceRange.endExclusive(), mappingRangeEndExclusive );
        return destinationRangeStartInclusive < destinationRangeEndExclusive
            ? Optional.of( new CategoryRange( destinationRangeStartInclusive + mappingDelta, destinationRangeEndExclusive + mappingDelta ) )
            : Optional.empty();
    }
}
