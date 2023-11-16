package christmas.domain;

import java.util.List;

public class Restaurant {
    private final List<Menu> restaurantMenu;

    private final Gift giftMenus;

    public Restaurant(List<Menu> restaurantMenu, Gift giftMenus) {
        validate(restaurantMenu);
        this.restaurantMenu = restaurantMenu;
        this.giftMenus = giftMenus;
    }

    private void validate(List<Menu> restaurantMenu) {
        if(restaurantMenu.stream().distinct().toList().size() != restaurantMenu.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 메뉴가 있습니다.");
        }
    }

    public Menu getEqualMenu(String menuName) {
        for(Menu menu : restaurantMenu) {
            if(menu.getMenuName().equals(menuName)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    public List<Menu> getRestaurantGiftMenus(long amount) {
        return giftMenus.getGiftsReceivedDependingPrice(amount);
    }

    public long getRestaurantGiftAmount(long amount) {
        return giftMenus.getGiftsBenefitAmount(amount);
    }
}
