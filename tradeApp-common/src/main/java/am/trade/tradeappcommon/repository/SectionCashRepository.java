package am.trade.tradeappcommon.repository;


import am.trade.tradeappcommon.model.SectionCash;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;

public interface SectionCashRepository extends JpaRepository<SectionCash, Integer> {
    @Query(value = "select * from section_cash  where  date = :date", nativeQuery = true)
    List<SectionCash> findByDate(@Param("date") String date);

    @Query("select s from SectionCash s where s.date >= :date")
    List<SectionCash> findOutComingByDate(@Param("date") Date date);

    @Query(value = "SELECT * FROM section_cash  WHERE  DATE = :date AND user_id = :user", nativeQuery = true)
    SectionCash searchByDateAndUserId(@Param("date") String date,
                                      @Param("user") int id);
}
