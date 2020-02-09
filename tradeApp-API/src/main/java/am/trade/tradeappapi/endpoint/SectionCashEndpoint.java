package am.trade.tradeappapi.endpoint;

import am.trade.tradeappapi.dto.TransferDto;
import am.trade.tradeappapi.security.CurrentUser;
import am.trade.tradeappcommon.model.OrderItem;
import am.trade.tradeappcommon.model.SectionCash;
import am.trade.tradeappcommon.model.Transfer;
import am.trade.tradeappcommon.model.User;
import am.trade.tradeappcommon.service.OrderItemService;
import am.trade.tradeappcommon.service.SectionCashService;
import am.trade.tradeappcommon.service.TransferService;
import am.trade.tradeappcommon.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/sectioncash")
public class SectionCashEndpoint {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final UserService userService;
    private final OrderItemService orderItemService;
    private final SectionCashService sectionCashService;
    private final TransferService transferService;

    public SectionCashEndpoint(UserService userService, OrderItemService orderItemService, SectionCashService sectionCashService, TransferService transferService) {
        this.userService = userService;
        this.orderItemService = orderItemService;
        this.sectionCashService = sectionCashService;
        this.transferService = transferService;
    }

    @GetMapping
    public ResponseEntity saveSectionCashIncomming() {
        List<User> allUsers = userService.findAllUsers();

        for (User user : allUsers) {
            SectionCash sectionCash = new SectionCash();
            sectionCash.setUser(user);
            String date = sdf.format(new Date());
            sectionCash.setDate(new Date());
            List<OrderItem> allItemsByUserId = orderItemService.findAllByUserId(user);
            Double userSum = 0.0;
            for (OrderItem orderItem : allItemsByUserId) {
                double count = orderItem.getCount();
                double priceOut = orderItem.getItems().getPriceOut();
                userSum += count * priceOut;
            }
            sectionCash.setIncoming(userSum);
            List<SectionCash> sectionCashDate = sectionCashService.findByDate(date);
            for (SectionCash cash : sectionCashDate) {
                if (cash.getUser().getLogin().equals(user.getLogin())) {
                    sectionCash.setId(cash.getId());
                    sectionCashService.save(sectionCash);
                }
            }
            sectionCashService.save(sectionCash);
        }
        return ResponseEntity.ok("Section Cash are saved");
    }


    @PostMapping
    public ResponseEntity transfer(@RequestBody TransferDto transferDto, @AuthenticationPrincipal CurrentUser currentUser) {
        SectionCash fromSectionCash = sectionCashService.getById(transferDto.getSectionId());
        String date = sdf.format(new Date());
        SectionCash toSectionCash = sectionCashService.searchByDateAndUserId(date, currentUser.getUser().getId());
        double toIncoming = toSectionCash.getIncoming();
        double fromOutComing = transferDto.getOutComing();
        double toIncomingSum = toIncoming + fromOutComing;
        Transfer transfer = new Transfer();
        transfer.setFrom(fromSectionCash.getUser());
        transfer.setTo(currentUser.getUser());
        transfer.setPrice(transferDto.getOutComing());
        transferService.save(transfer);
        toSectionCash.setIncoming(toIncomingSum);
        toSectionCash.setDescription(transferDto.getDescription());
        sectionCashService.save(toSectionCash);
        fromSectionCash.setIncoming(fromSectionCash.getIncoming() - fromOutComing);
        fromSectionCash.setOutcoming(fromOutComing);
        sectionCashService.save(fromSectionCash);
        return ResponseEntity.ok("Transfer are saved");
//        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
