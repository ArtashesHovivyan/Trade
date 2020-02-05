package am.trade.tradeappcommon.repository;

import am.trade.tradeappcommon.model.Category;
import am.trade.tradeappcommon.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Items, Integer> {

    Optional<Items> findByTitleOrBarcode(String title, String barcode);
    Optional<Items> findById(int id);

    List<Items> findAllByCategory(Category category);
}
