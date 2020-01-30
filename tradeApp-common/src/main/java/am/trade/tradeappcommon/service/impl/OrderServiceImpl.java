package am.trade.tradeappcommon.service.impl;

import am.trade.tradeappcommon.model.Order;
import am.trade.tradeappcommon.repository.OrderRepository;
import am.trade.tradeappcommon.service.OrderService;
import org.springframework.stereotype.Service;

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
    public Order getById(int id) {
        return orderRepository.getOne(id);
    }

    @Override
    public Order findOrderByPeopleId(int id) {
        return orderRepository.findByPeopleId(id);
    }

    @Override
    public List<Order> findAllByPeopleId(int id) {
        return orderRepository.findAllByPeopleId(id);
    }


}
