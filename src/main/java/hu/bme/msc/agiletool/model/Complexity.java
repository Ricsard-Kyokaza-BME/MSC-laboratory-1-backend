package hu.bme.msc.agiletool.model;

public enum Complexity {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FIVE(5),
    EIGHT(8),
    THIRTEEN(13),
    TWENTY(20),
    FORTY(40),
    HUNDRED(100);

    private final int value;

    Complexity(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

}
