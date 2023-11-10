package christmas.view;

import christmas.domain.Menu;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OutputView {
    public void printMenu(Map<Menu, Integer> orderedMenu) {
        System.out.println("<주문 메뉴>");
        for(Map.Entry<Menu, Integer> menu : orderedMenu.entrySet()) {
            System.out.println(menu.getKey().getMenuName() + " " + menu.getValue() + "개");
        }

    }

    public void printOrderedAmount(long amount) {
        System.out.println("<할인 전 총주문 금액>");
        DecimalFormat decFormat = new DecimalFormat("###,###");
        System.out.println(decFormat.format(amount) + "원");
    }

    public void printGivenMenu(List<Menu> menus) {
        System.out.println("<증정 메뉴>");
        if(menus == null || menus.isEmpty()) {
            System.out.println("없음");
            return;
        }
        for(Menu menu: menus) {
            System.out.println(menu.getMenuName() + "1개");
        }
    }

    public void printBenefit(Map<String, Long> benefits) {
        DecimalFormat decFormat = new DecimalFormat("###,###");
        for(Map.Entry<String, Long> menu : benefits.entrySet()) {
            System.out.println(menu.getKey() + ": -" + decFormat.format(menu.getValue()) + "원");
        }
    }
}
