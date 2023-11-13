package christmas.domain;

import java.util.List;
import java.util.Map;

public class Gift {
    private Map<Menu, Long> giveaways;

    public Gift(Map<Menu, Long> giveaways) {
        this.giveaways = giveaways;
    }

    public List<Menu> getGiftsReceivedDependingPrice(long orderAmount) {
        return giveaways.entrySet()
                .stream()
                .filter(menu -> menu.getValue() <= orderAmount)
                .map(Map.Entry::getKey)
                .toList();
    }
}
