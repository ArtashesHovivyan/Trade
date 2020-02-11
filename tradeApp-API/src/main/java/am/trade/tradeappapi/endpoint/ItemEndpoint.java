package am.trade.tradeappapi.endpoint;

import am.trade.tradeappapi.dto.ItemMainDto;
import am.trade.tradeappcommon.model.Items;
import am.trade.tradeappcommon.service.CategoryService;
import am.trade.tradeappcommon.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin("*")
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
            itemService.saveItem(items);
        return ResponseEntity.ok(items.getId());
    }

    @GetMapping
    public List<ItemMainDto> allItemsforMain() {
        List<ItemMainDto> itemMainDtoList = new ArrayList<>();
        for (Items items : itemService.findAll()) {
            ItemMainDto itemMainDto = new ItemMainDto();
            itemMainDto.setId(items.getId());
            itemMainDto.setTitle(items.getTitle());
            itemMainDto.setPriceOut(items.getPriceOut());
            itemMainDto.setCount(items.getCount());
            itemMainDtoList.add(itemMainDto);
        }
        return itemMainDtoList;
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
// All items by Id, title or barcode with ItemMainDto
    @GetMapping("/findAllItems/{name}")
    public ResponseEntity searchByIdTitleOrBarcode(@PathVariable("name") String name) {
        List<Items> allItems = itemService.findAll();
        List<ItemMainDto> findItem = new ArrayList<>();
        for (Items allItem : allItems) {
            if (allItem.getTitle().contains(name) || allItem.getBarcode().equals(name) || String.valueOf(allItem.getId()).contains(name)) {
                findItem.add(itemMaimDto(allItem));
            }
        }
        if (findItem.size() != 0) {
            return ResponseEntity.ok(findItem);
        }
        return ResponseEntity.notFound().build();
    }

    //    All items by category with ItemMAinDto
    @GetMapping("/findByCategory/{id}")
    public ResponseEntity findByCategory(@PathVariable("id") int category) {
        if (itemService.findByCategory(categoryService.findCategoryById(category)) == null) {
            return ResponseEntity.notFound().build();
        }
        List<ItemMainDto> itemMainDtos = new ArrayList<>();
        for (Items items : itemService.findByCategory(categoryService.findCategoryById(category))) {
           itemMainDtos.add(itemMaimDto(items));
        }
        return ResponseEntity.ok(itemMainDtos);
    }


    public ItemMainDto itemMaimDto (Items items){
        return ItemMainDto.builder()
                .id(items.getId())
                .title(items.getTitle())
                .count(items.getCount())
                .priceOut(items.getPriceOut())
                .build();
    }
}
