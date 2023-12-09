package com.adventofcode2023.dec07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JokerHandTest {

    @Test
    public void handWithFiveJokersIsFiveOfAKind() {
        Hand hand = new Hand( Rank.JOKER, Rank.JOKER, Rank.JOKER, Rank.JOKER, Rank.JOKER );

        assertEquals( HandType.FIVE_OF_A_KIND, hand.type() );
    }

    @Test
    public void handWithFourOfAKindAndOneJokerIsFiveOfAKind() {
        Hand hand = new Hand( Rank.TWO, Rank.JOKER, Rank.TWO, Rank.TWO, Rank.TWO );

        assertEquals( HandType.FIVE_OF_A_KIND, hand.type() );
    }

    @Test
    public void handWithFourJokersIsFiveOfAKind() {
        Hand hand = new Hand( Rank.JOKER, Rank.JOKER, Rank.EIGHT, Rank.JOKER, Rank.JOKER );

        assertEquals( HandType.FIVE_OF_A_KIND, hand.type() );
    }

    @Test
    public void fullHouseWithThreeJokersIsFiveOfAKind() {
        Hand hand = new Hand( Rank.FOUR, Rank.JOKER, Rank.FOUR, Rank.JOKER, Rank.JOKER );

        assertEquals( HandType.FIVE_OF_A_KIND, hand.type() );
    }

    @Test
    public void fullHouseWithTwoJokersIsFiveOfAKind() {
        Hand hand = new Hand( Rank.JOKER, Rank.QUEEN, Rank.QUEEN, Rank.JOKER, Rank.QUEEN );

        assertEquals( HandType.FIVE_OF_A_KIND, hand.type() );
    }

    @Test
    public void handWithThreeJokersIsFourOfAKind() {
        Hand hand = new Hand( Rank.JOKER, Rank.ACE, Rank.KING, Rank.JOKER, Rank.JOKER );

        assertEquals( HandType.FOUR_OF_A_KIND, hand.type() );
    }

    @Test
    public void threeOfAKindWithOneJokerIsFourOfAKind() {
        Hand hand = new Hand( Rank.JOKER, Rank.TEN, Rank.TEN, Rank.TEN, Rank.SEVEN );

        assertEquals( HandType.FOUR_OF_A_KIND, hand.type() );
    }

    @Test
    public void twoPairWithTwoJokersIsFourOfAKind() {
        Hand hand = new Hand( Rank.SIX, Rank.JOKER, Rank.JOKER, Rank.SIX, Rank.THREE );

        assertEquals( HandType.FOUR_OF_A_KIND, hand.type() );
    }

    @Test
    public void twoPairWithOneJokerIsFullHouse() {
        Hand hand = new Hand( Rank.FIVE, Rank.JOKER, Rank.FOUR, Rank.FIVE, Rank.FOUR );

        assertEquals( HandType.FULL_HOUSE, hand.type() );
    }

    @Test
    public void pairOfJokersIsThreeOfAKind() {
        Hand hand = new Hand( Rank.TWO, Rank.FIVE, Rank.JOKER, Rank.QUEEN, Rank.JOKER );

        assertEquals( HandType.THREE_OF_A_KIND, hand.type() );
    }

    @Test
    public void pairWithOneJokerIsThreeOfAKind() {
        Hand hand = new Hand( Rank.JOKER, Rank.EIGHT, Rank.EIGHT, Rank.KING, Rank.QUEEN );

        assertEquals( HandType.THREE_OF_A_KIND, hand.type() );
    }

    @Test
    public void highCardWithOneJokerIsOnePair() {
        Hand hand = new Hand( Rank.FOUR, Rank.ACE, Rank.TWO, Rank.JOKER, Rank.SEVEN );

        assertEquals( HandType.ONE_PAIR, hand.type() );
    }
}
