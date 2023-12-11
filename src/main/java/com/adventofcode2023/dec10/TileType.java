package com.adventofcode2023.dec10;

enum TileType {
    VERTICAL( false ) {
        @Override
        Turn turnWhenEnteringFrom( Direction incomingDirection ) {
            throw new IllegalStateException();
        }
    },
    HORIZONTAL( false ) {
        @Override
        Turn turnWhenEnteringFrom( Direction incomingDirection ) {
            throw new IllegalStateException();
        }
    },
    NORTH_AND_EAST( true ) {
        @Override
        Turn turnWhenEnteringFrom( Direction incomingDirection ) {
            return switch ( incomingDirection ) {
                case SOUTH -> Turn.LEFT;
                case WEST -> Turn.RIGHT;
                default -> throw new IllegalArgumentException();
            };
        }
    },
    NORTH_AND_WEST( true ) {
        @Override
        Turn turnWhenEnteringFrom( Direction incomingDirection ) {
            return switch( incomingDirection ) {
                case SOUTH -> Turn.RIGHT;
                case EAST -> Turn.LEFT;
                default -> throw new IllegalArgumentException();
            };
        }
    },
    SOUTH_AND_WEST( true ) {
        @Override
        Turn turnWhenEnteringFrom( Direction incomingDirection ) {
            return switch( incomingDirection ) {
                case NORTH -> Turn.LEFT;
                case EAST -> Turn.RIGHT;
                default -> throw new IllegalArgumentException();
            };
        }
    },
    SOUTH_AND_EAST( true ) {
        @Override
        Turn turnWhenEnteringFrom( Direction incomingDirection ) {
            return switch( incomingDirection ) {
                case NORTH -> Turn.RIGHT;
                case WEST -> Turn.LEFT;
                default -> throw new IllegalArgumentException();
            };
        }
    },
    GROUND( false ) {
        @Override
        Turn turnWhenEnteringFrom( Direction incomingDirection ) {
            throw new IllegalArgumentException();
        }
    };

    private final boolean isCorner;

    TileType( boolean isCorner ) {
        this.isCorner = isCorner;
    }

    boolean isCorner() {
        return isCorner;
    }

    abstract Turn turnWhenEnteringFrom( Direction incomingDirection );
}
