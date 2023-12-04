package com.adventofcode2023.dec04;

import java.util.Set;

class Card {

    private final int identifier;
    private final Set<Integer> winningNumbers;
    private final Set<Integer> cardNumbers;

    Card( int identifier, Set<Integer> winningNumbers, Set<Integer> cardNumbers ) {
        this.identifier = identifier;
        this.winningNumbers = Set.copyOf( winningNumbers );
        this.cardNumbers = Set.copyOf( cardNumbers );
    }

    int identifier() {
        return identifier;
    }

    int winningCardNumberCount() {
        return (int) cardNumbers
            .stream()
            .filter( winningNumbers::contains )
            .count();
    }
}
