package com.adventofcode2023.dec06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern RACE_TIMES_PATTERN = Pattern.compile( "Time:\\s+([^\n]+)" );
    private static final Pattern RACE_RECORDS_PATTERN = Pattern.compile( "Distance:\\s+([^\n]+)" );

    public static void main( String[] args ) throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec06/input.txt" ) ) ) {
            String raceTimesAsString = reader.readLine();
            String raceRecordsAsString = reader.readLine();
            List<Race> races = parseRaces( raceTimesAsString );
            List<Integer> records = parseRecords( raceRecordsAsString );
            List<Integer> numberOfWaysToWin = new ArrayList<>();
            for ( int i = 0; i < races.size(); ++i ) {
                int numberOfWaysToWinForCurrentRace = 0;
                Race race = races.get( i );
                int recordForCurrentRace = records.get( i );
                for ( int timeToHoldButton = 1; timeToHoldButton < race.timeInMilliseconds(); ++timeToHoldButton ) {
                    if ( race.distanceTravelled( timeToHoldButton ) > recordForCurrentRace ) {
                        ++numberOfWaysToWinForCurrentRace;
                    }
                }
                numberOfWaysToWin.add( numberOfWaysToWinForCurrentRace );
            }

            int productOfWaysToWin = numberOfWaysToWin
                .stream()
                .reduce( 1, (a, b) -> a * b, Integer::sum );
            System.out.println( "Product of ways to win: " + productOfWaysToWin );
        }
    }

    private static List<Race> parseRaces( String input ) {
        Matcher matcher = RACE_TIMES_PATTERN.matcher( input );
        if ( matcher.matches() ) {
            return Arrays.stream( matcher.group( 1 ).trim().split( "\\s+" ) )
                .mapToInt( Integer::parseInt )
                .mapToObj( Race::new )
                .toList();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static List<Integer> parseRecords( String input ) {
        Matcher matcher = RACE_RECORDS_PATTERN.matcher( input );
        if ( matcher.matches() ) {
            return Arrays.stream( matcher.group( 1 ).trim().split( "\\s+" ) )
                .map( Integer::valueOf )
                .toList();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
