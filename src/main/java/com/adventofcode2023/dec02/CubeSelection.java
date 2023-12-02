package com.adventofcode2023.dec02;

record CubeSelection( int redCubeCount, int greenCubeCount, int blueCubeCount ) {

    int power() {
        return redCubeCount * greenCubeCount * blueCubeCount;
    }
}
