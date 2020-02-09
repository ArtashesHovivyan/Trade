package am.trade.tradeappcommon.service.impl;

import am.trade.tradeappcommon.model.Category;
import am.trade.tradeappcommon.model.Items;
import am.trade.tradeappcommon.repository.ItemRepository;
import am.trade.tradeappcommon.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void saveItem(Items item) {
        itemRepository.save(item);

    }

    @Override
    public Optional<Items> findItemByTitleOrBarcode(String title, String barcode) {
        Optional<Items> items = itemRepository.findByTitleOrBarcode(title, barcode);
        return items;
    }

    @Override
    public Optional<Items> findItemById(int id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Items> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Items getItemById(int id) {
        return itemRepository.getOne(id);
    }

    @Override
    public List<Items> findByCategory(Category category) {
        return itemRepository.findAllByCategory(category);
    }

    @Override
    public List<Items> findAllById(int id) {
        return itemRepository.findAllById(id);
    }


}
