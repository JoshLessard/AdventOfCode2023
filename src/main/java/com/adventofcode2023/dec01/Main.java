package com.adventofcode2023.dec01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Main {

    private static final Map<String, Integer> DIGITS_BY_NAME = Map.of(
        "one", 1,
        "two", 2,
        "three", 3,
        "four", 4,
        "five", 5,
        "six", 6,
        "seven", 7,
        "eight", 8,
        "nine", 9
    );

    public static void main( String[] args ) throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec01/input.txt" ) ) ) {
            int calibrationValueSumPartA = reader
                .lines()
                .mapToInt( Main::toCalibrationValuePartA )
                .sum();
            System.out.println( "The sum of all calibration values for Part A is " + calibrationValueSumPartA );
        }

        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec01/input.txt" ) ) ) {
            int calibrationValueSumPartB = reader
                .lines()
                .mapToInt( Main::toCalibrationValuePartB )
                .sum();
            System.out.println( "The sum of all calibration values for Part B is " + calibrationValueSumPartB );
        }
    }

    private static int toCalibrationValuePartA( String inputLine ) {
        int firstDigit = Integer.MIN_VALUE;
        int lastDigit = Integer.MIN_VALUE;
        for ( int i = 0; i < inputLine.length(); ++i ) {
            char character = inputLine.charAt( i );
            if ( Character.isDigit( character ) ) {
                int digit = character - '0';
                if ( firstDigit == Integer.MIN_VALUE ) {
                    firstDigit = digit;
                }
                lastDigit = digit;
            }
        }

        return firstDigit * 10 + lastDigit;
    }

    private static int toCalibrationValuePartB( String inputLine ) {
        int firstDigit = Integer.MIN_VALUE;
        int lastDigit = Integer.MIN_VALUE;
        String scratchLine = inputLine;
        while ( ! scratchLine.isEmpty() ) {
            Integer digit = extractDigit( scratchLine );
            if ( digit != null ) {
                if ( firstDigit == Integer.MIN_VALUE ) {
                    firstDigit = digit;
                }
                lastDigit = digit;
            }
            scratchLine = scratchLine.substring( 1 );
        }

        return firstDigit * 10 + lastDigit;
    }

    private static Integer extractDigit( String inputLine ) {
        char character = inputLine.charAt( 0 );
        if ( Character.isDigit( character ) ) {
            return character - '0';
        }

        for ( String digitName : DIGITS_BY_NAME.keySet() ) {
            if ( inputLine.startsWith( digitName ) ) {
                return DIGITS_BY_NAME.get( digitName );
            }
        }

        return null;
    }
}
