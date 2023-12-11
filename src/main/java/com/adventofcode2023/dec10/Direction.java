package com.adventofcode2023.dec10;

enum Direction {
    NORTH {
        @Override
        Direction oppositeDirection() {
            return SOUTH;
        }

        @Override
        Point applyTo( Point point ) {
            return point.north();
        }
    },
    SOUTH {
        @Override
        Direction oppositeDirection() {
            return NORTH;
        }

        @Override
        Point applyTo( Point point ) {
            return point.south();
        }
    },
    EAST {
        @Override
        Direction oppositeDirection() {
            return WEST;
        }

        @Override
        Point applyTo( Point point ) {
            return point.east();
        }
    },
    WEST {
        @Override
        Direction oppositeDirection() {
            return EAST;
        }

        @Override
        Point applyTo( Point point ) {
            return point.west();
        }
    };

    abstract Direction oppositeDirection();
    abstract Point applyTo( Point point );
}
