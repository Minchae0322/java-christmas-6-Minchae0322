package christmas.domain.menuImpl;

import christmas.domain.Menu;

public class Dessert extends Menu {


    public Dessert(String menuName, long price, int amount) {
        super(menuName, price, amount);
    }

    @Override
    public Menu getType(String menuName, long price, int amount) {
        return new Dessert(menuName, price, amount);
    }
}
