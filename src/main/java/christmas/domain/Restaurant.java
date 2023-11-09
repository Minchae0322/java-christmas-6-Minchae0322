package christmas.domain;

import java.util.List;

public class Restaurant {
    private final List<Menu> restaurantMenu;

    public Restaurant(List<Menu> restaurantMenu) {
        this.restaurantMenu = restaurantMenu;
    }
}
