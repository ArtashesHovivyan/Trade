package am.trade.tradeappweb.controller;

import am.trade.tradeappcommon.model.InCame;
import am.trade.tradeappcommon.model.InCameItem;
import am.trade.tradeappcommon.service.*;
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
public class InCameController {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final PeopleService peopleService;
    private final InCameService inCameService;
    private final ItemService itemService;
    private final InCameItemService inCameItemService;

    public InCameController(PeopleService peopleService, InCameService inCameService, ItemService itemService, InCameItemService inCameItemService) {
        this.peopleService = peopleService;
        this.inCameService = inCameService;
        this.itemService = itemService;
        this.inCameItemService = inCameItemService;
    }


    @GetMapping("/inCame")
    public String incame(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser == null) {
            return "redirect:/log";
        }
        List<FindOrderDto> inCameDtoList = null;
        try {
            inCameDtoList = findInCameDtoList(dateRange("", ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("inCame", inCameDtoList);
        modelMap.addAttribute("user", currentUser.getUser());
        return "inCames";
    }


    @PostMapping("/inCame")
    public String inCamePage(ModelMap modelMap,
                             @RequestParam(value = "start") String starDate,
                             @RequestParam(value = "end") String endDate,
                             @AuthenticationPrincipal CurrentUser currentUser) throws ParseException {
        if (currentUser != null) {
            List<FindOrderDto> orderDtoList = findInCameDtoList(dateRange(starDate, endDate));
            modelMap.addAttribute("inCame", orderDtoList);
            modelMap.addAttribute("user", currentUser.getUser());
            return "inCames";
        }
        return "redirect:/log";
    }

    public List<FindOrderDto> findInCameDtoList(String date) throws ParseException {
        String[] tmp = date.split(",");
        List<FindOrderDto> findOrderDtoList = new LinkedList<>();
        List<InCame> result = inCameService.searchInCameByDateRange(sdf.parse(tmp[0]), sdf.parse(tmp[1]));
        for (InCame inCame : result) {
            List<InCameItem> byInCameId = inCameItemService.findByInCameId(inCame.getId());
            double orderSum = 0.0;
//            Ապրանքի լիստ
            List<ItemMainDto> itemMainDtoList = new ArrayList<>();
            double inCameItemSum = 0.0;
            for (InCameItem inCameItem : byInCameId) {
                ItemMainDto itemMainDto = new ItemMainDto();
                itemMainDto.setId(inCameItem.getId());
                itemMainDto.setCount(inCameItem.getCount());
                itemMainDto.setTitle(inCameItem.getTitle());
                itemMainDto.setPriceOut(inCameItem.getPriceOut());
                itemMainDtoList.add(itemMainDto);
                inCameItemSum += inCameItem.getPriceOut() * inCameItem.getCount();
            }
//            Ապրանքի լիստ վերջ
            orderSum += inCameItemSum;
            FindOrderDto findOrderDto = new FindOrderDto();
            findOrderDto.setId(inCame.getId());
            findOrderDto.setDate(inCame.getDate());
            findOrderDto.setItemMainDtos(itemMainDtoList);
            findOrderDto.setOrderSum(orderSum);
            findOrderDto.setPeople(inCame.getPeople());
            UserDto userDto = new UserDto(inCame.getUser().getId(),
                    inCame.getUser().getName(), inCame.getUser().getSurname(),
                    inCame.getUser().getLogin());
            findOrderDto.setUserDto(userDto);
            findOrderDtoList.add(findOrderDto);
        }
        return findOrderDtoList;
    }

    public String dateRange(String startDate, String endDate) {
        String dateRange = startDate + "," + endDate;
        DateTime time = new DateTime().withTimeAtStartOfDay();
        System.out.println(time);
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
