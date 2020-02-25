package am.trade.tradeappweb.controller;

import am.trade.tradeappcommon.model.Items;
import am.trade.tradeappcommon.service.CategoryService;
import am.trade.tradeappcommon.service.ItemService;
import am.trade.tradeappcommon.service.PeopleService;
import am.trade.tradeappweb.dto.ItemMainDto;
import am.trade.tradeappweb.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final CategoryService categoryService;
    private final ItemService itemService;
    private final PeopleService peopleService;

    public MainController(CategoryService categoryService, ItemService itemService, PeopleService peopleService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.peopleService = peopleService;
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }


    @GetMapping("/mainPage")
    public String mainPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            modelMap.addAttribute("user", currentUser.getUser());
            modelMap.addAttribute("allCategories", categoryService.findAll());
            List<ItemMainDto> itemMainDtoList = new ArrayList<>();
            List<Items> all = itemService.findAll();
            for (Items items : all) {
                ItemMainDto itemMainDto = new ItemMainDto();
                itemMainDto.setId(items.getId());
                itemMainDto.setTitle(items.getTitle());
                itemMainDto.setCount(items.getCount());
                itemMainDto.setPriceOut(items.getPriceOut());
                itemMainDtoList.add(itemMainDto);
            }
            modelMap.addAttribute("allItems", itemMainDtoList);
            return "mainPage";
        }
        return "redirect:/log";
    }



    @PostMapping("/mainPage")
    public String home(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser == null) {
            return "redirect:/log";
        }
        return "redirect:/mainPage";
    }
}
