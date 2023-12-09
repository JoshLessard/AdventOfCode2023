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
        if ( uniqueRankCountsDescending.size() == 1 ) {
            return HandType.FIVE_OF_A_KIND;
        } else if ( uniqueRankCountsDescending.size() == 2 && uniqueRankCountsDescending.get( 0 ) == 4 ) {
            return HandType.FOUR_OF_A_KIND;
        } else if ( uniqueRankCountsDescending.size() == 2 && uniqueRankCountsDescending.get( 0 ) == 3 && uniqueRankCountsDescending.get( 1 ) == 2 ) {
            return HandType.FULL_HOUSE;
        } else if ( uniqueRankCountsDescending.size() == 3 && uniqueRankCountsDescending.get( 0 ) == 3 ) {
            return HandType.THREE_OF_A_KIND;
        } else if ( uniqueRankCountsDescending.size() == 3 && uniqueRankCountsDescending.get( 0 ) == 2 ) {
            return HandType.TWO_PAIR;
        } else if ( uniqueRankCountsDescending.size() == 4 ) {
            return HandType.ONE_PAIR;
        } else if ( uniqueRankCountsDescending.size() == 5 ) {
            return HandType.HIGH_CARD;
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
}
