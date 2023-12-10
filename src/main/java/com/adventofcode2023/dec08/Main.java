package com.adventofcode2023.dec08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main( String[] args ) throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec08/input.txt" ) ) ) {
            List<Instruction> instructions = parseInstructions( reader.readLine() );
            reader.readLine(); // skip blank line
            DesertMap map = parseMap( reader );

            map.newPathFrom( new Node( "AAA" ) );
            while ( ! map.currentLocation().labelIs( "ZZZ" ) ) {
                instructions
                    .forEach( map::apply );
            }
            System.out.println( "Number of steps to reach ZZZ for Part 1: " + map.numberOfSteps() );

            System.out.println( "\nNumber of steps for Part 2 is lowest common multiple of: " );
            map
                .nodes()
                .filter( node -> node.labelEndsWith( 'A' ) )
                .mapToLong( startingNode -> numberOfStepsToEndingNode( map, startingNode, instructions ) )
                .forEach( numberOfSteps -> System.out.println( "\t" + numberOfSteps ) );
        }
    }

    private static List<Instruction> parseInstructions( String input ) {
        return input
            .chars()
            .mapToObj( Main::parseInstruction )
            .toList();
    }

    private static Instruction parseInstruction( int character ) {
        return switch ( character ) {
            case 'L' -> Instruction.LEFT;
            case 'R' -> Instruction.RIGHT;
            default -> throw new IllegalArgumentException();
        };
    }

    private static DesertMap parseMap( BufferedReader reader ) {
        DesertMapBuilder mapBuilder = new DesertMapBuilder();
        reader
            .lines()
            .forEach( mapBuilder::parseNode );
        return mapBuilder.build();
    }

    private static long numberOfStepsToEndingNode( DesertMap map, Node startingNode, List<Instruction> instructions ) {
        map.newPathFrom( startingNode );
        while ( ! map.currentLocation().labelEndsWith( 'Z' ) ) {
            instructions
                .forEach( map::apply );
        }
        return map.numberOfSteps();
    }
}
