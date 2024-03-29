package christmas.discount.impl;

import christmas.discount.DiscountPolicy;
import christmas.domain.Customer;

import java.util.Calendar;
import java.util.List;

public class SpecialDiscount implements DiscountPolicy {
    private final String discountName = "특별 할인";
    private final List<Calendar> specialDays;

    public static final long DISCOUNT_SPECIAL_DAY = 1000;

    public SpecialDiscount(List<Calendar> specialDays) {
        this.specialDays = specialDays;
    }

    @Override
    public long discount(Customer customer) {
        if(isDiscountable(customer)) {
            return DISCOUNT_SPECIAL_DAY;
        }
        return 0L;
    }

    @Override
    public boolean isDiscountable(Customer customer) {
        for(Calendar calendar : specialDays) {
            if(calendar.equals(customer.getVisitDate())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDiscountName() {
        return discountName;
    }
}
