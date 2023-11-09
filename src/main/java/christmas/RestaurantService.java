package christmas;

import christmas.discount.DiscountPolicy;
import christmas.domain.Customer;
import christmas.domain.Menu;
import christmas.domain.Restaurant;
import christmas.type.Badge;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantService {
    private final Restaurant restaurant;
    private final List<DiscountPolicy> discountPolicies;

    public RestaurantService(Restaurant restaurant, List<DiscountPolicy> discountPolicies) {
        this.restaurant = restaurant;
        this.discountPolicies = discountPolicies;
    }

    public Customer order(List<Menu> orderedMenus, Calendar visitDate) {
        return new Customer(orderedMenus, visitDate);
    }

    public Map<String, Long> discount(Customer customer) {
        Map<String, Long> discountInfo = new HashMap<>();
        for(DiscountPolicy discountPolicy : discountPolicies) {
            if(discountPolicy.isDiscountable(customer)) {
                discountInfo.put(discountPolicy.getDiscountName(), discountPolicy.discount(customer));
            }
        }
        return discountInfo;
    }

    public long discountAmount(Map<String, Long> discountInfo) {
        return discountInfo.values().stream().mapToLong(Long::longValue).sum();
    }


}
