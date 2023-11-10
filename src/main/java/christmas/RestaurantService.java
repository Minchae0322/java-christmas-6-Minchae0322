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
    private final Menu giveaway;

    public RestaurantService(Restaurant restaurant, List<DiscountPolicy> discountPolicies, Menu giveaway) {
        this.restaurant = restaurant;
        this.discountPolicies = discountPolicies;
        this.giveaway = giveaway;
    }

    public Map<String, Long> discount(Customer customer) {
        Map<String, Long> discountInfo = new HashMap<>();
        if(customer.getOrderCost() < 10000) {
            return discountInfo;
        }
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
