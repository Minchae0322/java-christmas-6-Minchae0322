package christmas.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Restaurant {
    private final List<Menu> restaurantMenu;

    public Restaurant(List<Menu> restaurantMenu) {
        validate(restaurantMenu);
        this.restaurantMenu = restaurantMenu;
    }

    private void validate(List<Menu> restaurantMenu) {
        if(restaurantMenu.stream().distinct().toList().size() != restaurantMenu.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 메뉴가 있습니다.");
        }
    }
}
