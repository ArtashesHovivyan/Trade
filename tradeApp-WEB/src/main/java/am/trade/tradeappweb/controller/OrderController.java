package am.trade.tradeappweb.controller;

import am.trade.tradeappcommon.model.Order;
import am.trade.tradeappcommon.model.OrderItem;
import am.trade.tradeappcommon.service.ItemService;
import am.trade.tradeappcommon.service.OrderItemService;
import am.trade.tradeappcommon.service.OrderService;
import am.trade.tradeappcommon.service.PeopleService;
import am.trade.tradeappweb.dto.FindOrderDto;
import am.trade.tradeappweb.dto.ItemMainDto;
import am.trade.tradeappweb.dto.UserDto;
import am.trade.tradeappweb.security.CurrentUser;
import org.joda.time.DateTime;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class OrderController {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final PeopleService peopleService;
    private final OrderService orderService;
    private final ItemService itemService;
    private final OrderItemService orderItemService;

    public OrderController(PeopleService peopleService, OrderService orderService, ItemService itemService, OrderItemService orderItemService) {
        this.peopleService = peopleService;
        this.orderService = orderService;
        this.itemService = itemService;
        this.orderItemService = orderItemService;
    }

    @GetMapping("/order")
    public String order(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser == null) {
            return "redirect:/log";
        }
        List<FindOrderDto> orderDtoList = null;
        try {
            orderDtoList = findOrderDtoList(dateRange("", ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("order", orderDtoList);
        modelMap.addAttribute("user", currentUser.getUser());
        return "orders";
    }


    @PostMapping("/order")
    public String orderPage(ModelMap modelMap,
                            @RequestParam(value = "start") String starDate,
                            @RequestParam(value = "end") String endDate,
                            @AuthenticationPrincipal CurrentUser currentUser) throws ParseException {
        if (currentUser != null ) {
            List<FindOrderDto> orderDtoList = findOrderDtoList(dateRange(starDate, endDate));
            modelMap.addAttribute("order", orderDtoList);
            modelMap.addAttribute("user", currentUser.getUser());
            return "orders";
        }
        return "redirect:/log";
    }

    public List<FindOrderDto> findOrderDtoList(String date) throws ParseException {

        String[] tmp = date.split(",");
        List<FindOrderDto> findOrderDtoList = new LinkedList<>();
        List<Order> result = orderService.searchByDateRange(sdf.parse(tmp[0]), sdf.parse(tmp[1]));

        for (Order order : result) {
            List<OrderItem> byOrderId = orderItemService.findByOrderId(order.getId());
            double orderSum = 0.0;
//            Ապրանքի լիստ
            List<ItemMainDto> itemMainDtoList = new ArrayList<>();
            double orderItemSum = 0.0;
            for (OrderItem orderItem : byOrderId) {
                ItemMainDto itemMainDto = new ItemMainDto();
                itemMainDto.setId(orderItem.getId());
                itemMainDto.setCount(orderItem.getCount());
                itemMainDto.setTitle(orderItem.getTitle());
                itemMainDto.setPriceOut(orderItem.getPriceOut());
                itemMainDtoList.add(itemMainDto);
                orderItemSum += orderItem.getPriceOut() * orderItem.getCount();
            }
//            Ապրանքի լիստ վերջ
            orderSum += orderItemSum;
            FindOrderDto findOrderDto = new FindOrderDto();
            findOrderDto.setId(order.getId());
            findOrderDto.setDate(order.getDate());
            findOrderDto.setItemMainDtos(itemMainDtoList);
            findOrderDto.setOrderSum(orderSum);
            findOrderDto.setPeople(order.getPeople());
            UserDto userDto = new UserDto(order.getUser().getId(), order.getUser()
                    .getName(), order.getUser().getSurname(), order.getUser().getLogin());
            findOrderDto.setUserDto(userDto);
            findOrderDtoList.add(findOrderDto);
        }
        return findOrderDtoList;
    }

    public String dateRange(String startDate, String endDate) {
        String dateRange = startDate + "," + endDate;
        DateTime time = new DateTime().withTimeAtStartOfDay();
        Date start = time.toDate();
        Date end = new Date();
        String a = sdf.format(start);
        String b = sdf.format(end);
        if (dateRange.equals(",")) {
            dateRange = a + "," + b;
        } else if (startDate.equals("")) {
            dateRange = a + "," + endDate;
        } else if (endDate.equals("")) {
            dateRange = startDate + "," + b;
        }
        return dateRange;
    }

}
