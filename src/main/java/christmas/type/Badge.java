package christmas.type;

public enum Badge {
    별(5000, "별"),
    트리(10000, "트리"),
    산타(20000, "산타"),
    없음(0, "없음");

    private final long price;
    private final String badgeName;

    Badge(long price, String badgeName) {
        this.price = price;
        this.badgeName = badgeName;
    }

    public String getBadgeName(long benefitAmount) {
        return (price <= benefitAmount) ? badgeName : "없음";
    }
}
