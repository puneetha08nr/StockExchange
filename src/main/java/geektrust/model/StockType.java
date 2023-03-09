package geektrust.model;

import java.util.Arrays;

public enum StockType {
    BUY("buy"), SELL("sell");

    private final String value;

    StockType(String value) {
        this.value = value;
    }

    public static StockType fromValue(String value) {
        return Arrays.stream(StockType.values()).filter(type -> type.getValue().equals(value))
            .findFirst().orElseThrow();
    }

    public String getValue() {
        return value;
    }
}
