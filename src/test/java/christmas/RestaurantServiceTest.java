package christmas;

import christmas.discount.DiscountPolicy;
import christmas.discount.impl.ChristmasDdayDiscount;
import christmas.discount.impl.SpecialDiscount;
import christmas.discount.impl.WeekDayDiscount;
import christmas.discount.impl.WeekendDiscount;
import christmas.domain.Customer;
import christmas.domain.Gift;
import christmas.domain.Menu;
import christmas.domain.Restaurant;
import christmas.domain.menuImpl.Appetizer;
import christmas.domain.menuImpl.Beverage;
import christmas.domain.menuImpl.Dessert;
import christmas.domain.menuImpl.Main;
import christmas.type.Badge;
import christmas.type.DayOfWeek;
import christmas.util.CalendarProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static christmas.Application.getChristmasEventDate;
import static christmas.Application.getSpecialDate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    private RestaurantService restaurantService;
    private Restaurant restaurant;
    private List<DiscountPolicy> discountPolicies;

    @BeforeEach
    void setUp() {
        // Initialize your test data
        restaurant = new Restaurant(List.of(new Appetizer("양송이수프", 6000), new Appetizer("타파스", 5500), new Appetizer("시저샐러드", 8000)
                , new Main("티본스테이크", 55000), new Main("바비큐립", 54000), new Main("해산물파스타", 35000)
                , new Main("크리스마스파스타", 25000), new Dessert("초코케이크", 15000), new Dessert("아이스크림", 5000)
                , new Beverage("제로콜라", 3000), new Beverage("레드와인", 60000), new Beverage("샴페인", 25000)), initGifts());
        discountPolicies = initDiscountPolicy();
        restaurantService = new RestaurantService(restaurant, discountPolicies);
    }

    public static List<DiscountPolicy> initDiscountPolicy() {
        List<Calendar> christmasEvent = getChristmasEventDate();
        return List.of(new ChristmasDdayDiscount(christmasEvent.get(0), christmasEvent.get(1))
                ,new WeekDayDiscount(List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY))
                ,new WeekendDiscount(List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY))
                ,new SpecialDiscount(getSpecialDate()));
    }

    public static Gift initGifts() {
        Map<Menu, Long> giveaways = new HashMap<>();
        giveaways.put(new Beverage("샴페인", 25000), 120000L);
        return new Gift(giveaways);
    }

    @Test
    void 할인_혜택() {
        Map<Menu, Integer> menus = new HashMap<>();
        menus.put(new Appetizer("양송이수프", 6000), 2);
        menus.put(new Main("바비큐립", 54000), 2);
        menus.put(new Dessert("아이스크림", 5000), 3);

        Calendar visit = CalendarProvider.getCalendar(2023,  Calendar.DECEMBER, 10);
        Customer customer = new Customer(menus, visit);

        Map<String, Long> discountInfo = restaurantService.getDiscountInfo(customer);
        ;

        assertTrue(discountInfo.containsKey("크리스마스 디데이 할인"));
        assertTrue(discountInfo.containsKey("특별 할인"));
        assertTrue(discountInfo.containsKey("평일 할인"));
        assertTrue(restaurantService.getCustomerGifts(customer.getOrderCost()).stream().map(Menu::getMenuName).toList().contains("샴페인"));
    }

    @Test
    void 할인_금액() {
        Map<Menu, Integer> menus = new HashMap<>();
        menus.put(new Appetizer("양송이수프", 6000), 2);
        menus.put(new Main("바비큐립", 54000), 2);
        menus.put(new Dessert("아이스크림", 5000), 3);

        Calendar visit = CalendarProvider.getCalendar(2023,  Calendar.DECEMBER, 10);
        Customer customer = new Customer(menus, visit);

        Map<String, Long> discountInfo = restaurantService.getDiscountInfo(customer);

        assertEquals(1900, discountInfo.get("크리스마스 디데이 할인"));
        assertEquals(6069, discountInfo.get("평일 할인"));
        assertNull(discountInfo.get("주말 할인"));
        assertEquals(1000, discountInfo.get("특별 할인"));
        assertEquals(25000, restaurantService.giftsBenefitAmount(customer.getOrderCost()));
    }

    @Test
    void 총_혜택_금액() {
        Map<Menu, Integer> menus = new HashMap<>();
        menus.put(new Appetizer("양송이수프", 6000), 2);
        menus.put(new Main("바비큐립", 54000), 2);
        menus.put(new Dessert("아이스크림", 5000), 3);

        Calendar visit = CalendarProvider.getCalendar(2023,  Calendar.DECEMBER, 10);
        Customer customer = new Customer(menus, visit);

        Map<String, Long> discountInfo = restaurantService.getDiscountInfo(customer);

        assertEquals(33969, restaurantService.getAllBenefitAmount(customer));

    }


    @Test
    void 예상_결제_금액() {
        Map<Menu, Integer> menus = new HashMap<>();
        menus.put(new Appetizer("양송이수프", 6000), 2);
        menus.put(new Main("바비큐립", 54000), 2);
        menus.put(new Dessert("아이스크림", 5000), 3);

        Calendar visit = CalendarProvider.getCalendar(2023,  Calendar.DECEMBER, 10);
        Customer customer = new Customer(menus, visit);

        Map<String, Long> discountInfo = restaurantService.getDiscountInfo(customer);

        assertEquals(135000, customer.getOrderCost());
        assertEquals(126031, customer.getOrderCost() - restaurantService.getDiscountBenefitAmount(customer));
    }

    @Test
    void 고객_뱃지() {
        Map<Menu, Integer> menus = new HashMap<>();
        menus.put(new Appetizer("양송이수프", 6000), 2);
        menus.put(new Main("바비큐립", 54000), 2);
        menus.put(new Dessert("아이스크림", 5000), 3);

        Calendar visit = CalendarProvider.getCalendar(2023,  Calendar.DECEMBER, 10);
        Customer customer = new Customer(menus, visit);

        Map<String, Long> discountInfo = restaurantService.getDiscountInfo(customer);

        assertEquals("산타", customer.addBenefitAmount(restaurantService.getAllBenefitAmount(customer)));

    }


}