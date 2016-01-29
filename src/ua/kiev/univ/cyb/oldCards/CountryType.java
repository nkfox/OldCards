package ua.kiev.univ.cyb.oldCards;

public enum CountryType {

    UKRAINE("Ukraine"),
    USA("USA"),
    SPAIN("Spain");
    private final String value;

    CountryType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CountryType fromValue(String v) {
        for (CountryType c : CountryType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
