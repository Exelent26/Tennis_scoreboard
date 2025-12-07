package model.score;

import lombok.Getter;


public enum Point {
    LOVE(0),
    FIFTEEN(15),
    THIRTY(30),
    FORTY(40),
    ADVANTAGE(-1);

    @Getter
    private final int value;

    Point(int value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return switch (this) {
            case LOVE -> "0";
            case FIFTEEN -> "15";
            case THIRTY -> "30";
            case FORTY -> "40";
            case ADVANTAGE -> "AD";
        };
    }

    public String getDisplayValue() {
        return switch (this) {
            case LOVE -> "0";
            case FIFTEEN -> "15";
            case THIRTY -> "30";
            case FORTY -> "40";
            case ADVANTAGE -> "AD";
        };


    }
}