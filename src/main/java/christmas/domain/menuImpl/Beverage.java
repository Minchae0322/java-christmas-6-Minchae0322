package christmas.domain.menuImpl;

import christmas.domain.Menu;

public class Beverage extends Menu {

    public Beverage(String menuName, long price, int amount) {
        super(menuName, price, amount);
    }


    @Override
    public Menu getType(String menuName, long price, int amount) {
        return new Beverage(menuName, price, amount);
    }
}
