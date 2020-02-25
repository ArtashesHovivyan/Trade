package am.trade.tradeappcommon.repository;

import am.trade.tradeappcommon.model.InCameItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InCameItemRepository extends JpaRepository<InCameItem, Integer> {

    List<InCameItem> findByInCameId(int id);
}
