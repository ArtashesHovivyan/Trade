package am.trade.tradeappcommon.service.impl;

import am.trade.tradeappcommon.model.SectionCash;
import am.trade.tradeappcommon.repository.SectionCashRepository;
import am.trade.tradeappcommon.service.SectionCashService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionCashServiceImpl implements SectionCashService {

    private final SectionCashRepository sectionCashRepository;

    public SectionCashServiceImpl(SectionCashRepository sectionCashRepository) {
        this.sectionCashRepository = sectionCashRepository;
    }

    @Override
    public void save(SectionCash sectionCash) {
        sectionCashRepository.save(sectionCash);
    }

    @Override
    public List<SectionCash> findByDate(String date) {
        return sectionCashRepository.findByDate(date);
    }

    @Override
    public SectionCash searchByDateAndUserId(String date, int id) {
        System.out.println(date);
        System.out.println(id);
                return sectionCashRepository.searchByDateAndUserId(date, id);
    }

    @Override
    public SectionCash getById(int id) {
        return sectionCashRepository.getOne(id);
    }


}
