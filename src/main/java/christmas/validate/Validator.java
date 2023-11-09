package christmas.validate;

import christmas.domain.Menu;
import christmas.domain.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

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
        if(numeric.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateOrderMenus(Restaurant restaurant, String inputMenu) {
        if(restaurant.getEqualMenu(inputMenu) == null) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
