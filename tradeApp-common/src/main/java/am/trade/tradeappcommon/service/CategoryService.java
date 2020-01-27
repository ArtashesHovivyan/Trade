package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.model.Category;

import java.util.List;

public interface CategoryService {

    void addCategory(Category category);

    List<Category> findAll();

    boolean getCategoryByName(String name);
}
