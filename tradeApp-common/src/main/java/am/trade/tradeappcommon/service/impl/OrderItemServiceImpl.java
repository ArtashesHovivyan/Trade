package am.trade.tradeappcommon.service.impl;

import am.trade.tradeappcommon.model.OrderItem;
import am.trade.tradeappcommon.repository.OrderItemRepository;
import am.trade.tradeappcommon.service.OrderItemService;
import am.trade.tradeappcommon.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderService orderService;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderService orderService) {
        this.orderItemRepository = orderItemRepository;
        this.orderService = orderService;
    }

    @Override
    public void addOrder(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> findAllByOrderId(int id) {
        return orderItemRepository.findAllByOrderId(id);
    }

}
