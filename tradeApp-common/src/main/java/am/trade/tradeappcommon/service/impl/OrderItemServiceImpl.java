package am.trade.tradeappcommon.service.impl;

import am.trade.tradeappcommon.model.OrderItem;
import am.trade.tradeappcommon.repository.OrderItemRepository;
import am.trade.tradeappcommon.service.OrderItemService;

import org.springframework.stereotype.Service;


@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
