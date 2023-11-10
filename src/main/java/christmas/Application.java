package christmas;

import christmas.domain.Restaurant;
import christmas.domain.menuImpl.Appetizer;
import christmas.domain.menuImpl.Dessert;
import christmas.domain.menuImpl.Main;
import christmas.view.InputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Restaurant restaurant = initRestaurant();
        InputView inputView = new InputView();
        inputView.readDate();
        inputView.readMenus(restaurant);
    }

    public static Restaurant initRestaurant() {
        return new Restaurant(List.of(new Appetizer("양송이수프", 6000), new Appetizer("타파스", 5500), new Appetizer("시저샐러드", 8000)
        , new Main("티본스테이크", 55000), new Main("바비큐립", 54000), new Main("해산물파스타", 35000)
        , new Main("크리스마스파스타", 25000), new Dessert("초코케이크", 15000)));
    }
}
