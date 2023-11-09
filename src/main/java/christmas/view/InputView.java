package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Menu;
import christmas.domain.Restaurant;
import christmas.parser.Parser;

import java.util.List;
import java.util.Map;

import static christmas.validate.Validator.validateDate;
import static christmas.validate.Validator.validateOrderMenus;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        try {
            validateDate(input);
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            e.getMessage();
            return readDate();
        }
    }

    public Map<Menu, Integer> readMenus(Restaurant restaurant) {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        try {
            return Parser.parseMenu(restaurant, input);
        } catch (IllegalArgumentException e) {
            e.getMessage();
            return readMenus(restaurant);
        }
    }
}
