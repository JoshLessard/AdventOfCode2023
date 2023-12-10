package com.adventofcode2023.dec08;

import static java.util.stream.Collectors.toUnmodifiableMap;

import java.util.Map;
import java.util.stream.Stream;

class DesertMap {

    private Node currentNode;
    private final Map<Node, Map<Instruction, Node>> destinationsByNode;

    private long numberOfSteps = 0L;

    DesertMap( Map<Node, Map<Instruction, Node>> destinationsByNode ) {
        this.destinationsByNode = deepCopy( destinationsByNode );
    }

    private static Map<Node, Map<Instruction, Node>> deepCopy( Map<Node, Map<Instruction, Node>> destinationsByNode ) {
        return destinationsByNode
            .entrySet()
            .stream()
            .collect( toUnmodifiableMap( Map.Entry::getKey, entry -> Map.copyOf( entry.getValue() ) ) );
    }

    Node currentLocation() {
        return currentNode;
    }

    void apply( Instruction instruction ) {
        currentNode = destinationsByNode
            .get( currentNode )
            .get( instruction );
        ++numberOfSteps;
    }

    long numberOfSteps() {
        return numberOfSteps;
    }

    Stream<Node> nodes() {
        return destinationsByNode
            .keySet()
            .stream();
    }

    void newPathFrom( Node startingNode ) {
        currentNode = startingNode;
        numberOfSteps = 0L;
    }
}
