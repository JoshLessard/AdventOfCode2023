package com.adventofcode2023.dec05;

class CategoryMappingRange {

    private final long sourceRangeStartInclusive;
    private final long sourceRangeEndExclusive;
    private final long sourceToDestinationDelta;

    CategoryMappingRange( long destinationRangeStart, long sourceRangeStart, long rangeSize ) {
        this.sourceRangeStartInclusive = sourceRangeStart;
        this.sourceRangeEndExclusive = sourceRangeStart + rangeSize;
        this.sourceToDestinationDelta = destinationRangeStart - sourceRangeStart;
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
