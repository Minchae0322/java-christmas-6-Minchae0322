package christmas.domain.menuImpl;

import christmas.domain.Menu;

public class Main extends Menu {

    public Main(String menuName, long price, int amount) {
        super(menuName, price, amount);
    }

    @Override
    public Menu getType(String menuName, long price, int amount) {
        return new Main(menuName, price, amount);
    }
}
