package christmas.parser;

import christmas.domain.Menu;
import christmas.domain.Restaurant;

import java.util.Arrays;
import java.util.List;

import static christmas.validate.Validator.validateNumericAmount;

public class Parser {

    public static void parseMenu(Restaurant restaurant, String menus) {
        String[] parsedMenu = menus.split(",");
        for(String menu : parsedMenu) {
            String menuName = menu.split("-")[0];
            validateNumericAmount(menu.split("-")[1]);
            int amount = Integer.parseInt(menu.split("-")[1]);
            restaurant.getEqualMenu(menuName).getType(menuName, )
        }
    }
}
