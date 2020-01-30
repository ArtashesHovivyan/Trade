package am.trade.tradeappcommon.repository;

import am.trade.tradeappcommon.model.Category;
import am.trade.tradeappcommon.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Items, Integer> {

    Optional<Items> findByTitleOrBarcode(String title, String barcode);

    Items getItemsByTitleOrBarcode(String title, String barcode);

    List<Items> findAllByCategory(Category category);
}
