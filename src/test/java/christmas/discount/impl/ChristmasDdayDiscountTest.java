package christmas.discount.impl;

import christmas.domain.Customer;
import christmas.type.Badge;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ChristmasDdayDiscountTest {
    @Test
    void 할인_금액() {
        Calendar start = Calendar.getInstance();
        start.clear();
        start.set(2023,Calendar.DECEMBER,1);

        Calendar end = Calendar.getInstance();
        end.clear();
        end.set(2023, Calendar.DECEMBER, 25);

        Calendar visit = Calendar.getInstance();
        visit.clear();
        visit.set(2023, Calendar.DECEMBER, 21);

        ChristmasDdayDiscount christmasDdayDiscount = new ChristmasDdayDiscount(start, end);
        Customer customer = new Customer(null, visit);
        assertTrue(christmasDdayDiscount.isDiscountable(customer));
        assertEquals(3000, christmasDdayDiscount.discount(customer));
    }

}