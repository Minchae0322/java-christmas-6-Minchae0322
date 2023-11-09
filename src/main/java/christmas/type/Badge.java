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
}
