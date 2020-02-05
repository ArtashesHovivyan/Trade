package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.model.Category;
import am.trade.tradeappcommon.model.Items;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    void saveItem(Items item);

    Optional<Items> findItemByTitleOrBarcode(String title, String barcode);
    Optional<Items> findItemById(int id);

    List<Items> findAll();

    void deleteById(int id);

    Items getItemById(int id);

    List<Items> findByCategory(Category category);


}
