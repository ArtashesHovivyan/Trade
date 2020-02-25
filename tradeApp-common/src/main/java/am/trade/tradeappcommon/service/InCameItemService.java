package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.model.InCameItem;

import java.util.List;

public interface InCameItemService {

    void saveInCameItem(InCameItem inCameItem);

    List<InCameItem> findByInCameId(int id);
}

