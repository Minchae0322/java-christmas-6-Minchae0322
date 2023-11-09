package christmas.type;

public enum DayOfWeek {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7)
    ;

    public static DayOfWeek valueOf(int number) {
        for(DayOfWeek dayOfWeek : values()) {
            if(dayOfWeek.number == number) {
                return dayOfWeek;
            }
        }
        throw new IllegalArgumentException();
    }

    DayOfWeek(int i) {
        this.number = i;
    }

    private final int number;
}
