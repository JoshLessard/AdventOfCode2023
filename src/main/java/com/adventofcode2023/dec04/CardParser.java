package com.adventofcode2023.dec04;

import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CardParser {

    private static final Pattern CARD_PATTERN = Pattern.compile( "^Card\\s+(\\d+):\\s+([\\d ]+)\\|([\\d ]+)$" );

    Card parseCard( String inputLine ) {
        Matcher matcher = CARD_PATTERN.matcher( inputLine );
        if ( matcher.matches() ) {
            int cardNumber = Integer.parseInt( matcher.group( 1 ) );
            Set<Integer> winningNumbers = parseNumbers( matcher.group( 2 ) );
            Set<Integer> playerNumbers = parseNumbers( matcher.group( 3 ) );
            return new Card( cardNumber, winningNumbers, playerNumbers );
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Set<Integer> parseNumbers( String input ) {
        return Arrays.stream( input.trim().split( "\\s+" ) )
            .map( Integer::valueOf )
            .collect( toSet() );
    }
}
