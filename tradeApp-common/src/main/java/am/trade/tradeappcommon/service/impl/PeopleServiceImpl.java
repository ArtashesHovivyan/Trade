package am.trade.tradeappcommon.service.impl;

import am.trade.tradeappcommon.model.People;
import am.trade.tradeappcommon.repository.PeopleRepository;
import am.trade.tradeappcommon.service.PeopleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public boolean isEmailExists(String email) {
        return peopleRepository.findByEmail(email) != null;
    }

    @Override
    public void registerPeople(People people) {
        peopleRepository.save(people);
    }

    @Override
    public List<People> findAll() {
        return peopleRepository.findAll();
    }

    @Override
    public People findPeopleById(int id) {
        return peopleRepository.findById(id);
    }


    @Override
    public People getByEmailOrPhone(String email, String phone) {
       return peopleRepository.getPeopleByEmailOrPhone(email, phone);
    }

    @Override
    public People getPeopleByPhone(String phone) {
        return peopleRepository.getPeopleByPhone(phone);
    }

}
