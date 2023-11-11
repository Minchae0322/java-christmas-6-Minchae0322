package christmas.type;

public enum Badge {
    별(5000),
    트리(10000),
    산타(20000),
    없음(0);

    Badge(long price) {
        this.price = price;
    }

    private final long price;

    public String getBadgeName(long benefitAmount) {
        if(price >= 20000) {
            return "산타";
        }
        if(price >= 10000) {
            return "트리";
        }
        if(price >= 5000) {
            return "별";
        }
        return "없음";
    }
}
