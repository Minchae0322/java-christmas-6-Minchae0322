package christmas.domain;

public abstract class Menu {
    private final String menuName;

    private final Integer price;

    public Menu(String menuName, Integer price) {
        this.menuName = menuName;
        this.price = price;
    }

    public String getMenuName() {
        return menuName;
    }

    public Integer getPrice() {
        return price;
    }
}
