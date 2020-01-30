package am.trade.tradeappcommon.repository;

import am.trade.tradeappcommon.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByPeopleId(int id);

    List<Order> findAllByPeopleId(int id);
}
