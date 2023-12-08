package com.adventofcode2023.dec05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class CategoryRangeTest {

    @Test
    public void subRangeStartingFromBeforeCategoryRangeReturnsEntireRange() {
        CategoryRange range = new CategoryRange( 20, 46 );

        Optional<CategoryRange> subRange = range.subRange( 10 );

        assertContains( new CategoryRange( 20, 46 ), subRange );
    }

    @Test
    public void subRangeStartingFromCategoryRangeStartReturnsEntireRange() {
        CategoryRange range = new CategoryRange( 33, 82 );

        Optional<CategoryRange> subRange = range.subRange( 33 );

        assertContains( new CategoryRange( 33, 82 ), subRange );
    }

    @Test
    public void subRangeStartingBetweenCategoryRangeStartAndEndReturnsCategorySuffixStartingWithSubRangeStart() {
        CategoryRange range = new CategoryRange( 48, 72 );

        Optional<CategoryRange> subRange = range.subRange( 59 );

        assertContains( new CategoryRange( 59, 72 ), subRange );
    }

    @Test
    public void subRangeStartingOneBeforeCategoryRangeEndReturnsOnlyLastElementInRange() {
        CategoryRange range = new CategoryRange( 25, 49 );

        Optional<CategoryRange> subRange = range.subRange( 48 );

        assertContains( new CategoryRange( 48, 49 ), subRange );
    }

    @Test
    public void subRangeStartingAtCategoryRangeEndIsEmpty() {
        CategoryRange range = new CategoryRange( 61, 82 );

        Optional<CategoryRange> subRange = range.subRange( 82 );

        assertTrue( subRange.isEmpty() );
    }

    @Test
    public void subRangeStartingAfterCategoryRangeEndIsEmpty() {
        CategoryRange range = new CategoryRange( 58, 92 );

        Optional<CategoryRange> subRange = range.subRange( 97 );

        assertTrue( subRange.isEmpty() );
    }

    private <T> void assertContains( T expected, Optional<T> actual ) {
        assertEquals( expected, actual.get() );
    }
}
