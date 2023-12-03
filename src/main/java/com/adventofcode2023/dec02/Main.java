package com.adventofcode2023.dec02;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern GAME_SELECTION_REGEX = Pattern.compile( "(\\d+) (.+)" );

    public static void main( String[] args ) throws IOException {
        Bag bag = new Bag( 12, 13, 14 );
        List<Game> games = parseGames();
        int sumOfPossibleGames = 0;
        for ( int i = 0; i < games.size(); ++i ) {
            int gameNumber = i + 1;
            Game game = games.get( i );
            if ( game.isPossibleWith( bag ) ) {
                sumOfPossibleGames += gameNumber;
            }
        }

        System.out.println( "Sum of possible games: " + sumOfPossibleGames );

        int sumOfPowers = games
            .stream()
            .map( Game::fewestCubesRequiredToBePossible)
            .mapToInt( CubeSelection::power )
            .sum();

        System.out.println( "Sum of powers: " + sumOfPowers );
    }

    private static List<Game> parseGames() throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec02/input.txt" ) ) ) {
            return reader
                .lines()
                .map( Main::toGame )
                .collect( toList() );
        }
    }

    private static Game toGame( String inputString ) {
        String gameSelections = inputString.split( ":" )[1].trim();
        List<CubeSelection> cubeSelections = Arrays.stream( gameSelections.split( ";" ) )
            .map( String::trim )
            .map( Main::toCubeSelection )
            .toList();
        return new Game( cubeSelections );
    }

    private static CubeSelection toCubeSelection( String inputString ) {
        int redCubeCount = 0;
        int greenCubeCount = 0;
        int blueCubeCount = 0;
        for ( String selection : inputString.split( "," ) ) {
            Matcher matcher = GAME_SELECTION_REGEX.matcher( selection.trim() );
            if ( matcher.matches() ) {
                int cubeCount = Integer.parseInt( matcher.group( 1 ) );
                Colour colour = Colour.valueOf( matcher.group( 2 ).toUpperCase() );
                switch ( colour ) {
                    case RED -> redCubeCount = cubeCount;
                    case GREEN -> greenCubeCount = cubeCount;
                    case BLUE -> blueCubeCount = cubeCount;
                }
            }
        }

        return new CubeSelection( redCubeCount, greenCubeCount, blueCubeCount );
    }
}
