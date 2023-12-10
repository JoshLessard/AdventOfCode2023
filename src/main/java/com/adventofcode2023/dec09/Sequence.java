package com.adventofcode2023.dec09;

import java.util.ArrayList;
import java.util.List;

class Sequence {

    private final List<Long> originalValues;

    Sequence( List<Long> originalValues ) {
        this.originalValues = List.copyOf( originalValues );
    }

    long nextValue() {
        return nextValue( originalValues );
    }

    private long nextValue( List<Long> values ) {
        if ( areAllZeroes( values ) ) {
            return 0;
        }
        return values.get( values.size() - 1 ) + nextValue( differences( values ) );
    }

    private boolean areAllZeroes( List<Long> values ) {
        return values
            .stream()
            .allMatch( value -> value == 0L );
    }

    private List<Long> differences( List<Long> values ) {
        List<Long> differences = new ArrayList<>( values.size() - 1 );
        for ( int i = 1; i < values.size(); ++i ) {
            differences.add( values.get( i ) - values.get( i - 1 ) );
        }
        return differences;
    }

    long previousValue() {
        return previousValue( originalValues );
    }

    private long previousValue( List<Long> values ) {
        if ( areAllZeroes( values ) ) {
            return 0L;
        }
        return values.get( 0 ) - previousValue( differences( values ) );
    }
}
