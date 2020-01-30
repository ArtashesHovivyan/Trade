package am.trade.tradeappapi.endpoint;

import am.trade.tradeappapi.security.CurrentUser;
import am.trade.tradeappcommon.model.*;
import am.trade.tradeappcommon.service.ItemService;
import am.trade.tradeappcommon.service.OrderItemService;
import am.trade.tradeappcommon.service.OrderService;
import am.trade.tradeappcommon.service.PeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/orders")
public class OrderEndpoint {

    private final PeopleService peopleService;
    private final OrderService orderService;
    private final ItemService itemService;
    private final OrderItemService orderItemService;

    public OrderEndpoint(PeopleService peopleService, OrderService orderService, ItemService itemService, OrderItemService orderItemService) {
        this.peopleService = peopleService;
        this.orderService = orderService;
        this.itemService = itemService;
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity saveItem(@RequestBody Order order, @AuthenticationPrincipal CurrentUser currentUser) {
        People people = peopleService.getPeopleByPhone(order.getPeople().getPhone());
        User user = currentUser.getUser();
        Order currentOrder = new Order();
        currentOrder.setPeople(people);
        currentOrder.setUser(user);

        orderService.addOrder(currentOrder);

        List<Items> orderItems = order.getItemsList();
        for (Items item : orderItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItems(item);
            orderItem.setOrder(currentOrder);
            orderItem.setCount(orderItem.getCount());
            orderItemService.addOrder(orderItem);
        }
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrderByPeopleId(@PathVariable("id") int id) {

        List<Order> allOrders = orderService.findAllByPeopleId(id);
        List<Order> orderList = new ArrayList<>();
        for (Order allOrder : allOrders) {
            List<OrderItem> orderItems = orderItemService.findAllByOrderId(allOrder.getId());
            List<Items> itemsList = new ArrayList<>();
            for (OrderItem orderItem : orderItems) {
                itemsList.add(itemService.getItemById(orderItem.getItems().getId()));            }
            allOrder.setItemsList(itemsList);
            orderList.add(allOrder);
        }
        return ResponseEntity.ok(orderList);
    }


//    @GetMapping("/{id}")
//    public ResponseEntity getOrderByPeopleId(@PathVariable("id") int id) {
//        if (peopleService.findPeopleById(id) != null) {
//            Order order = orderService.findOrderByPeopleId(id);
//            List<OrderItem> orderItems = orderItemService.findAllByOrderId(order.getId());
//            List<Items> itemsList = new ArrayList<>();
//            for (OrderItem orderItem : orderItems) {
//                itemsList.add(itemService.getItemById(orderItem.getItems().getId()));
//            }
//            order.setItemsList(itemsList);
//            return ResponseEntity.ok(order);
//        }
//        return ResponseEntity.notFound().build();
//    }


}
