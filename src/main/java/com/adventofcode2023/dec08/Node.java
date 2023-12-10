package com.adventofcode2023.dec08;

import java.util.Objects;

class Node {

    private final String label;

    Node( String label ) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node node)) return false;
        return label.equals(node.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    String label() {
        return label;
    }

    boolean labelIs( String label ) {
        return this.label.equals( label );
    }

    boolean labelEndsWith( char character ) {
        return label.charAt( label.length() - 1 ) == character;
    }
}
