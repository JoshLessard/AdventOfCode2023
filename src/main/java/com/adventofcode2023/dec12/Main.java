package com.adventofcode2023.dec12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern CONDITION_RECORD_PATTERN = Pattern.compile( "([.#?]+)\\s+([\\d,]+)" );

    public static void main( String[] args ) throws IOException {
        List<ConditionRecordConfiguration> configurations = parseConditionRecordConfigurations();
        long sumOfPossibleArrangements = configurations
            .stream()
            .mapToLong( Main::numberOfPossibleArrangements )
            .sum();
        System.out.println( "Number of possible arrangements: " + sumOfPossibleArrangements );
    }

    private static List<ConditionRecordConfiguration> parseConditionRecordConfigurations() throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec12/input.txt" ) ) ) {
            return reader
                .lines()
                .map( Main::parseConditionRecordConfiguration )
                .toList();
        }
    }

    private static long numberOfPossibleArrangements( ConditionRecordConfiguration configuration ) {
        ConditionRecord record = configuration.record();
        ContiguousGroupSizes contiguousOperationalGroupSizes = configuration.groupSizes();

        return record.numberOfValidArrangements( contiguousOperationalGroupSizes );
    }

    private static ConditionRecordConfiguration parseConditionRecordConfiguration( String input ) {
        Matcher matcher = CONDITION_RECORD_PATTERN.matcher( input );
        if ( ! matcher.matches() ) {
            throw new IllegalArgumentException( input );
        }
        return new ConditionRecordConfiguration(
            parseConditionRecord( matcher.group( 1 ) ),
            parseContiguousGroupSizes( matcher.group( 2 ) )
        );
    }

    private static ConditionRecord parseConditionRecord( String input ) {
        List<Condition> conditions = input
            .chars()
            .mapToObj( Main::parseCondition )
            .toList();
        return new ConditionRecord( conditions );
    }

    private static ContiguousGroupSizes parseContiguousGroupSizes( String input ) {
        List<Integer> groupSizes = Arrays.stream( input.split( "," ) )
            .map( Integer::valueOf )
            .toList();
        return new ContiguousGroupSizes( groupSizes );
    }

    private static Condition parseCondition( int character ) {
        return switch ( character ) {
            case '.' -> Condition.OPERATIONAL;
            case '#' -> Condition.DAMAGED;
            case '?' -> Condition.UNKNOWN;
            default -> throw new IllegalArgumentException();
        };
    }
}
