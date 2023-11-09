package christmas;

import christmas.domain.Customer;
import christmas.domain.Menu;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class CustomerService {

    public Customer order(Map<Menu, Integer> orderedMenus, Calendar visitDate) {
        return new Customer(orderedMenus, visitDate);
    }
}
