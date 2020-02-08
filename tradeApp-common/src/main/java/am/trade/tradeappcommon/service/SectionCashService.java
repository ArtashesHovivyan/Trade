package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.model.SectionCash;

import java.util.List;

public interface SectionCashService {
    void save(SectionCash sectionCash);

    List<SectionCash> findByDate(String date);

    SectionCash searchByDateAndUserId(String date, int id);

    SectionCash getById(int id);
}
