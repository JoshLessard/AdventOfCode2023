package com.adventofcode2023.dec05;

class CategoryRange {

    private final long sourceRangeStart;
    private final long rangeSize;
    private final long sourceToDestinationDelta;

    CategoryRange( long destinationRangeStart, long sourceRangeStart, long rangeSize ) {
        this.sourceRangeStart = sourceRangeStart;
        this.rangeSize = rangeSize;
        this.sourceToDestinationDelta = destinationRangeStart - sourceRangeStart;
    }

    boolean isInSourceRange( long source ) {
        return sourceRangeStart <= source && source < sourceRangeStart + rangeSize;
    }

    long mapSourceToDestination( long source ) {
        return source + sourceToDestinationDelta;
    }
}
