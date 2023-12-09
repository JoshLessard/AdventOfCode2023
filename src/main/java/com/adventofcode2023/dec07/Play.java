package com.adventofcode2023.dec07;

record Play( Hand hand, int bid ) {

    Play convertJacksToJokers() {
        return new Play( hand.convertJacksToJokers(), bid );
    }
}
