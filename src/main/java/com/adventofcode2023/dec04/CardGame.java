package com.adventofcode2023.dec04;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;

class CardGame {

    private final List<Card> cards;
    private final Map<Integer, Count> countsByCardNumber;

    CardGame( List<Card> cards ) {
        this.cards = List.copyOf( cards );
        this.countsByCardNumber = initializeCounts( cards );
    }

    private Map<Integer, Count> initializeCounts( List<Card> cards ) {
        return cards
            .stream()
            .collect( toMap( Card::identifier, card -> new Count( 1 ) ) );
    }

    void play() {
        for ( Card card : cards ) {
            int startInclusive = card.identifier() + 1;
            int endExclusive = Math.min( startInclusive + card.winningCardNumberCount(), maxCardIdentifier() + 1 );
            int copiesOfCurrentCard = countsByCardNumber.get( card.identifier() ).count();
            for ( int cardIdentifierToIncrement = startInclusive; cardIdentifierToIncrement < endExclusive; ++cardIdentifierToIncrement ) {
                countsByCardNumber
                    .get( cardIdentifierToIncrement )
                    .increment( copiesOfCurrentCard );
            }
        }
    }

    private int maxCardIdentifier() {
        return countsByCardNumber.keySet()
            .stream()
            .mapToInt( Integer::intValue )
            .max()
            .orElseThrow();
    }

    int cardCount() {
        return countsByCardNumber.values()
            .stream()
            .mapToInt( Count::count )
            .sum();
    }

    private static final class Count {

        private int count;

        private Count( int initialCount ) {
            this.count = initialCount;
        }

        private void increment( int delta ) {
            this.count += delta;
        }

        int count() {
            return count;
        }
    }
}
