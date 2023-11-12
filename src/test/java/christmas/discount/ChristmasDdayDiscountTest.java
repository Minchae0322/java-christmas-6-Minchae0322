package christmas.discount;

import christmas.discount.impl.ChristmasDdayDiscount;
import christmas.domain.Customer;
import christmas.type.Badge;
import christmas.util.CalendarProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ChristmasDdayDiscountTest {

    ChristmasDdayDiscount christmasDdayDiscount;

    @BeforeEach
    void before() {
        christmasDdayDiscount = new ChristmasDdayDiscount(CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 1),
                CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 25));
    }
    @Test
    @DisplayName("21일 날 방문했을때 크리스마스 디데이 할인으로 얻는 혜택")
    void 할인_금액_크리스마스_4일전() {
        Customer customer = new Customer(null,CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 21));

        assertTrue(christmasDdayDiscount.isDiscountable(customer));
        assertEquals(3000, christmasDdayDiscount.discount(customer));
    }

    @Test
    @DisplayName("25일 날 방문했을때 크리스마스 디데이 할인으로 얻는 혜택")
    void 할인_금액_크리스마스_당일() {
        Customer customer = new Customer(null,CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 25));

        assertTrue(christmasDdayDiscount.isDiscountable(customer));
        assertEquals(3400, christmasDdayDiscount.discount(customer));
    }

    @Test
    @DisplayName("1일 날 방문했을때 크리스마스 디데이 할인으로 얻는 혜택")
    void 할인_금액_크리스마스_이벤트_시작() {
        Customer customer = new Customer(null,CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 1));

        assertTrue(christmasDdayDiscount.isDiscountable(customer));
        assertEquals(1000, christmasDdayDiscount.discount(customer));
    }

    @Test
    @DisplayName("크리스마스 디데이 할인이 끝났을때  얻는 혜택")
    void 할인_금액_크리스마스_이벤트_끝난뒤_방문() {
        Customer customer = new Customer(null,CalendarProvider.getCalendar(2023, Calendar.DECEMBER, 27));

        assertFalse(christmasDdayDiscount.isDiscountable(customer));
        assertEquals(0, christmasDdayDiscount.discount(customer));
    }

}