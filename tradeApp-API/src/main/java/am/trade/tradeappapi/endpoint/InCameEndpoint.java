package am.trade.tradeappapi.endpoint;

import am.trade.tradeappapi.dto.AddOrderDto;
import am.trade.tradeappapi.dto.OrderItemDto;
import am.trade.tradeappapi.security.CurrentUser;
import am.trade.tradeappcommon.model.*;
import am.trade.tradeappcommon.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/incame")
public class InCameEndpoint {

    private final PeopleService peopleService;
    private final InCameService inCameService;
    private final ItemService itemService;
    private final InCameItemService inCameItemService;

    public InCameEndpoint(PeopleService peopleService, InCameService inCameService, ItemService itemService, InCameItemService inCameItemService) {
        this.peopleService = peopleService;
        this.inCameService = inCameService;
        this.itemService = itemService;
        this.inCameItemService = inCameItemService;
    }

    @PostMapping
    public ResponseEntity saveInCameItem(@RequestBody AddOrderDto addOrderDto, @AuthenticationPrincipal CurrentUser currentUser) {
        InCame inCame = new InCame();
        inCame.setUser(currentUser.getUser());
        inCame.setPeople(peopleService.getPeopleByPhone(addOrderDto.getPhoneNumber()));
        for (OrderItemDto orderItemDto : addOrderDto.getOrderItemDtos()) {
            InCameItem inCameItem = new InCameItem();
            if (!itemService.findItemById(orderItemDto.getItemId()).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            inCameItem.setItems(itemService.getItemById(orderItemDto.getItemId()));
            inCameItem.setCount(orderItemDto.getCount());
            inCameItem.setInCame(inCame);
            double itemCount = itemService.getItemById(orderItemDto.getItemId()).getCount();
            double count = itemCount + inCameItem.getCount();
            inCameService.addInCame(inCame);
            Items items = itemService.getItemById(orderItemDto.getItemId());
            items.setCount(count);
            itemService.saveItem(items);
            inCameItemService.saveInCameItem(inCameItem);
        }
        return ResponseEntity.ok("Order successful saved");
    }

//    -----saveOrder - JSON-----
//    {
//        "phoneNumber": "093-987456",
//            "orderItemDtos": [
//        {
//            "itemId": "4",
//                "count": "10"
//        },
//        {
//            "itemId": "2",
//                "count": "5"
//        }]
//    }

    @GetMapping
    public List<InCame> allInCame() {
        return inCameService.findAllInCames();
    }
}
