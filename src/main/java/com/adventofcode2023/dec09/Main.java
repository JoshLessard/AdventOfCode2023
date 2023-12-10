package com.adventofcode2023.dec09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main( String[] args ) throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec09/input.txt" ) ) ) {
            List<Sequence> sequences = reader
                .lines()
                .map( Main::toSequence )
                .toList();

            long nextValueSum = sequences
                .stream()
                .mapToLong( Sequence::nextValue )
                .sum();
            System.out.println( "Sum of next values: " + nextValueSum );

            long previousValueSum = sequences
                .stream()
                .mapToLong( Sequence::previousValue )
                .sum();
            System.out.println( "Sum of previous values: " + previousValueSum );
        }
    }

    private static Sequence toSequence( String input ) {
        List<Long> originalValues = Arrays.stream( input.trim().split( "\\s+" ) )
            .map( Long::valueOf )
            .toList();
        return new Sequence( originalValues );
    }
}
