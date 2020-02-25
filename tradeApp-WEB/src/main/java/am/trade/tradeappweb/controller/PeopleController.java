package am.trade.tradeappweb.controller;

import am.trade.tradeappcommon.model.People;
import am.trade.tradeappcommon.service.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

@Controller
public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


//    @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
//    public @ResponseBody
//    String getTime(@RequestParam(value = "name") String phone) {
//        People people = peopleService.findPeopleByEmailOrPhone(phone);
//        String result = people.getName();
//        return result;
//    }

    @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
    public @ResponseBody
    String getPeople() {
        People people = peopleService.findPeopleByEmailOrPhone("093-898394");
        String result = people.getName();
        return result;
    }

}
