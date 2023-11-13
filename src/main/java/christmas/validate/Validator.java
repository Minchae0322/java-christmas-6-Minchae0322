package christmas.validate;


import christmas.domain.Menu;
import christmas.domain.Restaurant;
import christmas.domain.menuImpl.Beverage;

import java.util.List;
import java.util.Map;


public class Validator {
    public static void validateDate(String date) {
        if(!date.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
        int day = Integer.parseInt(date);
        if(day < 1 || day > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateNumericAmount(String numeric) {
        if(numeric.equals(" ")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        if(!numeric.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateOrderedAmount(List<Integer> orderedAmount) {
        for(Integer amount : orderedAmount) {
            if(amount < 0 || amount > 20) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
        int amountSum = orderedAmount.stream().mapToInt(Integer::intValue).sum();
        if(amountSum > 20 || amountSum < 0) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateOrderedMenus(Map<Menu, Integer> orderedMenu) {
            validateAllBeverage(orderedMenu);
            validateOrderedAmount(orderedMenu.values().stream().toList());
    }

    public static void validateDuplicateMenu(int original, int mapSize) {
        if(original != mapSize) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateAllBeverage(Map<Menu, Integer> map) {
        if(map.entrySet().stream()
                .filter(menu -> menu.getKey() instanceof Beverage)
                .toList().size() == map.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
