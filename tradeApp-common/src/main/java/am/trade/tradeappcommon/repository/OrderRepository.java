package am.trade.tradeappcommon.repository;

import am.trade.tradeappcommon.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select a from Order a where a.date >= :creationDateTime")
    List<Order> searchByDate(
            @Param("creationDateTime") Date creationDateTime);

    @Query(value = "select a from Order a where a.date between :toDate and :fromDate")
    List<Order> searchByDateRange(
            @Param("toDate") Date toDateTime,
            @Param("fromDate") Date fromDateTime);
}
