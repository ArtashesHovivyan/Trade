package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.model.Order;
import am.trade.tradeappcommon.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface OrderService {

    void addOrder(Order order);

    List<Order> findAllOrders();

    List<Order> findAllByUserId(int user);

    List<Order> searchByDateRange(Date toDate, Date fromDate);
}
