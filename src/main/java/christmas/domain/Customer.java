package christmas.domain;

import christmas.domain.menuImpl.Dessert;
import christmas.domain.menuImpl.Main;
import christmas.type.Badge;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Customer {
    private final Map<Menu, Integer> menus;
    private final Calendar visitDate;
    private long discountAmount;

    public Customer(Map<Menu, Integer> menus, Calendar visitDate) {
        validate(menus, visitDate);
        this.menus = menus;
        this.visitDate = visitDate;
        this.discountAmount = 0;
    }

    private void validate(Map<Menu, Integer> menus, Calendar visitDate) {

    }

    public long addDiscountAmount(long amount) {
        return discountAmount += amount;
    }

    public long getOrderCost() {
        return menus.entrySet().stream()
                .map(menu -> menu.getKey().getPrice() * menu.getValue())
                .mapToLong(Long::longValue)
                .sum();
    }

    public int getDessertAmount() {
        return menus.entrySet().stream()
                .filter(menu -> menu.getKey() instanceof Dessert)
                .map(Map.Entry::getValue)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getMainAmount() {
        return menus.entrySet().stream()
                .filter(menu -> menu.getKey() instanceof Main)
                .map(Map.Entry::getValue)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Calendar getVisitDate() {
        return this.visitDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "menus=" + menus +
                '}';
    }
}
