package am.trade.tradeappcommon.service.impl;

import am.trade.tradeappcommon.model.InCame;
import am.trade.tradeappcommon.model.Order;
import am.trade.tradeappcommon.repository.InCameRepository;
import am.trade.tradeappcommon.service.InCameService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InCameServiceImpl implements InCameService {

    private final InCameRepository inCameRepository;

    public InCameServiceImpl(InCameRepository inCameRepository) {
        this.inCameRepository = inCameRepository;
    }

    @Override
    public void addInCame(InCame inCame) {
        inCameRepository.save(inCame);
    }

    @Override
    public List<InCame> findAllInCames() {
        return inCameRepository.findAll();
    }
}
