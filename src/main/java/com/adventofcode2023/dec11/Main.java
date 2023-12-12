package com.adventofcode2023.dec11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main( String[] args ) throws IOException {
        Universe universe = parseUniverse();
        universe.expand( 2 );
        long shortestPathSum = universe.galaxyShortestPathSum();
        System.out.println( "The sum of shortest paths with expansion factor 2 is " + shortestPathSum );

        universe = parseUniverse();
        universe.expand( 1000000 );
        shortestPathSum = universe.galaxyShortestPathSum();
        System.out.println( "The sum of shortest paths with expansion factor 1000000 is " + shortestPathSum );
    }

    private static Universe parseUniverse() throws IOException {
        UniverseBuilder builder = new UniverseBuilder();
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec11/input.txt" ) ) ) {
            reader
                .lines()
                .forEach( builder::parseLine );
        }

        return builder.build();
    }
}
