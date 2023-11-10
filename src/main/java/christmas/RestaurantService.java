package christmas;

import christmas.discount.DiscountPolicy;
import christmas.domain.Customer;
import christmas.domain.Menu;
import christmas.domain.Restaurant;
import christmas.domain.Giveaway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantService {
    private final Restaurant restaurant;
    private final List<DiscountPolicy> discountPolicies;
    private final Giveaway giveaway;


    public RestaurantService(Restaurant restaurant, List<DiscountPolicy> discountPolicies, Giveaway giveaway) {
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

    public long getDiscountBenefitAmount(Map<String, Long> discountInfo) {
        return discountInfo.values().stream().mapToLong(Long::longValue).sum();
    }

    public long getAllBenefitAmount(Customer customer) {
        return giveawayBenefitAmount(getCustomerGiveaway(customer.getOrderCost()))+ getDiscountBenefitAmount(discount(customer));
    }

    public List<Menu> getCustomerGiveaway(long orderAmount) {
        return giveaway.getGiveawaysReceive(orderAmount);
    }

    public long giveawayBenefitAmount(List<Menu> giveaways) {
        return giveaways.stream()
                .map(Menu::getPrice)
                .mapToLong(Long::longValue)
                .sum();
    }



}
