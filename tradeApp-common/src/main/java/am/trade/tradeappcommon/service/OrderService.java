package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.model.Order;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface OrderService {

    void addOrder (Order order);

    List<Order> findAllOrders();

    List<Order> searchByDate(Date toDate);
    List<Order> searchByDateRange(Date toDate, Date fromDate);
}
