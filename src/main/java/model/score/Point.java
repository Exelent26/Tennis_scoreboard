package model.score;

import lombok.Getter;


public enum Point {
    LOVE(0),
    FIFTEEN(15),
    THIRTY(30),
    FORTY(40),
    ADVANTAGE(-1); // например, -1 для обозначения "AD"

    @Getter
    private final int value;

    Point(int value) {
        this.value = value;
    }

    public static Point nextPoints(Point correctPoint) {
        Point[] points = Point.values();
        int ordinal = correctPoint.ordinal();
        int nextOrdinal = (ordinal + 1) % points.length;
        return points[nextOrdinal];
    }



}