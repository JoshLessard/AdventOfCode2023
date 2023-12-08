package com.adventofcode2023.dec06;

class Race {

    private final int timeInMilliseconds;

    Race( int timeInMilliseconds ) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

    int timeInMilliseconds() {
        return timeInMilliseconds;
    }

    int distanceTravelled( int millisecondsToHoldButton ) {
        int millisecondsToTravel = timeInMilliseconds - millisecondsToHoldButton;
        int speedPerMillisecond = millisecondsToHoldButton;
        return millisecondsToTravel * speedPerMillisecond;
    }
}
