package com.adventofcode2023.dec10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// TODO Lots of refactoring opportunities in today's solution
public class Main {

    public static void main( String[] args ) throws IOException  {
        PipeField pipeField = parsePipeField();
        int numberOfSteps = pipeField.largestNumberOfStepsFromStartingPoint();
        System.out.println( "Number of steps to farthest point: " + numberOfSteps );

        int numberOfEnclosedTiles = pipeField.numberOfEnclosedTiles();
        System.out.println( "Number of tiles enclosed in the main loop: " + numberOfEnclosedTiles );
    }

    private static PipeField parsePipeField() throws IOException {
        PipeFieldBuilder builder = new PipeFieldBuilder();
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec10/input.txt" ) ) ) {
            reader
                .lines()
                .forEach( builder::parseRow );
        }
        return builder.build();
    }
}
