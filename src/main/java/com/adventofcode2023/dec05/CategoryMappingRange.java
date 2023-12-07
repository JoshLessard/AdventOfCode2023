package com.adventofcode2023.dec05;

class CategoryMappingRange {

    private final long sourceRangeStartInclusive;
    private final long sourceRangeEndExclusive;
    private final long sourceToDestinationDelta;

    CategoryMappingRange( long sourceRangeStartInclusive, long sourceRangeEndExclusive, long sourceToDestinationDelta ) {
        this.sourceRangeStartInclusive = sourceRangeStartInclusive;
        this.sourceRangeEndExclusive = sourceRangeEndExclusive;
        this.sourceToDestinationDelta = sourceToDestinationDelta;
    }

    long sourceRangeStartInclusive() {
        return sourceRangeStartInclusive;
    }

    long sourceRangeEndExclusive() {
        return sourceRangeEndExclusive;
    }

    boolean isInSourceRange( long source ) {
        return sourceRangeStartInclusive <= source && source < sourceRangeEndExclusive;
    }

    long mapSourceToDestination( long source ) {
        return source + sourceToDestinationDelta;
    }
}
