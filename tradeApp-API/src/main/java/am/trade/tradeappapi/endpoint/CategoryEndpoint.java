package am.trade.tradeappapi.endpoint;

import am.trade.tradeappcommon.exeption.CategoryNotFoundExeption;
import am.trade.tradeappcommon.model.Category;
import am.trade.tradeappcommon.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/category")
public class CategoryEndpoint {

    private final CategoryService categoryService;

    public CategoryEndpoint(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @PostMapping
    public ResponseEntity saveCategory(@RequestBody Category category) {
        if (categoryService.getCategoryByName(category.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        categoryService.addCategory(category);
        return ResponseEntity.ok(category.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategoryById(@PathVariable("id") int id) {
        if (categoryService.findCategoryById(id) != null) {
            return ResponseEntity.ok(categoryService.findCategoryById(id));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") int id) {
        if (categoryService.findCategoryById(id) == null){
            return ResponseEntity.notFound().build();
        }
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
