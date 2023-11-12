package christmas.discount.impl;

import christmas.domain.Customer;
import christmas.util.CalendarProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.text.AsyncBoxView;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecialDiscountTest {
    SpecialDiscount specialDiscount;

    @BeforeEach
    void before() {
        specialDiscount = new SpecialDiscount(List.of(CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 3),
                CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 10), CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 17),
                CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 24), CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 25),
                CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 31)));
    }

    @Test
    @DisplayName("스페셜데이에 방문했을경우 할인")
    void 할인_금액() {
        Customer customer = new Customer(null, CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 10));

        assertTrue(specialDiscount.isDiscountable(customer));
        assertEquals(1000, specialDiscount.discount(customer));
    }

    @Test
    @DisplayName("스페셜데이에 방문하지 않았을 경우 할인 x")
    void 할인_금액_스페셜데이x() {
        Customer customer = new Customer(null, CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 9));

        assertFalse(specialDiscount.isDiscountable(customer));
        assertEquals(0, specialDiscount.discount(customer));
    }
}