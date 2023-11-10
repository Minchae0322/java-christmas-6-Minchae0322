package christmas.domain;

import java.util.List;
import java.util.Map;

public class Giveaway {
    private Map<Menu, Long> giveaways;

    public Giveaway(Map<Menu, Long> giveaways) {
        this.giveaways = giveaways;
    }

    public List<Menu> getGiveawaysReceive(long orderAmount) {
        return giveaways.entrySet()
                .stream()
                .filter(menu -> menu.getValue() <= orderAmount)
                .map(Map.Entry::getKey)
                .toList();
    }
}
