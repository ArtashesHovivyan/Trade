package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.model.OrderItem;

import java.util.List;

public interface OrderItemService {

    void addOrder(OrderItem orderItem);

    List<OrderItem> findAllByOrderId(int id);

}
