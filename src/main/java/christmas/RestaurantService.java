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

    public static final Long MIN_BENEFIT_AMOUNT = 10000L;


    public RestaurantService(Restaurant restaurant, List<DiscountPolicy> discountPolicies, Giveaway giveaway) {
        this.restaurant = restaurant;
        this.discountPolicies = discountPolicies;
        this.giveaway = giveaway;
    }

    public Map<String, Long> getDiscountInfo(Customer customer) {
        Map<String, Long> discountInfo = new HashMap<>();
        if(isDiscountable(customer.getOrderCost())) {
            return discountInfo;
        }

        for(DiscountPolicy discountPolicy : discountPolicies) {
            long discountAmount = discount(discountPolicy, customer);
            if(discountAmount != 0L) {
                discountInfo.put(discountPolicy.getDiscountName(), discountAmount);
            }
        }
        return discountInfo;
    }

    public boolean isDiscountable(long amount) {
        return amount >= MIN_BENEFIT_AMOUNT;
    }

    public Long discount(DiscountPolicy discountPolicy, Customer customer) {
        return discountPolicy.discount(customer);
    }

    public long getDiscountBenefitAmount(Map<String, Long> discountInfo) {
        return discountInfo.values().stream().mapToLong(Long::longValue).sum();
    }

    public long getAllBenefitAmount(Customer customer) {
        return giveawayBenefitAmount(getCustomerGiveaway(customer.getOrderCost()))+ getDiscountBenefitAmount(getDiscountInfo(customer));
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
