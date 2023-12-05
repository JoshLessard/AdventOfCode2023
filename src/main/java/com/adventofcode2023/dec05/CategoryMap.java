package com.adventofcode2023.dec05;

import java.util.List;

class CategoryMap {

    private final List<CategoryRange> categoryRanges;

    CategoryMap( List<CategoryRange> categoryRanges ) {
        this.categoryRanges = List.copyOf( categoryRanges );
    }

    long mapSourceToDestination( long source ) {
        return categoryRanges
            .stream()
            .filter( range -> range.isInSourceRange( source ) )
            .findFirst()
            .map( range -> range.mapSourceToDestination( source ) )
            .orElse( source );
    }
}
