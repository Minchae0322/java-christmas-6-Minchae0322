package christmas;

import christmas.domain.Customer;
import christmas.domain.Menu;

import java.util.Calendar;
import java.util.List;

public class CustomerService {

    public Customer order(List<Menu> orderedMenus, Calendar visitDate) {
        return new Customer(orderedMenus, visitDate);
    }
}
