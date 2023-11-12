package christmas.discount.impl;

import christmas.domain.Customer;
import christmas.domain.Menu;
import christmas.domain.menuImpl.Dessert;
import christmas.domain.menuImpl.Main;
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

class WeekendDiscountTest {
    WeekendDiscount weekendDiscount;

    @BeforeEach
    void before() {
        weekendDiscount = new WeekendDiscount(List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));
    }

    @Test
    @DisplayName("주말에 방문했을경우 할인 메인 1개 주문")
    void 할인_금액_주말_1개() {
        Map<Menu, Integer> orderedMenus = new HashMap<>();
        orderedMenus.put(new Main("티본스테이크", 55000), 1);
        Customer customer = new Customer(orderedMenus, CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 15));

        assertTrue(weekendDiscount.isDiscountable(customer));
        assertEquals(2023, weekendDiscount.discount(customer));
    }

    @Test
    @DisplayName("주말에 방문했을경우 할인 메인 2개 주문")
    void 할인_금액_주말_2개() {
        Map<Menu, Integer> orderedMenus = new HashMap<>();
        orderedMenus.put(new Main("티본스테이크", 55000), 2);
        Customer customer = new Customer(orderedMenus, CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 15));

        assertTrue(weekendDiscount.isDiscountable(customer));
        assertEquals(4046, weekendDiscount.discount(customer));
    }

    @Test
    @DisplayName("주말에 방문했을경우 할인 메인 0개 주문")
    void 할인_금액_주말_0개() {
        Map<Menu, Integer> orderedMenus = new HashMap<>();
        orderedMenus.put(new Main("티본스테이크", 55000), 0);
        Customer customer = new Customer(orderedMenus, CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 15));

        assertFalse(weekendDiscount.isDiscountable(customer));
        assertEquals(0, weekendDiscount.discount(customer));
    }

    @Test
    @DisplayName("주말에 방문하지 않았을 경우")
    void 할인_금액_주말x() {
        Map<Menu, Integer> orderedMenus = new HashMap<>();
        orderedMenus.put(new Main("티본스테이크", 55000), 2);
        Customer customer = new Customer(orderedMenus, CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 11));

        assertFalse(weekendDiscount.isDiscountable(customer));
        assertEquals(0, weekendDiscount.discount(customer));
    }
}