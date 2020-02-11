package am.trade.tradeappapi.endpoint;

import am.trade.tradeappcommon.exeption.UserNotFoundExeption;
import am.trade.tradeappcommon.model.People;
import am.trade.tradeappcommon.service.PeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/people")
public class PeopleEndpoint {

    private final PeopleService peopleService;

    public PeopleEndpoint(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public List<People> peoples() {
        return peopleService.findAll();
    }

    @PostMapping
    public ResponseEntity savePeople(@RequestBody People people) {
        if (peopleService.isEmailExists(people.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        peopleService.registerPeople(people);
        return ResponseEntity.ok(people.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity getPeopleById(@PathVariable("id") int id) {
        if (peopleService.findPeopleById(id) != null) {
            return ResponseEntity.ok(peopleService.findPeopleById(id));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity getPeopleByPhone(@PathVariable("phone") String name) {
        if (peopleService.getPeopleByPhone(name) == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(peopleService.getPeopleByPhone(name));
    }

//    Փնտրել մարդուն իր հեռախոսահամարով կամ էլեկտրոնային հասցեով
    @GetMapping("/phoneoremail/{search}")
    public ResponseEntity getPeopleByPhoneOrEmail(@PathVariable("search") String name) {
        if (peopleService.findPeopleByEmailOrPhone(name) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(peopleService.findPeopleByEmailOrPhone(name));
    }
}
