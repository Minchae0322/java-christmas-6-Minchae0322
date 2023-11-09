package christmas.domain.menuImpl;

import christmas.domain.Menu;

public class Appetizer extends Menu {
    public Appetizer(String menuName, long price, int amount) {
        super(menuName, price, amount);
    }

    @Override
    public Menu getType(String menuName, long price, int amount) {
        return new Appetizer(menuName, price, amount);
    }


}
