package christmas.discount.impl;

import christmas.discount.DiscountPolicy;
import christmas.domain.Customer;
import christmas.domain.Menu;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChristmasDdayDiscount implements DiscountPolicy {

    private final String discountName = "크리스마스 디데이 할인";
    private final Calendar eventStart;
    private final Calendar eventEnd;
    public static final long DISCOUNT_AMOUNT_PER_DAY = 100;

    public ChristmasDdayDiscount(Calendar eventStart, Calendar eventEnd) {
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public long discount(Customer customer) {
        long diffSec = (customer.getVisitDate().getTimeInMillis() - eventStart.getTimeInMillis()) / 1000; //초 차이
        long diffDays = diffSec / (60 * 60 * 24);
        return 1000 + diffDays * DISCOUNT_AMOUNT_PER_DAY;
    }

    @Override
    public boolean isDiscountable(Customer customer) {
        if(customer.getVisitDate().equals(eventEnd) || customer.getVisitDate().equals(eventStart)) {
            return true;
        }
        return customer.getVisitDate().after(eventStart) && customer.getVisitDate().before(eventEnd);
    }

    @Override
    public String getDiscountName() {
        return discountName;
    }
}
