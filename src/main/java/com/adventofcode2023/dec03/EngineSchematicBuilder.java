package com.adventofcode2023.dec03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

class EngineSchematicBuilder {

    private int width = 0;
    private int height = 0;
    private final Map<Point, Character> symbolsByLocation = new HashMap<>();
    private final List<ValueLocation> valueLocations = new ArrayList<>();

    void addRow( String inputString ) {
        width = Math.max( width, inputString.length() );
        int index = 0;
        while ( index < inputString.length() ) {
            char currentCharacter = inputString.charAt( index );
            if ( currentCharacter == '.' ) {
                ++index;
            } else if ( Character.isDigit( currentCharacter ) ) {
                Point leftmostPoint = new Point( index, height );
                int value = currentCharacter - '0';
                ++index;
                currentCharacter = inputString.charAt( index );
                while ( Character.isDigit( currentCharacter ) ) {
                    value = value * 10 + (currentCharacter - '0');
                    ++index;
                    if ( index >= inputString.length() ) {
                        break;
                    }
                    currentCharacter = inputString.charAt( index );
                }
                Point rightmostPoint = new Point( index - 1, height );
                valueLocations.add( new ValueLocation( value, leftmostPoint, rightmostPoint ) );
            } else {
                symbolsByLocation.put( new Point( index, height ), currentCharacter );
                ++index;
            }
        }
        ++height;
    }

    EngineSchematic build() {
        return new EngineSchematic( width, height, symbolsByLocation, valueLocations );
    }
}
