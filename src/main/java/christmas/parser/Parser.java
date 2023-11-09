package christmas.parser;

import christmas.domain.Menu;
import christmas.domain.Restaurant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static christmas.validate.Validator.validateNumericAmount;
import static christmas.validate.Validator.validateOrderMenus;

public class Parser {

    public static HashMap<Menu, Integer> parseMenu(Restaurant restaurant, String menus) {
        String[] parsedMenu = menus.split(",");
        HashMap<Menu, Integer> menuInfo = new HashMap<>();
        for(String menu : parsedMenu) {
            String menuName = menu.split("-")[0];
            validateOrderMenus(restaurant, menuName);
            validateNumericAmount(menu.split("-")[1]);
            int amount = Integer.parseInt(menu.split("-")[1]);
            menuInfo.put(restaurant.getEqualMenu(menuName), amount);
        }
        return menuInfo;
    }
}
