package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.model.OrderItem;
import am.trade.tradeappcommon.model.User;

import java.util.List;

public interface OrderItemService {

    void saveOrderItem(OrderItem orderItem);

    List<OrderItem> findAllByUserId(User user);
}

