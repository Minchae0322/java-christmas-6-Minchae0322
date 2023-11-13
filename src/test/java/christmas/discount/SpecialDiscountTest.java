package christmas.discount;

import christmas.discount.impl.SpecialDiscount;
import christmas.domain.Customer;
import christmas.domain.Menu;
import christmas.domain.menuImpl.Appetizer;
import christmas.domain.menuImpl.Dessert;
import christmas.domain.menuImpl.Main;
import christmas.util.CalendarProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.text.AsyncBoxView;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Menu, Integer> menus = new HashMap<>();
        menus.put(new Appetizer("양송이수프", 6000), 2);
        menus.put(new Main("바비큐립", 54000), 2);
        menus.put(new Dessert("아이스크림", 5000), 3);

        Calendar visit = CalendarProvider.getCalendar(2023,  Calendar.DECEMBER, 10);
        Customer customer = new Customer(menus, visit);

        assertTrue(specialDiscount.isDiscountable(customer));
        assertEquals(1000, specialDiscount.discount(customer));
    }

    @Test
    @DisplayName("스페셜데이에 방문하지 않았을 경우 할인 x")
    void 할인_금액_스페셜데이x() {
        Map<Menu, Integer> menus = new HashMap<>();
        menus.put(new Appetizer("양송이수프", 6000), 2);
        menus.put(new Main("바비큐립", 54000), 2);
        menus.put(new Dessert("아이스크림", 5000), 3);

        Calendar visit = CalendarProvider.getCalendar(2023,  Calendar.DECEMBER, 15);
        Customer customer = new Customer(menus, visit);

        assertFalse(specialDiscount.isDiscountable(customer));
        assertEquals(0, specialDiscount.discount(customer));
    }
}