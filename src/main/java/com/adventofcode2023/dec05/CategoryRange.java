package com.adventofcode2023.dec05;

import java.util.Optional;

record CategoryRange( long startInclusive, long endExclusive ) {

    Optional<CategoryRange> subRange( long subRangeStartInclusive ) {
        long start = Math.max( startInclusive, subRangeStartInclusive );
        return start < endExclusive
            ? Optional.of( new CategoryRange( start, endExclusive ) )
            : Optional.empty();
    }
}
