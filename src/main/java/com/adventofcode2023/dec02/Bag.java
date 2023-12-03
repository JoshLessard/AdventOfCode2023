package com.adventofcode2023.dec02;

record Bag( int redCubeCount, int greenCubeCount, int blueCubeCount ) {

    boolean containsEnoughCubesToSatisfy( CubeSelection cubeSelection ) {
        return redCubeCount >= cubeSelection.redCubeCount()
            && greenCubeCount >= cubeSelection.greenCubeCount()
            && blueCubeCount >= cubeSelection.blueCubeCount();
    }
}
