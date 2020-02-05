package am.trade.tradeappcommon.repository;

import am.trade.tradeappcommon.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
