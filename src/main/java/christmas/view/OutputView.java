package christmas.view;

import christmas.domain.Menu;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OutputView {
    public void printMenu(Map<Menu, Integer> orderedMenu) {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
        System.out.println("<주문 메뉴>");
        for(Map.Entry<Menu, Integer> menu : orderedMenu.entrySet()) {
            System.out.println(menu.getKey().getMenuName() + " " + menu.getValue() + "개");
        }
        System.out.println();
    }

    public void printOrderedAmount(long amount) {
        System.out.println("<할인 전 총주문 금액>");
        DecimalFormat decFormat = new DecimalFormat("###,###");
        System.out.println(decFormat.format(amount) + "원");
        System.out.println();
    }

    public void printBenefitMenu(List<Menu> menus) {
        System.out.println("<증정 메뉴>");
        if(menus == null || menus.isEmpty()) {
            System.out.println("없음");
            System.out.println();
            return;
        }
        for(Menu menu: menus) {
            System.out.println(menu.getMenuName() + " 1개");
        }
        System.out.println();
    }

    public void printBenefit(Map<String, Long> benefits, long benefitMenuAmount) {
        System.out.println("<혜택 내역>");
        if(benefits.isEmpty()) {
            System.out.println("없음\n");
            return;
        }
        DecimalFormat decFormat = new DecimalFormat("###,###");
        for(Map.Entry<String, Long> menu : benefits.entrySet()) {
            System.out.println(menu.getKey() + ": -" + decFormat.format(menu.getValue()) + "원");
        }
        System.out.println("증정 이벤트" + ": -" + decFormat.format(benefitMenuAmount) + "원\n");

    }

    public void printAllBenefitAmount(long amount) {
        System.out.println("<총혜택 금액>");
        if(amount == 0) {
            System.out.println("-"  + "0원\n");
            return;
        }
        DecimalFormat decFormat = new DecimalFormat("###,###");
        System.out.println("-" + decFormat.format(amount) + "원");
        System.out.println();
    }
}
