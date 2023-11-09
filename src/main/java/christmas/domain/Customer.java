package christmas.domain;

import christmas.domain.menuImpl.Dessert;
import christmas.domain.menuImpl.Main;
import christmas.type.Badge;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Customer {
    private final List<Menu> menus;
    private final Calendar visitDate;
    private long discountAmount;

    public Customer(List<Menu> menus, Calendar visitDate) {
        this.menus = menus;
        this.visitDate = visitDate;
        this.discountAmount = 0;
    }

    public long addDiscountAmount(long amount) {
        return discountAmount += amount;
    }

    public int getOrderAmount() {
        int sum = 0;
        for(Menu menu : menus) {
            sum += menu.getPrice();
        }
        return sum;
    }

    public int getDessertAmount() {
        return menus.stream()
                .filter(menu -> menu instanceof Dessert)
                .toList()
                .size();
    }

    public int getMainAmount() {
        return menus.stream()
                .filter(menu -> menu instanceof Main)
                .toList()
                .size();
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
