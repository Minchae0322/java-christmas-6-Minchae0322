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

    public static Badge getBadge(long benefitAmount) {
        if(benefitAmount >= 20000) {
            return Badge.산타;
        }
        if(benefitAmount >= 10000) {
            return Badge.트리;
        }
        if(benefitAmount >= 5000) {
            return Badge.별;
        }
        return Badge.없음;
    }

    public static String getName(Badge badge) {
        return badge.badgeName;
    }
}
