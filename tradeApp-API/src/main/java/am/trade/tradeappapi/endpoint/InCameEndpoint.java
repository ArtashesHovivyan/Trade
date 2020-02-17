package am.trade.tradeappapi.endpoint;

import am.trade.tradeappapi.dto.AddOrderDto;
import am.trade.tradeappapi.dto.OrderItemDto;
import am.trade.tradeappapi.security.CurrentUser;
import am.trade.tradeappcommon.model.*;
import am.trade.tradeappcommon.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/income")
public class InCameEndpoint {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final PeopleService peopleService;
    private final InCameService inCameService;
    private final ItemService itemService;
    private final InCameItemService inCameItemService;
    private final SectionCashService sectionCashService;
    private final TransferService transferService;


    public InCameEndpoint(PeopleService peopleService, InCameService inCameService, ItemService itemService, InCameItemService inCameItemService, SectionCashEndpoint sectionCashEndpoint, SectionCashService sectionCashService, TransferService transferService) {
        this.peopleService = peopleService;
        this.inCameService = inCameService;
        this.itemService = itemService;
        this.inCameItemService = inCameItemService;
        this.sectionCashService = sectionCashService;
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity saveInCameItem(@RequestBody AddOrderDto addOrderDto, @AuthenticationPrincipal CurrentUser currentUser) {
        InCame inCame = new InCame();
        inCame.setUser(currentUser.getUser());
        People peopleByPhone = peopleService.getPeopleByPhone(addOrderDto.getPhoneNumber());
        inCame.setPeople(peopleByPhone);
        double outComingCash = 0.0;
        for (OrderItemDto orderItemDto : addOrderDto.getOrderItemDtos()) {
            InCameItem inCameItem = new InCameItem();
            if (!itemService.findItemById(orderItemDto.getItemId()).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            Items items = itemService.getItemById(orderItemDto.getItemId());
            inCameItem.setInCame(inCame);
            inCameItem.setTitle(items.getTitle());
            inCameItem.setDescription(items.getDescription());
            inCameItem.setCount(orderItemDto.getCount());
            inCameItem.setCategoryName(items.getCategory().getName());
            inCameItem.setPriceIn(items.getPriceIn());
            inCameItem.setPriceOut(items.getPriceOut());

            double itemCount = items.getCount();
            double count = itemCount + inCameItem.getCount();
            inCameService.addInCame(inCame);
//            double priceIn = items.getPriceIn();
//            double priceOut = items.getPriceOut();
            items.setCount(count);
            itemService.saveItem(items);
            inCameItemService.saveInCameItem(inCameItem);
            outComingCash += items.getPriceIn() * inCameItem.getCount();
        }
        String date = sdf.format(new Date());
        SectionCash toSectionCash = sectionCashService.searchByDateAndUserId(date, currentUser.getUser().getId());
        double toIncoming = toSectionCash.getIncoming();
        double toIncomingSum = toIncoming - outComingCash;
        Transfer transfer = new Transfer();
        transfer.setFrom(currentUser.getUser());
        transfer.setToPeople(peopleByPhone);
        transfer.setPrice(outComingCash);
        transferService.save(transfer);
        double sectionCashOutcoming = toSectionCash.getOutcoming()+outComingCash;
        toSectionCash.setIncoming(toIncomingSum);
        toSectionCash.setOutcoming(sectionCashOutcoming);
        toSectionCash.setDescription(currentUser.getUser().getName() + " Do inCame order");
        sectionCashService.save(toSectionCash);

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