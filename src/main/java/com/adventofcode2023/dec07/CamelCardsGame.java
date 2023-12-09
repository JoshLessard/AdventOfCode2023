package com.adventofcode2023.dec07;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.List;

class CamelCardsGame {

    private final List<Play> plays;

    CamelCardsGame( List<Play> plays ) {
        this.plays = new ArrayList<>( plays );
        this.plays.sort( comparing( Play::hand ) );
    }

    long winnings() {
        long winnings = 0L;
        for ( int i = 0; i < plays.size(); ++i ) {
            Play play = plays.get( i );
            int rank = plays.size() - i;
            winnings += (long) play.bid() * rank;
        }

        return winnings;
    }
}
