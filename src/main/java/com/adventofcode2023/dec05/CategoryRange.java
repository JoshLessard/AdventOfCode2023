package com.adventofcode2023.dec05;

import java.util.Optional;

record CategoryRange( long startInclusive, long endExclusive ) {

    Optional<CategoryRange> rangeSuffix( long startInclusive ) {
        long suffixStartInclusive = Math.max( this.startInclusive, startInclusive );
        return suffixStartInclusive < endExclusive
            ? Optional.of( new CategoryRange( suffixStartInclusive, endExclusive ) )
            : Optional.empty();
    }
}
