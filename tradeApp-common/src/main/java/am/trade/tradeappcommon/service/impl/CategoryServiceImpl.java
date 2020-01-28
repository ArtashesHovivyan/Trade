package am.trade.tradeappcommon.service.impl;

import am.trade.tradeappcommon.model.Category;
import am.trade.tradeappcommon.repository.CategoryRepository;
import am.trade.tradeappcommon.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean getCategoryByName(String name) {
        return categoryRepository.getByName(name).isPresent();
    }

    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

}
