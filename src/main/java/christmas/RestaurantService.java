package christmas;

import christmas.discount.DiscountPolicy;
import christmas.domain.Customer;
import christmas.domain.Menu;
import christmas.domain.Restaurant;
import christmas.domain.Gift;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantService {
    private final Restaurant restaurant;
    private final List<DiscountPolicy> discountPolicies;
    private final Gift gift;

    public static final Long MIN_BENEFIT_AMOUNT = 10000L;


    public RestaurantService(Restaurant restaurant, List<DiscountPolicy> discountPolicies, Gift gift) {
        this.restaurant = restaurant;
        this.discountPolicies = discountPolicies;
        this.gift = gift;
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
        return giveawayBenefitAmount(getCustomerGifts(customer.getOrderCost()))+ getDiscountBenefitAmount(getDiscountInfo(customer));
    }

    public List<Menu> getCustomerGifts(long orderAmount) {
        return restaurant.getRestaurantGiftMenus(orderAmount);
    }

    public long giveawayBenefitAmount(List<Menu> giveaways) {
        return giveaways.stream()
                .map(Menu::getPrice)
                .mapToLong(Long::longValue)
                .sum();
    }



}
