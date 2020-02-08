package am.trade.tradeappcommon.repository;

import am.trade.tradeappcommon.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByUserId(int userId);

    @Query(value = "SELECT * FROM orders a WHERE date BETWEEN :toDate AND :fromDate + interval 1 day", nativeQuery = true)
    List<Order> searchByDateRange(
            @Param("toDate") Date toDateTime,
            @Param("fromDate") Date fromDateTime);
}
