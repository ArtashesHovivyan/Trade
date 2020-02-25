package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.model.InCame;

import java.util.Date;
import java.util.List;

public interface InCameService {

    void addInCame(InCame inCame);

    List<InCame> findAllInCames();

    List<InCame> searchInCameByDateRange(Date toDate, Date fromDate);

}
