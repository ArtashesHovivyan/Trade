package am.trade.tradeappapi.endpoint;

import am.trade.tradeappcommon.model.OrderItem;
import am.trade.tradeappcommon.model.SectionCash;
import am.trade.tradeappcommon.model.User;
import am.trade.tradeappcommon.service.OrderItemService;
import am.trade.tradeappcommon.service.SectionCashService;
import am.trade.tradeappcommon.service.UserService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@EnableScheduling
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/sectionCash")
public class SectionCashEndpoint {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final UserService userService;
    private final OrderItemService orderItemService;
    private final SectionCashService sectionCashService;

    public SectionCashEndpoint(UserService userService, OrderItemService orderItemService, SectionCashService sectionCashService) {
        this.userService = userService;
        this.orderItemService = orderItemService;
        this.sectionCashService = sectionCashService;
    }

    @GetMapping
    @Scheduled(fixedDelay = 10*60000)
    public void saveSectionCashIncomming() {
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
                double priceOut = orderItem.getPriceOut();
                userSum += count * priceOut;
            }
            sectionCash.setIncoming(userSum);
            List<SectionCash> sectionCashDate = sectionCashService.findByDate(date);
            for (SectionCash cash : sectionCashDate) {
                if (cash.getUser().getLogin().equals(user.getLogin())) {
                    sectionCash.setId(cash.getId());
                    sectionCash.setOutcoming(cash.getOutcoming());
                    sectionCashService.save(sectionCash);
                }
            }
            sectionCashService.save(sectionCash);
        }
    }
}
