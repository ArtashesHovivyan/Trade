package am.trade.tradeappcommon.service.impl;

import am.trade.tradeappcommon.model.InCameItem;
import am.trade.tradeappcommon.repository.InCameItemRepository;
import am.trade.tradeappcommon.service.InCameItemService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InCameItemServiceImpl implements InCameItemService {

    private final InCameItemRepository inCameItemRepository;

    public InCameItemServiceImpl(InCameItemRepository inCameItemRepository) {
        this.inCameItemRepository = inCameItemRepository;
    }

    @Override
    public void saveInCameItem(InCameItem inCameItem) {
        inCameItemRepository.save(inCameItem);
    }

    @Override
    public List<InCameItem> findByInCameId(int id) {
        List<InCameItem> allInCameItems = inCameItemRepository.findByInCameId(id);
        return allInCameItems;
    }
}
