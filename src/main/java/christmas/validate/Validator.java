package christmas.validate;


import christmas.domain.Menu;
import christmas.domain.menuImpl.Beverage;

import java.util.List;
import java.util.Map;


public class Validator {

    public static final String WRONG_ORDER_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public static final String WRONG_DATE_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static void validateDate(String date) {
        if(!date.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(WRONG_DATE_MESSAGE);
        }
        int day = Integer.parseInt(date);
        if(day < 1 || day > 31) {
            throw new IllegalArgumentException(WRONG_DATE_MESSAGE);
        }
    }

    public static void validateOrderedMenuType(String menuType) {
        //입력이 (...),인 경우 String[] = {(...), ""} 로 넘어온다.
        if(menuType.equals("")) {
            throw new IllegalArgumentException(WRONG_ORDER_MESSAGE);
        }
        if(!menuType.contains("-")) {
            throw new IllegalArgumentException(WRONG_ORDER_MESSAGE);
        }
        //입력이 스테이크-1-피자-2인 경우
        if(menuType.split("-", -1).length > 2) {
            throw new IllegalArgumentException(WRONG_ORDER_MESSAGE);
        }
        String menuAmount = menuType.split("-", -1)[1];
        if(menuAmount.equals(" ") || menuAmount.equals("")) {
            throw new IllegalArgumentException(WRONG_ORDER_MESSAGE);
        }
        if(!menuAmount.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(WRONG_ORDER_MESSAGE);
        }
    }

    public static void validateOrderedAmount(List<Integer> orderedAmount) {
        for(Integer amount : orderedAmount) {
            if(amount < 0 || amount > 20) {
                throw new IllegalArgumentException(WRONG_ORDER_MESSAGE);
            }
        }
        int amountSum = orderedAmount.stream().mapToInt(Integer::intValue).sum();
        if(amountSum > 20 || amountSum < 0) {
            throw new IllegalArgumentException(WRONG_ORDER_MESSAGE);
        }
    }

    public static void validateOrderedMenus(Map<Menu, Integer> orderedMenu) {
            validateAllBeverage(orderedMenu);
            validateOrderedAmount(orderedMenu.values().stream().toList());
    }

    public static void validateDuplicateMenu(int original, int mapSize) {
        if(original != mapSize) {
            throw new IllegalArgumentException(WRONG_ORDER_MESSAGE);
        }
    }

    public static void validateAllBeverage(Map<Menu, Integer> map) {
        if(map.entrySet().stream()
                .filter(menu -> menu.getKey() instanceof Beverage)
                .toList().size() == map.size()) {
            throw new IllegalArgumentException(WRONG_ORDER_MESSAGE);
        }
    }
}
