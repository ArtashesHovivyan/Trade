package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.model.Category;
import am.trade.tradeappcommon.model.Items;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    void addItem(Items item);

    Optional<Items> findItemByTitleOrBarcode(String title, String barcode);

    List<Items> findAll();

    void deleteById(int id);

    Items getItemById(int id);

    Items getItemsByTitleOrBarcode(String title, String barcode);

    List<Items> findByCategory(Category category);

}
