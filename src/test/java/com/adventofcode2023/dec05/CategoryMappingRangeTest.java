package com.adventofcode2023.dec05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class CategoryMappingRangeTest {

    private static final long DUMMY_DELTA = 43342902342345L;

    @Test
    public void sourceRangeThatBeginsAndEndsBeforeMappingRangeStartIsNotMapped() {
        CategoryRange sourceRange = new CategoryRange( 10, 25 );
        CategoryMappingRange mappingRange = new CategoryMappingRange( 30, 50, DUMMY_DELTA );

        Optional<CategoryRange> destinationRange = mappingRange.mapSourcesToDestinations( sourceRange );

        assertTrue( destinationRange.isEmpty() );
    }

    @Test
    public void sourceRangeThatBeginsBeforeAndEndsExactlyOnMappingRangeStartIsNotMapped() {
        CategoryRange sourceRange = new CategoryRange( 3, 27 );
        CategoryMappingRange mappingRange = new CategoryMappingRange( 27, 48, DUMMY_DELTA );

        Optional<CategoryRange> destinationRange = mappingRange.mapSourcesToDestinations( sourceRange );

        assertTrue( destinationRange.isEmpty() );
    }

    @Test
    public void sourceRangeThatBeginsBeforeAndEndsExactlyOneBeyondMappingRangeStartMapsLastElementOfSourceRange() {
        CategoryRange sourceRange = new CategoryRange( 31, 77 );
        CategoryMappingRange mappingRange = new CategoryMappingRange( 76, 100, 14 );

        Optional<CategoryRange> destinationRange = mappingRange.mapSourcesToDestinations( sourceRange );

        assertContains( new CategoryRange( 76 + 14, 77 + 14 ), destinationRange );
    }

    @Test
    public void sourceRangeThatBeginsBeforeAndEndsBeyondMappingRangeStartMapsSourceRangeBeyondMappingRangeStart() {
        CategoryRange sourceRange = new CategoryRange( 23, 45 );
        CategoryMappingRange mappingRange = new CategoryMappingRange( 30, 115, 12 );

        Optional<CategoryRange> destinationRange = mappingRange.mapSourcesToDestinations( sourceRange );

        assertContains( new CategoryRange( 30 + 12, 45 + 12 ), destinationRange );
    }

    @Test
    public void sourceRangeThatBeginsBeforeMappingRangeStartAndEndsOnMappingRangeEndMapsSourceRangeStartingFromMappingRangeStart() {
        CategoryRange sourceRange = new CategoryRange( 19, 46 );
        CategoryMappingRange mappingRange = new CategoryMappingRange( 35, 46, 3 );

        Optional<CategoryRange> destinationRange = mappingRange.mapSourcesToDestinations( sourceRange );

        assertContains( new CategoryRange( 35 + 3, 46 + 3 ), destinationRange );
    }

    @Test
    public void sourceRangeThatBeginsBeforeMappingRangeStartAndEndsAfterMappingRangeEndMapsSourceRangeBetweenMappingRangeStartAndMappingRangeEnd() {
        CategoryRange sourceRange = new CategoryRange( 32, 78 );
        CategoryMappingRange mappingRange = new CategoryMappingRange( 40, 62, 5 );

        Optional<CategoryRange> destinationRange = mappingRange.mapSourcesToDestinations( sourceRange );

        assertContains( new CategoryRange( 40 + 5, 62 + 5 ), destinationRange );
    }

    @Test
    public void sourceRangeThatBeginsAfterMappingRangeStartAndEndsBeforeMappingRangeEndsMapsEntireSourceRange() {
        CategoryRange sourceRange = new CategoryRange( 33, 79 );
        CategoryMappingRange mappingRange = new CategoryMappingRange( 20, 98, 12 );

        Optional<CategoryRange> destinationRange = mappingRange.mapSourcesToDestinations( sourceRange );

        assertContains( new CategoryRange( 33 + 12, 79 + 12 ), destinationRange );
    }

    @Test
    public void sourceRangeThatBeginsAfterMappingRangeEndIsNotMapped() {
        CategoryRange sourceRange = new CategoryRange( 52, 86 );
        CategoryMappingRange mappingRange = new CategoryMappingRange( 12, 38, 25 );

        Optional<CategoryRange> destinationRange = mappingRange.mapSourcesToDestinations( sourceRange );

        assertTrue( destinationRange.isEmpty() );
    }

    private <T> void assertContains( T expected, Optional<T> actual ) {
        assertEquals( expected, actual.get() );
    }
}
