package christmas.discount;

import christmas.discount.impl.WeekDayDiscount;
import christmas.domain.Customer;
import christmas.domain.Menu;
import christmas.domain.menuImpl.Dessert;
import christmas.type.DayOfWeek;
import christmas.util.CalendarProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WeekDayDiscountTest {
    WeekDayDiscount weekDayDiscount;

    @BeforeEach
    void before() {
        weekDayDiscount = new WeekDayDiscount(List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY));
    }

    @Test
    @DisplayName("평일에 방문했을경우 할인 디저트 1개 주문")
    void 할인_금액_평일_1개() {
        Map<Menu, Integer> orderedMenus = new HashMap<>();
        orderedMenus.put(new Dessert("아이스크림", 5000), 1);
        Customer customer = new Customer(orderedMenus, CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 11));

        assertTrue(weekDayDiscount.isDiscountable(customer));
        assertEquals(2023, weekDayDiscount.discount(customer));
    }

    @Test
    @DisplayName("평일에 방문했을경우 할인 디저트 2개 주문")
    void 할인_금액_평일_2개() {
        Map<Menu, Integer> orderedMenus = new HashMap<>();
        orderedMenus.put(new Dessert("아이스크림", 5000), 2);
        Customer customer = new Customer(orderedMenus, CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 11));

        assertTrue(weekDayDiscount.isDiscountable(customer));
        assertEquals(4046, weekDayDiscount.discount(customer));
    }

    @Test
    @DisplayName("평일에 방문했을경우 할인 디저트 0개 주문")
    void 할인_금액_평일_0개() {
        Map<Menu, Integer> orderedMenus = new HashMap<>();
        orderedMenus.put(new Dessert("아이스크림", 5000), 0);
        Customer customer = new Customer(orderedMenus, CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 11));

        assertFalse(weekDayDiscount.isDiscountable(customer));
        assertEquals(0, weekDayDiscount.discount(customer));
    }

    @Test
    @DisplayName("평일에 방문하지 않았을 경우")
    void 할인_금액_평일x() {
        Map<Menu, Integer> orderedMenus = new HashMap<>();
        orderedMenus.put(new Dessert("아이스크림", 5000), 1);
        Customer customer = new Customer(orderedMenus, CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 15));

        assertFalse(weekDayDiscount.isDiscountable(customer));
        assertEquals(0, weekDayDiscount.discount(customer));
    }
}