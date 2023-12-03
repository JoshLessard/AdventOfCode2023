package com.adventofcode2023.dec02;

import java.util.List;

class Game {

    private final List<CubeSelection> cubeSelections;

    Game( List<CubeSelection> cubeSelections ) {
        this.cubeSelections = List.copyOf( cubeSelections );
    }

    boolean isPossibleWith( Bag bag ) {
        return cubeSelections
            .stream()
            .allMatch( bag::containsEnoughCubesToSatisfy );
    }

    CubeSelection fewestCubesRequiredToBePossible() {
        int redCubeCount = 0;
        int greenCubeCount = 0;
        int blueCubeCount = 0;
        for ( CubeSelection cubeSelection : cubeSelections ) {
            redCubeCount = Math.max( redCubeCount, cubeSelection.redCubeCount() );
            greenCubeCount = Math.max( greenCubeCount, cubeSelection.greenCubeCount() );
            blueCubeCount = Math.max( blueCubeCount, cubeSelection.blueCubeCount() );
        }

        return new CubeSelection( redCubeCount, greenCubeCount, blueCubeCount );
    }
}
