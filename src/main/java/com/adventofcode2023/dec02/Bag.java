package com.adventofcode2023.dec02;

class Bag {

    private final int redCubeCount;
    private final int greenCubeCount;
    private final int blueCubeCount;

    Bag( int redCubeCount, int greenCubeCount, int blueCubeCount ) {
        this.redCubeCount = redCubeCount;
        this.greenCubeCount = greenCubeCount;
        this.blueCubeCount = blueCubeCount;
    }

    boolean containsEnoughCubesToSatisfy( Game game ) {
        return game
            .cubeSelections()
            .allMatch( this::containsEnoughCubesToSatisfy );
    }

    private boolean containsEnoughCubesToSatisfy( CubeSelection cubeSelection ) {
        return redCubeCount >= cubeSelection.redCubeCount()
            && greenCubeCount >= cubeSelection.greenCubeCount()
            && blueCubeCount >= cubeSelection.blueCubeCount();
    }
}
