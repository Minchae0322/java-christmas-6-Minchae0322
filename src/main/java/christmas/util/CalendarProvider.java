package christmas.util;

import java.util.Calendar;

public class CalendarProvider {
    public static Calendar getCalendar(int year,  int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, day);
        return calendar;
    }
}
