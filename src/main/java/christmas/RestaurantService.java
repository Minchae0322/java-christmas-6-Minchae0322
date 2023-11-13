package christmas;

import christmas.discount.DiscountPolicy;
import christmas.domain.Customer;
import christmas.domain.Menu;
import christmas.domain.Restaurant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantService {
    private final Restaurant restaurant;
    private final List<DiscountPolicy> discountPolicies;

    public static final Long MIN_BENEFIT_AMOUNT = 10000L;


    public RestaurantService(Restaurant restaurant, List<DiscountPolicy> discountPolicies) {
        this.restaurant = restaurant;
        this.discountPolicies = discountPolicies;

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

    public long getDiscountBenefitAmount(Customer customer) {
        long benefitAmount = 0;
        for(DiscountPolicy discountPolicy : discountPolicies) {
            benefitAmount += discount(discountPolicy, customer);
        }
        return benefitAmount;
    }

    public long getAllBenefitAmount(Customer customer) {
        return giftsBenefitAmount(getCustomerGifts(customer.getOrderCost())) + getDiscountBenefitAmount(customer);
    }

    public List<Menu> getCustomerGifts(long orderAmount) {
        return restaurant.getRestaurantGiftMenus(orderAmount);
    }

    public long giftsBenefitAmount(List<Menu> gifts) {
        return gifts.stream()
                .map(Menu::getPrice)
                .mapToLong(Long::longValue)
                .sum();
    }

    public long giftsBenefitAmount(long amount) {
        return restaurant.getRestaurantGiftAmount(amount);
    }



}
