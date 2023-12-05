package com.adventofcode2023.dec05;

import static java.util.stream.Collectors.toSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern SEEDS_PATTERN = Pattern.compile( "seeds:\\s+([^\n]+)" );
    private static final Pattern MAP_RANGE_PATTERN = Pattern.compile( "^(\\d+)\\s+(\\d+)\\s+(\\d+)$" );

    public static void main( String[] args ) throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec05/input.txt" ) ) ) {
            Set<Long> seeds = parseSeeds( reader );
            GardenMap gardenMap = parseGardenMap( reader );
            long closestLocation = seeds.stream()
                .mapToLong( gardenMap::mapSeedToLocation )
                .min()
                .orElseThrow();
            System.out.println( "The closest location is " + closestLocation );
        }
    }

    private static GardenMap parseGardenMap( BufferedReader reader ) throws IOException {
        return new GardenMap(
            parseCategoryMap( reader, "seed", "soil" ),
            parseCategoryMap( reader, "soil", "fertilizer" ),
            parseCategoryMap( reader, "fertilizer", "water" ),
            parseCategoryMap( reader, "water", "light" ),
            parseCategoryMap( reader, "light", "temperature" ),
            parseCategoryMap( reader, "temperature", "humidity" ),
            parseCategoryMap( reader, "humidity", "location" )
        );
    }

    private static Set<Long> parseSeeds( BufferedReader reader ) throws IOException {
        Matcher matcher = SEEDS_PATTERN.matcher( reader.readLine() );
        if ( matcher.matches() ) {
            Set<Long> seeds = Arrays.stream( matcher.group( 1 ).trim().split( "\\s+" ) )
                .map( Long::valueOf )
                .collect( toSet() );
            reader.readLine(); // skip blank line
            return seeds;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static CategoryMap parseCategoryMap( BufferedReader reader, String sourceCategory, String destinationCategory ) throws IOException {
        ensureExpectedMapTitle( reader, sourceCategory, destinationCategory );
        List<CategoryRange> categoryRanges = new ArrayList<>();
        String line;
        while ( ( line = reader.readLine() ) != null && ! line.isBlank() ) {
            Matcher matcher = MAP_RANGE_PATTERN.matcher( line );
            if ( matcher.matches() ) {
                long destinationRangeStart = Long.parseLong( matcher.group( 1 ) );
                long sourceRangeStart = Long.parseLong( matcher.group( 2 ) );
                long rangeSize = Long.parseLong( matcher.group( 3 ) );
                categoryRanges.add( new CategoryRange( destinationRangeStart, sourceRangeStart, rangeSize ) );
            } else {
                throw new IllegalArgumentException();
            }
        }
        return new CategoryMap( categoryRanges );
    }

    private static void ensureExpectedMapTitle( BufferedReader reader, String sourceCategory, String destinationCategory ) throws IOException {
        Pattern pattern = Pattern.compile( sourceCategory + "-to-" + destinationCategory + " map:" );
        Matcher matcher = pattern.matcher( reader.readLine() );
        if ( ! matcher.matches() ) {
            throw new IllegalArgumentException();
        }
    }
}
