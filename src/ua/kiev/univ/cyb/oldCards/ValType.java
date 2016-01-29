package ua.kiev.univ.cyb.oldCards;

public enum ValType {

    COLLECTION("collection"),
    HISTORICAL("historical"),
    THEMATIC("thematic");
    private final String value;

    ValType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ValType fromValue(String v) {
        for (ValType c : ValType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
