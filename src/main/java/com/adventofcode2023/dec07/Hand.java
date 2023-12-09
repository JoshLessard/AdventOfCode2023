package com.adventofcode2023.dec07;

import static java.util.Comparator.comparing;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class Hand implements Comparable<Hand> {

    private final List<Rank> ranks;
    private final HandType type;

    Hand( Rank... ranks ) {
        this( Arrays.asList( ranks ) );
    }

    Hand( List<Rank> ranks ) {
        if ( ranks.size() != 5 ) {
            throw new IllegalArgumentException();
        }
        this.ranks = List.copyOf( ranks );
        this.type = determineType();
    }

    private HandType determineType() {
        List<Long> uniqueRankCountsDescending = uniqueRankCountsDescending();
        int jokerCount = countJokers();
        if ( uniqueRankCountsDescending.size() == 1 ) {
            return HandType.FIVE_OF_A_KIND;
        } else if ( uniqueRankCountsDescending.size() == 2 && uniqueRankCountsDescending.get( 0 ) == 4 ) {
            return jokerCount == 4 || jokerCount == 1
                ? HandType.FIVE_OF_A_KIND
                : HandType.FOUR_OF_A_KIND;
        } else if ( uniqueRankCountsDescending.size() == 2 && uniqueRankCountsDescending.get( 0 ) == 3 ) {
            return jokerCount == 3 || jokerCount == 2
                ? HandType.FIVE_OF_A_KIND
                : HandType.FULL_HOUSE;
        } else if ( uniqueRankCountsDescending.size() == 3 && uniqueRankCountsDescending.get( 0 ) == 3 ) {
            return jokerCount == 3 || jokerCount == 1
                ? HandType.FOUR_OF_A_KIND
                : HandType.THREE_OF_A_KIND;
        } else if ( uniqueRankCountsDescending.size() == 3 && uniqueRankCountsDescending.get( 0 ) == 2 ) {
            return switch ( jokerCount ) {
                case 2 -> HandType.FOUR_OF_A_KIND;
                case 1 -> HandType.FULL_HOUSE;
                default -> HandType.TWO_PAIR;
            };
        } else if ( uniqueRankCountsDescending.size() == 4 ) {
            return jokerCount == 2 || jokerCount == 1
                ? HandType.THREE_OF_A_KIND
                : HandType.ONE_PAIR;
        } else if ( uniqueRankCountsDescending.size() == 5 ) {
            return jokerCount == 1
                ? HandType.ONE_PAIR
                : HandType.HIGH_CARD;
        }
        throw new IllegalStateException();
    }

    private List<Long> uniqueRankCountsDescending() {
        Collection<Long> uniqueRankCounts = ranks
            .stream()
            .collect( groupingBy( identity(), counting() ) )
            .values();
        List<Long> uniqueRankCountsDescending = new ArrayList<>( uniqueRankCounts );
        uniqueRankCountsDescending.sort( comparing( Long::longValue ).reversed() );
        return uniqueRankCountsDescending;
    }

    private int countJokers() {
        return (int) ranks
            .stream()
            .filter( rank -> rank == Rank.JOKER )
            .count();
    }

    HandType type() {
        return type;
    }

    @Override
    public int compareTo( Hand other ) {
        int typeComparison = type.compareTo( other.type );
        return typeComparison != 0
            ? typeComparison
            : compareRanks( other );
    }

    private int compareRanks( Hand other ) {
        for ( int i = 0; i < ranks.size(); ++i ) {
            Rank thisRank = ranks.get( i );
            Rank otherRank = other.ranks.get( i );
            int rankComparison = thisRank.compareTo( otherRank );
            if ( rankComparison != 0 ) {
                return rankComparison;
            }
        }

        return 0;
    }

    Hand convertJacksToJokers() {
        List<Rank> ranksWithJacksConvertedToJokers = ranks
            .stream()
            .map( rank -> rank == Rank.JACK ? Rank.JOKER : rank )
            .toList();
        return new Hand( ranksWithJacksConvertedToJokers );
    }
}
