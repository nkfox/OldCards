package ua.kiev.univ.cyb.oldCards;

public enum TType {
   
    CONGRATULATORY("congratulatory"),
    ADVERTISING("advertising"),
    COMMON("common");
    private final String value;

    TType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TType fromValue(String v) {
        for (TType c : TType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}