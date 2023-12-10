package com.adventofcode2023.dec08;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DesertMapBuilder {

    private static final Pattern NODE_PATTERN = Pattern.compile( "([A-Z]{3}) = \\(([A-Z]{3}), ([A-Z]{3})\\)" );

    private final Map<Node, Map<Instruction, Node>> destinationsByNode = new HashMap<>();

    void parseNode( String input ) {
        Matcher matcher = NODE_PATTERN.matcher( input );
        if ( matcher.matches() ) {
            Node source = new Node( matcher.group( 1 ) );
            Node leftDestination = new Node( matcher.group( 2 ) );
            Node rightDestination = new Node( matcher.group( 3 ) );

            Map<Instruction, Node> destinations = destinationsByNode.computeIfAbsent( source, s -> new HashMap<>() );
            destinations.put( Instruction.LEFT, leftDestination );
            destinations.put( Instruction.RIGHT, rightDestination );
        } else {
            throw new IllegalArgumentException();
        }
    }

    DesertMap build() {
        return new DesertMap( destinationsByNode );
    }
}
