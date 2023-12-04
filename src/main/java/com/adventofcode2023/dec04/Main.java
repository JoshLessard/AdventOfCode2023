package com.adventofcode2023.dec04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main( String[] args ) throws IOException {
        List<Card> cards = parseCards();
        int pointSum = cards
            .stream()
            .mapToInt( Card::winningCardNumberCount )
            .map( Main::toPointValue )
            .sum();
        System.out.println( "The sum of point values is " + pointSum );

        CardGame game = new CardGame( cards );
        game.play();
        long cardCount = game.cardCount();
        System.out.println( "The total number of cards is " + cardCount );
    }

    private static int toPointValue( int winningCardNumberCount ) {
        return winningCardNumberCount == 0
            ? 0
            : (int) Math.pow( 2, winningCardNumberCount - 1 );
    }

    private static List<Card> parseCards() throws IOException {
        CardParser parser = new CardParser();
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec04/input.txt" ) ) ) {
            return reader
                .lines()
                .map( parser::parseCard )
                .toList();
        }
    }
}
