package christmas.domain;

public abstract class Menu {
    private final String menuName;

    private final long price;


    public Menu(String menuName, long price) {
        validate(menuName, price);
        this.menuName = menuName;
        this.price = price;
    }

    private void validate(String menuName, long price) {
        if(!menuName.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*")) {
            throw new IllegalArgumentException("[ERROR] 특수문자를 사용 할 수 없습니다.");
        }
        if(menuName.length() > 20) {
            throw new IllegalArgumentException("[ERROR] 20자 이상 입력하실 수 없습니다.");
        }
        if(price < 0 || price > 100000000) {
            throw new IllegalArgumentException("[ERROR] 0원 이상 1억원 이하의 금액을 입력해주세요");
        }
    }

    public String getMenuName() {
        return menuName;
    }

    public long getPrice() {
        return price;
    }
}
