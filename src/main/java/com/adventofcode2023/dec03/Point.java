package com.adventofcode2023.dec03;

record Point( int x, int y ) {

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
