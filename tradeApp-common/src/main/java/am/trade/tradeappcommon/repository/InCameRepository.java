package am.trade.tradeappcommon.repository;

import am.trade.tradeappcommon.model.InCame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface InCameRepository extends JpaRepository<InCame, Integer> {

    @Query(value = "SELECT * FROM in_came WHERE date BETWEEN :toDate AND :fromDate + interval 1 day", nativeQuery = true)
    List<InCame> searchByDateRange(
            @Param("toDate") Date toDateTime,
            @Param("fromDate") Date fromDateTime);

}
