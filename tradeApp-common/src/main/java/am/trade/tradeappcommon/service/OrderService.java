package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    void addOrder (Order order);

    List<Order> findAllOrders();

    Order getById(int id);

    Order findOrderByPeopleId (int id);

    List<Order> findAllByPeopleId(int id);
}
