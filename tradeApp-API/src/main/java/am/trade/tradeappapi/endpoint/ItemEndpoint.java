package am.trade.tradeappapi.endpoint;

import am.trade.tradeappcommon.model.Items;
import am.trade.tradeappcommon.service.CategoryService;
import am.trade.tradeappcommon.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/items")
public class ItemEndpoint {

    private final ItemService itemService;
    private final CategoryService categoryService;

    public ItemEndpoint(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity saveItem(@RequestBody Items items) {
        if (itemService.findItemByTitleOrBarcode(items.getTitle(), items.getBarcode()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else
            itemService.addItem(items);
        return ResponseEntity.ok(items.getId());
    }

    @GetMapping
    public List<Items> items() {
//        Add items DTO
        System.out.println(itemService.findAll());
        return itemService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable("id") int id) {
        itemService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getItem(@PathVariable("id") int id) {
        if (itemService.getItemById(id) != null) {
            return ResponseEntity.ok(itemService.getItemById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findItems/{name}")
    public ResponseEntity searchByTitleOrBarcode(@PathVariable("name") String name) {
        if (itemService.getItemsByTitleOrBarcode(name, name) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemService.getItemsByTitleOrBarcode(name, name));
    }

    @GetMapping("/findByCategory/{id}")
    public ResponseEntity findByCategory(@PathVariable("id") int category) {
        if (itemService.findByCategory(categoryService.findCategoryById(category)) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemService.findByCategory(categoryService.findCategoryById(category)));
    }

}
