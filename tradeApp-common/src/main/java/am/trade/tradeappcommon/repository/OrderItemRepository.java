package am.trade.tradeappcommon.repository;

import am.trade.tradeappcommon.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findAllByOrderId(int orderId);
}
