package com.adventofcode2023.dec05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class CategoryRangeTest {

    @Test
    public void rangeSuffixStartingFromBeforeCategoryRangeReturnsEntireRange() {
        CategoryRange range = new CategoryRange( 20, 46 );

        Optional<CategoryRange> rangeSuffix = range.rangeSuffix( 10 );

        assertContains( new CategoryRange( 20, 46 ), rangeSuffix );
    }

    @Test
    public void rangeSuffixStartingFromCategoryRangeStartReturnsEntireRange() {
        CategoryRange range = new CategoryRange( 33, 82 );

        Optional<CategoryRange> rangeSuffix = range.rangeSuffix( 33 );

        assertContains( new CategoryRange( 33, 82 ), rangeSuffix );
    }

    @Test
    public void rangeSuffixStartingBetweenCategoryRangeStartAndEndReturnsCategorySuffixStartingWithRangeSuffixStart() {
        CategoryRange range = new CategoryRange( 48, 72 );

        Optional<CategoryRange> rangeSuffix = range.rangeSuffix( 59 );

        assertContains( new CategoryRange( 59, 72 ), rangeSuffix );
    }

    @Test
    public void rangeSuffixStartingOneBeforeCategoryRangeEndReturnsOnlyLastElementInRange() {
        CategoryRange range = new CategoryRange( 25, 49 );

        Optional<CategoryRange> rangeSuffix = range.rangeSuffix( 48 );

        assertContains( new CategoryRange( 48, 49 ), rangeSuffix );
    }

    @Test
    public void rangeSuffixStartingAtCategoryRangeEndIsEmpty() {
        CategoryRange range = new CategoryRange( 61, 82 );

        Optional<CategoryRange> rangeSuffix = range.rangeSuffix( 82 );

        assertTrue( rangeSuffix.isEmpty() );
    }

    @Test
    public void rangeSuffixStartingAfterCategoryRangeEndIsEmpty() {
        CategoryRange range = new CategoryRange( 58, 92 );

        Optional<CategoryRange> rangeSuffix = range.rangeSuffix( 97 );

        assertTrue( rangeSuffix.isEmpty() );
    }

    private <T> void assertContains( T expected, Optional<T> actual ) {
        assertEquals( expected, actual.get() );
    }
}
