package christmas.validate;


import christmas.domain.Menu;
import christmas.domain.Restaurant;
import christmas.domain.menuImpl.Beverage;

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

    public static void validateOrderMenus(Restaurant restaurant, String inputMenu) {
        if(!inputMenu.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        if(restaurant.getEqualMenu(inputMenu) == null) {
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
