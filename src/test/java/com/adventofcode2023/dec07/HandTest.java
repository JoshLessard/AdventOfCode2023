package com.adventofcode2023.dec07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    public void handWithFiveOfSameRankIsFiveOfAKind() {
        Hand hand = new Hand( Rank.TEN, Rank.TEN, Rank.TEN, Rank.TEN, Rank.TEN );

        assertEquals( HandType.FIVE_OF_A_KIND, hand.type() );
    }

    @Test
    public void handWithFourOfSameRankIsFourOfAKind() {
        Hand hand = new Hand( Rank.EIGHT, Rank.TWO, Rank.EIGHT, Rank.EIGHT, Rank.EIGHT );

        assertEquals( HandType.FOUR_OF_A_KIND, hand.type() );
    }

    @Test
    public void handWithThreeOfSameRankAndTwoOfSameRankIsFullHouse() {
        Hand hand = new Hand( Rank.TWO, Rank.TWO, Rank.SEVEN, Rank.SEVEN, Rank.TWO );

        assertEquals( HandType.FULL_HOUSE, hand.type() );
    }

    @Test
    public void handWithThreeOfSameRankAndTwoDifferentRanksIsThreeOfAKind() {
        Hand hand = new Hand( Rank.ACE, Rank.THREE, Rank.ACE, Rank.ACE, Rank.NINE );

        assertEquals( HandType.THREE_OF_A_KIND, hand.type() );
    }

    @Test
    public void handWithTwoOfSameRankAndTwoOfDifferentRankIsTwoPair() {
        Hand hand = new Hand( Rank.TEN, Rank.QUEEN, Rank.QUEEN, Rank.TWO, Rank.TEN );

        assertEquals( HandType.TWO_PAIR, hand.type() );
    }

    @Test
    public void handWithTwoOfSameRankAndThreeDifferentRanksIsOnePair() {
        Hand hand = new Hand( Rank.JACK, Rank.FIVE, Rank.NINE, Rank.JACK, Rank.QUEEN );

        assertEquals( HandType.ONE_PAIR, hand.type() );
    }

    @Test
    public void handWithFiveDistinctRanksIsHighCard() {
        Hand hand = new Hand( Rank.SIX, Rank.ONE, Rank.EIGHT, Rank.ACE, Rank.JACK );

        assertEquals( HandType.HIGH_CARD, hand.type() );
    }
}
