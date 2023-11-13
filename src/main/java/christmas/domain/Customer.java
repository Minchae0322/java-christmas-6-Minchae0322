package christmas.domain;

import christmas.domain.menuImpl.Dessert;
import christmas.domain.menuImpl.Main;
import christmas.type.Badge;

import java.util.Calendar;
import java.util.Map;

public class Customer {
    private final Map<Menu, Integer> orderedMenus;
    private final Calendar visitDate;
    private long benefits;
    private Badge badge;

    public Customer(Map<Menu, Integer> orderedMenus, Calendar visitDate) {
        validate(orderedMenus, visitDate);
        this.orderedMenus = orderedMenus;
        this.visitDate = visitDate;
        this.benefits = 0;
        this.badge = Badge.없음;
    }

    private void validate(Map<Menu, Integer> menus, Calendar visitDate) {
        if(menus == null) {
            throw new NullPointerException("메뉴를 주문하지 않았습니다.");
        }
    }

    public String addBenefitAmount(long amount) {
        benefits += amount;
        return badge.getBadgeName(benefits);
    }



    public long getOrderCost() {
        return orderedMenus.entrySet().stream()
                .map(menu -> menu.getKey().getPrice() * menu.getValue())
                .mapToLong(Long::longValue)
                .sum();
    }

    public int getDessertAmount() {
        return orderedMenus.entrySet().stream()
                .filter(menu -> menu.getKey() instanceof Dessert)
                .map(Map.Entry::getValue)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getMainAmount() {
        return orderedMenus.entrySet().stream()
                .filter(menu -> menu.getKey() instanceof Main)
                .map(Map.Entry::getValue)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Calendar getVisitDate() {
        return this.visitDate;
    }

    public Map<Menu, Integer> getOrderedMenus() {
        return orderedMenus;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "menus=" + orderedMenus +
                '}';
    }
}
