package com.adventofcode2023.dec11;

class PointContainer {

    private Point point;

    PointContainer( Point point ) {
        this.point = point;
    }

    void transformX( long delta ) {
        point = point.transformX( delta );
    }

    void transformY( long delta ) {
        point = point.transformY( delta );
    }

    Point point() {
        return point;
    }
}
