package christmas.parser;

import christmas.domain.Menu;
import christmas.domain.Restaurant;

import java.util.HashMap;

import static christmas.validate.Validator.*;

public class Parser {

    public static HashMap<Menu, Integer> parseMenu(Restaurant restaurant, String menus) {
        String[] parsedMenu = menus.split(",");
        HashMap<Menu, Integer> menuInfo = new HashMap<>();

        for(String menu : parsedMenu) {
            String menuName = menu.split("-")[0];
            int amount = Integer.parseInt(menu.split("-")[1]);
            menuInfo.put(restaurant.getEqualMenu(menuName), amount);
        }
        return menuInfo;
    }

    public static void valid() {
        /*int amountSum = 0;
        validateOrderMenus(restaurant, menuName);
        validateNumericAmount(menu.split("-")[1]);
        amountSum += amount;
        if(amountSum < 0 || amountSum > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        validateAllBeverage(menuInfo);*/
    }


}
