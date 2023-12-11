package com.adventofcode2023.dec10;

enum Turn {
    LEFT {
        @Override
        Direction applyTo( Direction direction ) {
            return switch( direction ) {
                case NORTH -> Direction.WEST;
                case SOUTH -> Direction.EAST;
                case EAST -> Direction.NORTH;
                case WEST -> Direction.SOUTH;
            };
        }
    },
    RIGHT {
        @Override
        Direction applyTo( Direction direction ) {
            return switch ( direction ) {
                case NORTH -> Direction.EAST;
                case SOUTH -> Direction.WEST;
                case EAST -> Direction.SOUTH;
                case WEST -> Direction.NORTH;
            };
        }
    };

    abstract Direction applyTo( Direction direction );
}
