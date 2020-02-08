package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.model.InCame;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InCameService {

    void addInCame(InCame inCame);

    List<InCame> findAllInCames();

}
