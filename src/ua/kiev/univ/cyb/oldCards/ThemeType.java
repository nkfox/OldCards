package ua.kiev.univ.cyb.oldCards;

public enum ThemeType {

    CITY_LANDSCAPE("city landscape"),
    NATURE("nature"),
    PEOPLE("people"),
    RELIGION("religion"),
    SPORT("sport"),
    ARCHITECTURE("architecture");
    private final String value;

    ThemeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ThemeType fromValue(String v) {
        for (ThemeType c : ThemeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
