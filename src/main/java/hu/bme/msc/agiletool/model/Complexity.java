package hu.bme.msc.agiletool.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

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


    private static Map<Integer, Complexity> valueA = new HashMap<Integer, Complexity>();

    static {
        valueA.put(0, Complexity.ZERO);
        valueA.put(1, Complexity.ONE);
        valueA.put(2, Complexity.TWO);
        valueA.put(3, Complexity.THREE);
        valueA.put(5, Complexity.FIVE);
        valueA.put(8, Complexity.EIGHT);
        valueA.put(13, Complexity.THIRTEEN);
        valueA.put(20, Complexity.TWENTY);
        valueA.put(40, Complexity.FORTY);
        valueA.put(100, Complexity.HUNDRED);
    }

    @JsonCreator
    public static Complexity forValue(Integer value) {
        return valueA.get(value);
    }

    @JsonValue
    public Integer toValue() {
        for (Map.Entry<Integer, Complexity> entry : valueA.entrySet()) {
            if (entry.getValue() == this)
                return entry.getKey();
        }

        return null; // or fail
    }

    private final int value;

    Complexity(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }

//    public static Complexity getComplexityBayValaue(int value){
//        for (Complexity c : Complexity.values()) {
//            if (c.getValue() == value){
//                return c;
//            }
//        }
//        return Complexity.ZERO;
//    }

}
