package christmas.discount.impl;

import christmas.discount.DiscountPolicy;
import christmas.domain.Customer;
import christmas.type.DayOfWeek;

import java.util.Calendar;
import java.util.List;

public class WeekendDiscount implements DiscountPolicy {

    private final String discountName = "주말 할인";
    private final List<DayOfWeek> discountDays;

    public static final long WEEKEND_DISCOUNT = 2023;

    public WeekendDiscount(List<DayOfWeek> discountDays) {
        this.discountDays = discountDays;
    }

    @Override
    public long discount(Customer customer) {
        return customer.getMainAmount() * WEEKEND_DISCOUNT;
    }

    @Override
    public boolean isDiscountable(Customer customer) {
        Calendar visitDay = customer.getVisitDate();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(visitDay.get(Calendar.DAY_OF_WEEK));
        return discountDays.contains(dayOfWeek) && customer.getDessertAmount() > 0;
    }

    @Override
    public String getDiscountName() {
        return discountName;
    }
}
