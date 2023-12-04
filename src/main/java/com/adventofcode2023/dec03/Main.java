package com.adventofcode2023.dec03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main( String[] args ) throws IOException {
        EngineSchematic engineSchematic = parseEngineSchematic();
        int partNumberSum = engineSchematic.partNumbers()
            .stream()
            .mapToInt( Integer::intValue )
            .sum();
        System.out.println( "The sum of part numbers is " + partNumberSum );

        long gearRatioSum = engineSchematic
            .gearRatios()
            .stream()
            .mapToLong( Integer::longValue )
            .sum();
        System.out.println( "The sum of gear ratios is " + gearRatioSum );
    }

    private static EngineSchematic parseEngineSchematic() throws IOException {
        EngineSchematicBuilder schematicBuilder = new EngineSchematicBuilder();
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec03/input.txt" ) ) ) {
            reader
                .lines()
                .forEach( schematicBuilder::addRow );
        }
        return schematicBuilder.build();
    }
}
