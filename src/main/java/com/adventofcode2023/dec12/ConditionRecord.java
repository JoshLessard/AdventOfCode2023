package com.adventofcode2023.dec12;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

class ConditionRecord {

    private final List<Condition> conditions;

    ConditionRecord( List<Condition> conditions ) {
        this.conditions = List.copyOf( conditions );
    }

    public long numberOfValidArrangements( List<Integer> contiguousDamagedGroupSizes ) {
        Collection<List<Condition>> permutations = permutationsForUnknownSprings( conditions );
        return permutations
            .stream()
            .filter( p -> isValid( p, contiguousDamagedGroupSizes ) )
            .count();
    }

    private Collection<List<Condition>> permutationsForUnknownSprings( List<Condition> conditions ) {
        int indexOfFirstUnknown = conditions.indexOf( Condition.UNKNOWN );
        if ( indexOfFirstUnknown == -1 ) {
            return List.of( conditions );
        } else {
            Collection<List<Condition>> permutations = new ArrayList<>();
            List<Condition> prefix = new LinkedList<>( conditions.subList( 0, indexOfFirstUnknown ) );
            Collection<List<Condition>> suffixPermutations = permutationsForUnknownSprings( conditions.subList( indexOfFirstUnknown + 1, conditions.size() ) );
            for ( Collection<Condition> suffixPermutation : suffixPermutations ) {
                List<Condition> permutation = new ArrayList<>( prefix );
                permutation.add( Condition.OPERATIONAL );
                permutation.addAll( suffixPermutation );
                permutations.add( permutation );
            }
            for ( Collection<Condition> suffixPermutation : suffixPermutations ) {
                List<Condition> permutation = new ArrayList<>( prefix );
                permutation.add( Condition.DAMAGED );
                permutation.addAll( suffixPermutation );
                permutations.add( permutation );
            }

            return permutations;
        }
    }

    private boolean isValid( List<Condition> permutation, List<Integer> expectedGroupSizes ) {
        List<Integer> actualGroupSizes = findContiguousDamagedGroupSizes( permutation );
        return actualGroupSizes.equals( expectedGroupSizes );
    }

    private List<Integer> findContiguousDamagedGroupSizes( List<Condition> permutation ) {
        List<Integer> actualGroupSizes = new ArrayList<>();
        int currentDamagedGroupSize = 0;
        for ( Condition condition : permutation) {
            if ( condition == Condition.DAMAGED ) {
                ++currentDamagedGroupSize;
            } else {
                if ( currentDamagedGroupSize > 0 ) {
                    actualGroupSizes.add( currentDamagedGroupSize );
                }
                currentDamagedGroupSize = 0;
            }
        }
        if ( currentDamagedGroupSize > 0 ) {
            actualGroupSizes.add( currentDamagedGroupSize );
        }
        return actualGroupSizes;
    }
}
