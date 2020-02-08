package am.trade.tradeappcommon.service.impl;

import am.trade.tradeappcommon.model.Order;
import am.trade.tradeappcommon.repository.OrderRepository;
import am.trade.tradeappcommon.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByUserId(int user) {
        return orderRepository.findAllByUserId(user);
    }

    @Override
    public List<Order> searchByDateRange(Date toDate, Date fromDate) {
        return orderRepository.searchByDateRange(toDate, fromDate);
    }

}
