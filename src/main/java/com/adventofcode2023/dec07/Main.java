package com.adventofcode2023.dec07;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern PLAY_PATTERN = Pattern.compile( "^([^\s]+)\\s+(\\d+)$" );

    public static void main( String[] args ) throws IOException {
        CamelCardsGame game = new CamelCardsGame( parsePlays() );

        System.out.println( "Total winnings: " + game.winnings() );
    }

    private static List<Play> parsePlays() throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec07/input.txt" ) ) ) {
            return reader
                .lines()
                .map( PLAY_PATTERN::matcher )
                .filter( Matcher::matches )
                .map( Main::parsePlay )
                .collect( toList() );
        }
    }

    private static Play parsePlay( Matcher matcher ) {
        Hand hand = parseHand( matcher.group( 1 ) );
        int bid = Integer.parseInt( matcher.group( 2 ) );
        return new Play( hand, bid );
    }

    private static Hand parseHand( String ranksAsString ) {
        List<Rank> ranks = ranksAsString
            .chars()
            .mapToObj( Main::parseRank )
            .toList();
        return new Hand( ranks );
    }

    private static Rank parseRank( int character ) {
        return switch( character ) {
            case 'A' -> Rank.ACE;
            case 'K' -> Rank.KING;
            case 'Q' -> Rank.QUEEN;
            case 'J' -> Rank.JACK;
            case 'T' -> Rank.TEN;
            case '9' -> Rank.NINE;
            case '8' -> Rank.EIGHT;
            case '7' -> Rank.SEVEN;
            case '6' -> Rank.SIX;
            case '5' -> Rank.FIVE;
            case '4' -> Rank.FOUR;
            case '3' -> Rank.THREE;
            case '2' -> Rank.TWO;
            default -> throw new IllegalArgumentException();
        };
    }
}
