package christmas.discount;

import christmas.domain.Customer;
import christmas.domain.Menu;

import java.util.Calendar;
import java.util.List;

public interface DiscountPolicy {
    long discount(Customer customer);

    boolean isDiscountable(Customer customer);

    String getDiscountName();

}
