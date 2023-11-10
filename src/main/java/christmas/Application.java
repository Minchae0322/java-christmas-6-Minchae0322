package christmas;

import christmas.discount.DiscountPolicy;
import christmas.discount.impl.ChristmasDdayDiscount;
import christmas.discount.impl.SpecialDiscount;
import christmas.discount.impl.WeekDayDiscount;
import christmas.discount.impl.WeekendDiscount;
import christmas.domain.Giveaway;
import christmas.domain.Menu;
import christmas.domain.Restaurant;
import christmas.domain.menuImpl.Appetizer;
import christmas.domain.menuImpl.Beverage;
import christmas.domain.menuImpl.Dessert;
import christmas.domain.menuImpl.Main;
import christmas.type.DayOfWeek;
import christmas.util.CalendarProvider;
import christmas.view.InputView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.util.CalendarProvider.getCalendar;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Restaurant restaurant = initRestaurant();
        RestaurantService restaurantService = new RestaurantService(restaurant, initDiscountPolicy(), initGiveaway());
        CustomerService customerService = new CustomerService();
        InputView inputView = new InputView();
        Calendar visitDay = getCalendar(2023, Calendar.DECEMBER, inputView.readDate());
        customerService.order(inputView.readMenus(restaurant), visitDay);

    }

    public static Restaurant initRestaurant() {
        return new Restaurant(List.of(new Appetizer("양송이수프", 6000), new Appetizer("타파스", 5500), new Appetizer("시저샐러드", 8000)
        , new Main("티본스테이크", 55000), new Main("바비큐립", 54000), new Main("해산물파스타", 35000)
        , new Main("크리스마스파스타", 25000), new Dessert("초코케이크", 15000), new Dessert("아이스크림", 5000)
        , new Beverage("제로콜라", 3000), new Beverage("레드와인", 60000), new Beverage("샴페인", 25000)));
    }

    public static List<DiscountPolicy> initDiscountPolicy() {
        List<Calendar> christmasEvent = getChristmasEventDate();
        return List.of(new ChristmasDdayDiscount(christmasEvent.get(0), christmasEvent.get(1))
        ,new WeekDayDiscount(List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY))
        ,new WeekendDiscount(List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY))
        ,new SpecialDiscount(getSpecialDate()));
    }

    public static Giveaway initGiveaway() {
        Map<Menu, Long> giveaways = new HashMap<>();
        giveaways.put(new Beverage("샴페인", 25000), 120000L);
        return new Giveaway(giveaways);
    }

    public static List<Calendar> getChristmasEventDate() {
        Calendar christmasStart = getCalendar(2023, Calendar.DECEMBER, 1);
        Calendar christmasEnd = getCalendar(2023, Calendar.DECEMBER, 25);
        return List.of(christmasStart, christmasEnd);
    }

    public static List<Calendar> getSpecialDate() {
        return List.of(getCalendar(2023, Calendar.DECEMBER, 3),
                getCalendar(2023, Calendar.DECEMBER, 10));
    }
}
